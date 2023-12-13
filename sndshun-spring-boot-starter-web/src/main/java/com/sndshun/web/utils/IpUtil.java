package com.sndshun.web.utils;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;

/**
 * Ip工具
 *
 * @author maple
 */
public class IpUtil {


    /**
     * 通过ip获取信息
     *
     * @param ip ip
     * @return ip的信息
     */
    public static HashMap<String, Object> getInformationViaIp(String ip) {
        HashMap<String, Object> map = new HashMap<>(17);
        long startTime = System.currentTimeMillis();
        String url = "https://qifu-api.baidubce.com/ip/geo/v1/district?ip=" + ip;
        HttpResponse execute = HttpRequest.get(url).execute();
        if (execute.isOk()) {
            Object data = JSONUtil.parseObj(execute.body()).get("data");
            JSONObject result = JSONUtil.parseObj(data);
            map.put("continent", result.getStr("continent"));
            map.put("country", result.getStr("country"));
            map.put("zipcode", result.getStr("zipcode"));
            map.put("timezone", result.getStr("timezone"));
            map.put("accuracy", result.getStr("accuracy"));
            map.put("owner", result.getStr("owner"));
            map.put("isp", result.getStr("isp"));
            map.put("source", result.getStr("source"));
            map.put("areacode", result.getStr("areacode"));
            map.put("adcode", result.getStr("adcode"));
            map.put("asnumber", result.getStr("asnumber"));
            map.put("lat", result.getStr("lat"));
            map.put("lng", result.getStr("lng"));
            map.put("radius", result.getStr("radius"));
            map.put("prov", result.getStr("prov"));
            map.put("city", result.getStr("city"));
            map.put("district", result.getStr("district"));
            return map;
        }
        long endTime = System.currentTimeMillis();
        System.out.println("耗时：" + (endTime - startTime));
        return null;
    }

    /**
     * 获取ip
     *
     * @param request ip
     * @return ip
     */
//    public static String getIpAddress(HttpServletRequest request) {
//        String ip = request.getHeader("X-Real-IP");
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("x-forwarded-for");
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("Proxy-Client-IP");
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("WL-Proxy-Client-IP");
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("HTTP_CLIENT_IP");
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getRemoteAddr();
//            if ("127.0.0.1".equals(ip) || "0:0:0:0:0:0:0:1".equals(ip)) {
//                //根据网卡取本机配置的IP
//                InetAddress inet = null;
//                try {
//                    inet = InetAddress.getLocalHost();
//                } catch (UnknownHostException e) {
//                    System.out.println(e.getMessage());
//                }
//                ip = inet.getHostAddress();
//            }
//        }
//        return ip;
//    }
}
