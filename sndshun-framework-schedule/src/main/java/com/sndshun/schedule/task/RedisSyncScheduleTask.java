package com.sndshun.schedule.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;


/**
 * @author maple
 */
@Component("RedisSyncScheduleTask")
public class RedisSyncScheduleTask {

    private final RedisTemplate redisTemplate;

    @Autowired
    public RedisSyncScheduleTask(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 定时更新文章浏览量
     */
    void updateArticleViewsRegularly() {

    }
}
