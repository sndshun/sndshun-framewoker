package com.sndshun.schedule.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;


/**
 *
 * @author maple
 */
@Component("RedisSyncScheduleTask")
public class RedisSyncScheduleTask {

    private final RedisTemplate redisTemplate;

    @Autowired
    public RedisSyncScheduleTask(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void test() {
        System.out.println("测试定时任务");
    }
}
