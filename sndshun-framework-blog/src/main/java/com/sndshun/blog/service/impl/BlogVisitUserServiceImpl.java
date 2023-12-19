package com.sndshun.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sndshun.blog.mapper.BlogVisitUserMapper;
import com.sndshun.blog.entity.BlogVisitUserEntity;
import com.sndshun.blog.service.BlogVisitUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * 访客用户(BlogVisitUser)表服务实现类
 *
 * @author sndshun
 * @since 2023-12-11 13:33:07
 */
@Service("blogVisitUserService")
public class BlogVisitUserServiceImpl extends ServiceImpl<BlogVisitUserMapper, BlogVisitUserEntity> implements BlogVisitUserService {
    private final RedisTemplate<String, Object> restTemplate;


    @Autowired
    public BlogVisitUserServiceImpl(RedisTemplate<String, Object> restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public boolean doesItExist(String uuid, String ip) {
        String result = (String) restTemplate.opsForValue().get("ip:" + ip);
        if (result == null) {
            return doesItExistDb(uuid, ip);
        } else {
            return true;
        }
    }

    @Override
    public String getUuidByiP(String ip) {
        LambdaQueryWrapper<BlogVisitUserEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BlogVisitUserEntity::getIp, ip);
        BlogVisitUserEntity blogVisitUser = super.baseMapper.selectOne(wrapper);
        return null!=blogVisitUser?blogVisitUser.getUuid():null;
    }

    private boolean doesItExistDb(String uuid, String ip) {
        LambdaQueryWrapper<BlogVisitUserEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(BlogVisitUserEntity::getUuid, uuid);
        lambdaQueryWrapper.eq(BlogVisitUserEntity::getIp, ip);
        return this.baseMapper.exists(lambdaQueryWrapper);
    }
}
