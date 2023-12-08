package com.sndshun.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.support.AbstractValueAdaptingCache;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * caffeine redis 双重缓存具体实现
 * @author sndshun
 * @date 2023/11/25 01:51:59
 */
@Slf4j
public class DoubleCache extends AbstractValueAdaptingCache {
    private String cacheName;
    private RedisTemplate<Object, Object> redisTemplate;
    private static Cache<Object, Object> caffeineCache;
    private DoubleCacheConfig doubleCacheConfig;
 
    protected DoubleCache(boolean allowNullValues) {
        super(allowNullValues);
    }


    public DoubleCache(String cacheName,RedisTemplate<Object, Object> redisTemplate,
                       Cache<Object, Object> caffeineCache,
                       DoubleCacheConfig doubleCacheConfig){

        super(null==doubleCacheConfig.getAllowNull()?false:doubleCacheConfig.getAllowNull());
        this.cacheName=cacheName;
        this.redisTemplate=redisTemplate;
        this.caffeineCache=caffeineCache;
        this.doubleCacheConfig=doubleCacheConfig;
    }

    @Override
    protected Object lookup(Object key) {
        // 先从caffeine中查找
        Object obj = caffeineCache.getIfPresent(key);
        if (Objects.nonNull(obj)){
            System.out.println(caffeineCache);
            System.out.println(caffeineCache.asMap());
            log.info("进程内缓存中获取 caffeine:"+obj.toString());
            return obj;
        }

        //再从redis中查找
        String redisKey=this.cacheName+":"+ key;
        try {
            obj = redisTemplate.opsForValue().get(redisKey);
            if (Objects.nonNull(obj)){
                log.info("进程外缓存中获取 redis"+obj.toString());
                caffeineCache.put(key,obj);
            }
            return obj;
        } catch (Exception e) {
            log.warn("进程外缓存连接失败");
            return null;
        }
    }

    @Override
    public String getName() {
        return this.cacheName;
    }

    @Override
    public Object getNativeCache() {
        return this;
    }

    @Override
    public <T> T get(Object key, Callable<T> valueLoader) {
        ReentrantLock lock=new ReentrantLock();
        try{
            lock.lock();//加锁
            Object obj = lookup(key);
            if (Objects.nonNull(obj)){
                return (T)obj;
            }
            //没有找到
            obj = valueLoader.call();
            put(key,obj);//放入缓存
            return (T)obj;
        }catch (Exception e){
            log.error(e.getMessage());
        }finally {
            lock.unlock();
        }
        return null;
    }

    @Override
    public void put(Object key, Object value) {

        if(!isAllowNullValues() && Objects.isNull(value)){
            //log.error("已设置为无法缓存 Null");
            throw new RuntimeException("已设置为无法缓存 Null");
        }

        // null对象只存在redis中一份就够了
        if (Objects.isNull(value)){
            //使用 toStoreValue(value) 包装，解决caffeine不能存null的问题
            //caffeineCache.put(key,toStoreValue(value));
            redisTemplate.opsForValue().set(this.cacheName+":"+key,value);
            return;
        }

        caffeineCache.put(key,value);

        String redisKey=this.cacheName +":"+ key;
        Optional<Long> expireOpt = Optional.ofNullable(doubleCacheConfig)
                .map(DoubleCacheConfig::getRedisExpire);
        if (expireOpt.isPresent()){
            redisTemplate.opsForValue().set(redisKey,toStoreValue(value),
                    expireOpt.get(), TimeUnit.SECONDS);
        }else{
            redisTemplate.opsForValue().set(redisKey,toStoreValue(value));
        }
    }

    @Override
    public void evict(Object key) {
        redisTemplate.delete(this.cacheName +":"+ key);
        caffeineCache.invalidate(key);
    }

    @Override
    public void clear() {
        Set<Object> keys = redisTemplate.keys(this.cacheName.concat(":*"));
        for (Object key : keys) {
            redisTemplate.delete(String.valueOf(key));
        }
        System.out.println(caffeineCache);
        System.out.println(caffeineCache.asMap());
        caffeineCache.invalidateAll();
    }

}