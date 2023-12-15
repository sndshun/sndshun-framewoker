package com.sndshun.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sndshun.blog.entity.BlogVisitLogEntity;
import com.sndshun.blog.mapper.BlogVisitLogMapper;
import com.sndshun.blog.service.BlogVisitLogService;
import com.sndshun.blog.service.BlogVisitUserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final BlogVisitUserService blogVisitUserService;

    @Autowired
    public BlogVisitLogServiceImpl(RedisTemplate<String, Object> restTemplate, BlogVisitUserService blogVisitUserService) {
        this.restTemplate = restTemplate;
        this.blogVisitUserService = blogVisitUserService;
    }

    @Override
    public String addVisitIpAndMark(String ip, String value) {
        try {
            restTemplate.opsForValue().set("ip:" + ip, value);
            return value;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String getValueByKey(String ip) {
        String uuid = (String) restTemplate.opsForValue().get("ip:" + ip);
        if (uuid == null) {
            uuid = getValueByKeyDb(ip);
        }
        return uuid;
    }

    /**
     * DB
     *
     * @param ip ip
     * @return 识别码
     */
    private String getValueByKeyDb(String ip) {
        return blogVisitUserService.getUuidByiP(ip);
    }
}
