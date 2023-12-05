package com.sndshun.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@EnableCaching   //开启缓存功能，作用于缓存配置类上或者作用于springboot启动类上
@Configuration
public class RedisConfig extends CachingConfigurerSupport {
    /** 默认日期时间格式 */
    public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    /** 默认日期格式 */
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    /** 默认时间格式 */
    public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";
    /**
     * 创建一个RedisTemplate实例，用于操作Redis数据库。
     * 其中，redisTemplate是一个泛型为<String, Object>的模板对象，可以存储键值对数据；
     * @param factory   factory是一个Redis连接工厂对象，用于建立与Redis服务器的连接
     * @return
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        //创建一个字符串序列化器对象，用于将字符串类型的数据转换成二进制格式存储到Redis中。
        RedisSerializer<String> redisSerializer = new StringRedisSerializer();
        //创建一个字符串序列化器对象，用于将字符串类型的数据转换成二进制格式存储到Redis中。
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        //创建一个字符串序列化器对象，用于将字符串类型的数据转换成二进制格式存储到Redis中。
        ObjectMapper om = new ObjectMapper();
        //支持序列化反序列化 java.time  LocalDateTime，LocalDate，LocalTime 并添加支持格式
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT)));
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT)));
        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern(DEFAULT_TIME_FORMAT)));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT)));
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT)));
        javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern(DEFAULT_TIME_FORMAT)));
        om.registerModule(javaTimeModule);
        //设置ObjectMapper对象的属性访问器可见性，使其能够访问所有的属性。
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        //启用默认类型识别，避免在序列化过程中出现类型错误。
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        //忽略null属性
        om.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        //将ObjectMapper对象设置为JSON序列化器的属性访问器。
        jackson2JsonRedisSerializer.setObjectMapper(om);
        //将ObjectMapper对象设置为JSON序列化器的属性访问器。
        template.setConnectionFactory(factory);
        //key序列化方式,将ObjectMapper对象设置为JSON序列化器的属性访问器。
        template.setKeySerializer(redisSerializer);
        //value序列化,将ObjectMapper对象设置为JSON序列化器的属性访问器。
        template.setValueSerializer(jackson2JsonRedisSerializer);
        //value hashmap序列化,将ObjectMapper对象设置为JSON序列化器的属性访问器。
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        return template;
    }

    /**
     *  创建一个CacheManager实例，用于管理缓存。
     *  其中，cacheManager是一个缓存管理器对象，用于管理缓存的生命周期和策略等；
     * @param factory
     * @return
     */
    @ConditionalOnProperty(prefix = "system",name = "cache",havingValue = "redis")
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory factory) {
        //第一个序列化器用于将字符串类型的数据转换为二进制格式，第二个序列化器用于将Java对象序列化为JSON格式。
        RedisSerializer<String> redisSerializer = new StringRedisSerializer();
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        //解决查询缓存转换异常的问题
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        // 配置序列化（解决乱码的问题）,过期时间14天
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofDays(14))
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer))
                //禁用缓存空值
                .disableCachingNullValues();
        RedisCacheManager cacheManager = RedisCacheManager.builder(factory)
                .cacheDefaults(config)
                .build();
        return cacheManager;
    }


    /**
     * 配置redis查询失败时处理方法 不影响继续向数据库查询
     * @return {@link CacheErrorHandler }
     * @author sndshun
     * @date 2023/11/29 04:47:34
     */
    @Override
    @Bean
    public CacheErrorHandler errorHandler() {
        CacheErrorHandler cacheErrorHandler = new CacheErrorHandler() {

            @Override
            public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {
                log.error("redis异常：key=[{}]", key, exception);
            }

            @Override
            public void handleCachePutError(RuntimeException exception, Cache cache, Object key, Object value) {
                log.error("redis异常：key=[{}]", key, exception);
            }

            @Override
            public void handleCacheEvictError(RuntimeException exception, Cache cache, Object key) {
                log.error("redis异常：key=[{}]", key, exception);
            }

            @Override
            public void handleCacheClearError(RuntimeException exception, Cache cache) {
                log.error("redis异常：", exception);
            }
        };

        return cacheErrorHandler;
    }
}