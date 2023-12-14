package com.sndshun.blog.task;

import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author maple
 *
 * DisallowConcurrentExecution 作业不并发
 */
@Slf4j
@DisallowConcurrentExecution
@Component
public class TestTask implements Job {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Override
    public void execute(JobExecutionContext context) {
        log.info("博客定时任务测试========================开始");
        redisTemplate.opsForValue().increment("test", 1);
        log.info("博客定时任务测试========================结束");
    }
}
