package com.sndshun.cache;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * 自定义 双重缓存管理器
 * @author sndshun
 * @date 2023/11/25 02:42:49
 */

public class DoubleCacheManager implements CacheManager {
    Map<String, Cache> cacheMap = new ConcurrentHashMap<>();
    private RedisTemplate<Object, Object> redisTemplate;
    private DoubleCacheConfig dcConfig;
 
    public DoubleCacheManager(RedisTemplate<Object, Object> redisTemplate,
                              DoubleCacheConfig doubleCacheConfig) {
        this.redisTemplate = redisTemplate;
        this.dcConfig = doubleCacheConfig;
    }

    @Override
    public Cache getCache(String name) {
        Cache cache = cacheMap.get(name);
        if (Objects.nonNull(cache)) {
            return cache;
        }

        cache = new DoubleCache(name, redisTemplate, createCaffeineCache(), dcConfig);
        Cache oldCache = cacheMap.putIfAbsent(name, cache);
        return oldCache == null ? cache : oldCache;
    }

    @Override
    public Collection<String> getCacheNames() {
        return cacheMap.keySet();
    }

    /**
     * 构建 caffeine 缓存
     * @return
     */
    private com.github.benmanes.caffeine.cache.Cache createCaffeineCache(){
        Caffeine<Object, Object> caffeineBuilder = Caffeine.newBuilder();
        Optional<DoubleCacheConfig> dcConfigOpt = Optional.ofNullable(this.dcConfig);
        dcConfigOpt.map(DoubleCacheConfig::getInit)
                .ifPresent(init->caffeineBuilder.initialCapacity(init));
        dcConfigOpt.map(DoubleCacheConfig::getMax)
                .ifPresent(max->caffeineBuilder.maximumSize(max));
        dcConfigOpt.map(DoubleCacheConfig::getCaffeineExpire)
                .ifPresent(eaw->caffeineBuilder.expireAfterWrite(eaw, TimeUnit.SECONDS));
        return caffeineBuilder.build();
    }
}