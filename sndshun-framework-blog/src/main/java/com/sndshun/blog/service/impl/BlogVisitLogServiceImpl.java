package com.sndshun.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sndshun.blog.entity.BlogVisitLogEntity;
import com.sndshun.blog.mapper.BlogVisitLogMapper;
import com.sndshun.blog.service.BlogVisitLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


/**
 * 访问日志表(BlogVisitLog)表服务实现类
 *
 * @author sndshun
 * @since 2023-12-03 18:37:42
 */
@Service("blogVisitLogService")
public class BlogVisitLogServiceImpl extends ServiceImpl<BlogVisitLogMapper, BlogVisitLogEntity> implements BlogVisitLogService {
    private final RedisTemplate<String, Object> restTemplate;

    @Autowired
    public BlogVisitLogServiceImpl(RedisTemplate<String, Object> restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Cacheable(cacheNames = "blog:visit", key = "#ip")
    @Override
    public String addVisitIpAndMark(String ip, String value) {
        try {
            restTemplate.opsForValue().set(ip, value, -1);
            return value;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String getValueByKey(String ip) {
        String result = (String) restTemplate.opsForValue().get(ip);
        return result;
    }
}
