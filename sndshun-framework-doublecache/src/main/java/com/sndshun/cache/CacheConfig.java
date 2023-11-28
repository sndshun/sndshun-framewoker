package com.sndshun.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

@Configuration
public class CacheConfig {
    @Resource
    private RedisTemplate<Object,Object> redisTemplate;
    @Resource
    private DoubleCacheConfig doubleCacheConfig;


    /**
     * 注册双重缓存管理器
     * todo 分布式缓存下通知进程内缓存更新数据   https://blog.csdn.net/xiyang_1990/article/details/129136037
     * @return {@link DoubleCacheManager }
     * @author sndshun
     * @date 2023/11/25 03:05:09
     */
    @ConditionalOnProperty(prefix = "system",name = "cache",havingValue = "doublecache")
    @Bean
    public DoubleCacheManager cacheManager(){
        return new DoubleCacheManager(redisTemplate,doubleCacheConfig);
    }
}
