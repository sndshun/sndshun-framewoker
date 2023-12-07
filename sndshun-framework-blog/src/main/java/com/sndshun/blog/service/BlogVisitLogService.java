package com.sndshun.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.sndshun.blog.entity.BlogVisitLogEntity;

/**
 * 访问日志表(BlogVisitLog)表服务接口
 *
 * @author sndshun
 * @since 2023-12-03 18:37:42
 */
public interface BlogVisitLogService extends IService<BlogVisitLogEntity> {


    /**
     * 添加访客IP和标识
     *
     * @param ip    地址
     * @param value uuid
     * @return 真假
     */
    String addVisitIpAndMark(String ip, String value);

    /**
     * 通过key获取value
     *
     * @param key 标识
     * @return uuid
     */
    String getValueByKey(String key);
}
