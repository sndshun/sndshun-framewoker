package com.sndshun.blog.aspect;

import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.sndshun.blog.annotation.VisitLog;
import com.sndshun.blog.entity.BlogVisitLogEntity;
import com.sndshun.blog.entity.BlogVisitUserEntity;
import com.sndshun.blog.service.BlogVisitLogService;
import com.sndshun.blog.service.BlogVisitUserService;
import com.sndshun.commons.tools.IpUtils;
import com.sndshun.commons.tools.Result;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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
public class VisitLogAspect {

    private final BlogVisitLogService blogVisitLogService;
    private final BlogVisitUserService blogVisitUserService;
    private ThreadLocal<Long> currentTime = new ThreadLocal<>();

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
        String ip = IpUtils.getIpAddress(request);
        //校验访客标识码
        String identification = checkIdentification(ip, request);
        //添加日志
        handleLog(joinPoint, visitLog, request, result, times, identification, ip);
        //添加访客
        getInformationViaIp(identification, ip);
        return result;
    }

    /**
     * 根据Ip检查访客身份
     *
     * @param ip      IP
     * @param request 请求
     * @return
     */
    private String checkIdentification(String ip, HttpServletRequest request) {
        String uuid = blogVisitLogService.getValueByKey(ip);
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
        String ip = IpUtils.getIpAddress(request);
        String userAgent = request.getHeader("User-Agent");
        //根据时间戳、ip、userAgent生成UUID
        String assembleUuId = timestamp + ip + userAgent;
        String uuid = UUID.nameUUIDFromBytes(assembleUuId.getBytes()).toString();
        //添加访客标识码UUID至响应头
        response.addHeader("identification", uuid);
        //暴露自定义header供页面资源使用
        response.addHeader("Access-Control-Expose-Headers", "identification");
        blogVisitLogService.addVisitIpAndMark(ip, uuid);
        return uuid;
    }

    /**
     * 设置VisitLo对象属性
     *
     * @param joinPoint 程序连接点
     * @param visitLog  访问日志
     * @param result    结果
     * @param times     时间
     */
    private void handleLog(ProceedingJoinPoint joinPoint, VisitLog visitLog, HttpServletRequest request, Result<?> result, int times, String uuid, String ip) {
        String uri = request.getRequestURI();
        String method = request.getMethod();
        String userAgent = request.getHeader("User-Agent");
        UserAgent parse = UserAgentUtil.parse(userAgent);
        //初始化访问日志对象
        BlogVisitLogEntity blogVisitLog = new BlogVisitLogEntity();
        blogVisitLog.setUuid(uuid).setUri(uri).setMethod(method).setParam(null)
                .setBehavior(visitLog.value().getType())
                .setContent(visitLog.value().getContent())
                .setRemark(null).setIp(ip).setOs(parse.getOs().toString())
                .setBrowser(parse.getBrowser().toString()).setTimes(times)
                .setCreateTime(new Date()).setUserAgent(userAgent);
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
        HashMap<String, Object> ipMsg = IpUtils.getInformationViaIp(ip);
        boolean itExist = blogVisitUserService.doesItExist(uuid, ip);
        if (!itExist) {
            BlogVisitUserEntity blogVisitUser = new BlogVisitUserEntity();
            String country = ipMsg.get("country").toString();
            String prov = ipMsg.get("prov").toString();
            String city = ipMsg.get("city").toString();
            String lat = ipMsg.get("lat").toString();
            String lng = ipMsg.get("lng").toString();
            blogVisitUser.setUuid(uuid).setIp(ip).setCountry(country).setProv(prov).setCity(city).setLat(lat).setLng(lng);
            saveVisitUserAsync(blogVisitUser);
        }
    }

    @Async
    void saveVisitUserAsync(BlogVisitUserEntity blogVisitUser) {
        blogVisitUserService.save(blogVisitUser);
    }
}
