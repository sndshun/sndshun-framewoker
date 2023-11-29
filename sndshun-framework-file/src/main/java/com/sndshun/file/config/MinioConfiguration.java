package com.sndshun.file.config;


import com.sndshun.file.service.strategy.MinioOssServiceImpl;
import io.minio.MinioClient;

import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Minio配置
 *
 * @author mapleie
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass({MinioClient.class})
@EnableConfigurationProperties(OssProperties.class)
@ConditionalOnExpression("${oss.enabled}")
@ConditionalOnProperty(value = "oss.type", havingValue = "minio")
public class MinioConfiguration {


    @Bean
    @ConditionalOnMissingBean(MinioClient.class)
    public MinioClient minioClient(OssProperties ossProperties) {
        return MinioClient.builder().endpoint(ossProperties.getEndpoint()).credentials(ossProperties.getAccessKey(), ossProperties.getSecretKey()).build();
    }


    @Bean
    @ConditionalOnBean({MinioClient.class})
    @ConditionalOnMissingBean(MinioOssServiceImpl.class)
    public MinioOssServiceImpl MinioOssServiceImpl(MinioClient minioClient, OssProperties ossProperties) {
        return new MinioOssServiceImpl(minioClient, ossProperties);
    }
}
