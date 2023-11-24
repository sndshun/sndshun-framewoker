package com.sndshun.cache;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Getter
@Setter
@ConfigurationProperties(prefix = "system.doublecache")
@Configuration
public class DoubleCacheConfig {
    private Boolean allowNull;
    private Integer init;
    private Integer max;
    private Long caffeineExpire;
    private Long redisExpire;
}
