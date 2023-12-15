package com.sndshun.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sndshun.blog.entity.BlogVisitUserEntity;

/**
 * 访客用户(BlogVisitUser)表服务接口
 *
 * @author sndshun
 * @since 2023-12-11 13:33:07
 */
public interface BlogVisitUserService extends IService<BlogVisitUserEntity> {


    /**
     * 验证是否存在
     *
     * @param uuid 访客识别码
     * @param ip   IP
     * @return 真假
     */
    boolean doesItExist(String uuid, String ip);

    /**
     * 通过ip获取访客识别码
     *
     * @param ip ip
     * @return uuid
     */
    String getUuidByiP(String ip);
}
