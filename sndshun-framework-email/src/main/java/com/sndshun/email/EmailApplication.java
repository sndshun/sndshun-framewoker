package com.sndshun.email;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author sndshun
 * @Date 2023/11/15 18:46
 * @Version 1.0
 * @Description: 邮箱服务
 */
@SpringBootApplication(scanBasePackages = "com.sndshun")
@MapperScan("com.sndshun.email.mapper")
public class EmailApplication {
    public static void main(String[] args) {
        SpringApplication.run(EmailApplication.class, args);
    }
}