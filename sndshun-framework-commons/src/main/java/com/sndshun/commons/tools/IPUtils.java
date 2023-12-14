package com.sndshun.commons.tools;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;


/**
 * Ip 工具类
 *
 * @author maple
 */
public class IPUtils {

    /**
     * 通过ip获取信息
     *
     * @param ip ip
     * @return ip的信息
     */
    public static String getInfoIp(String ip) {
        String url = "https://qifu-api.baidubce.com/ip/geo/v1/district?ip=" + ip;
        HttpResponse execute = HttpRequest.get(url).execute();
        if (execute.isOk()) {
            return JSONUtil.parseObj(execute.body()).get("data").toString();
        }
        return null;
    }
}
