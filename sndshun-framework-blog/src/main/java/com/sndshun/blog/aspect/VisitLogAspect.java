package com.sndshun.blog.aspect;

import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sndshun.blog.annotation.VisitLog;
import com.sndshun.blog.entity.BlogVisitLogEntity;
import com.sndshun.blog.entity.BlogVisitUserEntity;
import com.sndshun.blog.service.BlogVisitLogService;
import com.sndshun.blog.service.BlogVisitUserService;
import com.sndshun.commons.tools.IPUtils;
import com.sndshun.commons.tools.Result;

import com.sndshun.commons.tools.Utils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 访问日志切面
 *
 * @author maple
 */
@Component
@Aspect
@Slf4j
public class VisitLogAspect {

    private final BlogVisitLogService blogVisitLogService;
    private final BlogVisitUserService blogVisitUserService;
    private final ThreadLocal<Long> currentTime = new ThreadLocal<>();

    @Autowired
    public VisitLogAspect(BlogVisitLogService blogVisitLogService, BlogVisitUserService blogVisitUserService) {
        this.blogVisitLogService = blogVisitLogService;
        this.blogVisitUserService = blogVisitUserService;
    }

    /**
     * 配置切入点
     *
     * @param visitLog 注解
     */
    @Pointcut("@annotation(visitLog)")
    public void logPointcut(VisitLog visitLog) {

    }

    /**
     * 配置环绕通知
     */
    @Around(value = "logPointcut(visitLog)", argNames = "joinPoint,visitLog")
    public Result<?> logAround(ProceedingJoinPoint joinPoint, VisitLog visitLog) throws Throwable {
        currentTime.set(System.currentTimeMillis());
        Result<?> result = (Result<?>) joinPoint.proceed();
        int times = (int) (System.currentTimeMillis() - currentTime.get());
        currentTime.remove();
        //获取请求对象
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        //获取到IP
        String ip = ServletUtil.getClientIP(request);
        //校验访客标识码
        String identification = checkIdentification(ip, request);
        //添加日志
        handleLog(joinPoint, visitLog, request, times, identification, ip);
        //添加访客
        getInformationViaIp(identification, ip);
        return result;
    }

    /**
     * 根据Ip检查访客身份
     *
     * @param ip      IP
     * @param request 请求
     * @return uuid
     */
    private String checkIdentification(String ip, HttpServletRequest request) {
        String uuid = blogVisitLogService.getValueByKey(ip);
        log.info("访客UUID：{}",uuid);
        //设略一些验证逻辑
        if (uuid == null) {
            uuid = generateUUID(request);
        }
        return uuid;
    }

    /**
     * 生成uuid
     *
     * @param request 请求
     * @return uuid
     */
    private String generateUUID(HttpServletRequest request) {
        //获取响应对象
        HttpServletResponse response = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getResponse();
        //获取当前时间戳，精确到小时，防刷访客数据
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        String timestamp = Long.toString(calendar.getTimeInMillis() / 1000);
        //获取访问者基本信息
        String ip = ServletUtil.getClientIP(request);
        String userAgent = request.getHeader("User-Agent");
        //根据时间戳、ip、userAgent生成UUID
        String assembleUuId = timestamp + ip + userAgent;
        String uuid = UUID.nameUUIDFromBytes(assembleUuId.getBytes()).toString().substring(19);
        //暴露自定义header供页面资源使用
        response.addHeader("Access-Control-Expose-Headers", "identification");
        return uuid;
    }

    /**
     * 设置VisitLo对象属性
     *
     * @param joinPoint 程序连接点
     * @param visitLog  访问日志
     * @param times     时间
     */
    private void handleLog(ProceedingJoinPoint joinPoint, VisitLog visitLog, HttpServletRequest request, int times, String uuid, String ip) {
        String uri = request.getRequestURI();
        String method = request.getMethod();
        String userAgent = request.getHeader("User-Agent");
        UserAgent parse = UserAgentUtil.parse(userAgent);
        //获取请求参数
        Map<String, Object> reqParams = getReqParams(joinPoint);
        //解析成字符串
        String params = Utils.substring(writeValueAsString(reqParams), 0, 2000);
        //初始化访问日志对象
        BlogVisitLogEntity blogVisitLog = new BlogVisitLogEntity();
        blogVisitLog.setUuid(uuid).setUri(uri).setMethod(method).setParam(params).setBehavior(visitLog.value().getType()).setContent(visitLog.value().getContent()).setRemark(null).setIp(ip).setOs(parse.getOs().toString()).setBrowser(parse.getBrowser().toString()).setTimes(times).setCreateTime(new Date()).setUserAgent(userAgent);
        //添加
        saveVisitLogAsync(blogVisitLog);
    }

    @Async
    void saveVisitLogAsync(BlogVisitLogEntity blogVisitLog) {
        // 调用保存访问日志的服务方法
        blogVisitLogService.save(blogVisitLog);
    }

    /**
     * 通过Ip获取信息
     *
     * @param uuid 访客识别码
     * @param ip   Ip
     */
    private void getInformationViaIp(String uuid, String ip) {
        String ipMsg = IPUtils.getInfoIp(ip);
        log.info("IP内容：{}",ipMsg);
        boolean itExist = blogVisitUserService.doesItExist(uuid, ip);
        if (!itExist) {
            BlogVisitUserEntity blogVisitUser = new BlogVisitUserEntity();
            JSONObject result = JSONUtil.parseObj(ipMsg);
            String country = result.getStr("country");
            String prov = result.getStr("prov");
            String city = result.getStr("city");
            String lat = result.getStr("lat");
            String lng = result.getStr("lng");
            String radius = result.getStr("radius");
            log.info("国家：{} 省：{} 城市：{} lat：{} lng：{} 半径：{}", country, prov, city, lat, lng, radius);
            blogVisitUser.setUuid(uuid).setIp(ip).setCountry(country).setProv(prov).setCity(city).setLat(lat).setLng(lng).setRadius(radius);
            blogVisitUserService.save(blogVisitUser);
            blogVisitLogService.addVisitIpAndMark(blogVisitUser.getIp(), blogVisitUser.getUuid());
        }
    }

    /**
     * 获取请求参数
     *
     * @param joinPoint 运行时连接点
     * @return 结果
     */
    private Map<String, Object> getReqParams(JoinPoint joinPoint) {
        Map<String, Object> map = new LinkedHashMap<>();
        String[] parameterNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            if (!isFilterObject(args[i])) {
                map.put(parameterNames[i], args[i]);
            }
        }
        return map;
    }

    /**
     * 考虑数据是文件、httpRequest 还是响应
     *
     * @param o 数据
     * @return 如果匹配返回 true，否则返回 false
     */
    private static boolean isFilterObject(final Object o) {
        return o instanceof HttpServletRequest || o instanceof HttpServletResponse || o instanceof MultipartFile;
    }

    /**
     * 将值写入字符串
     *
     * @param value 值
     * @return 结果
     */
    private String writeValueAsString(Object value) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }

}
