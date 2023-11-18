package com.sndshun.template;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author sndshun
 * @Date 2023/11/17 6:49
 * @Version 1.0
 * @Description:
 */
@SpringBootApplication(scanBasePackages = "com.sndshun")
@MapperScan("com.sndshun.template.mapper")
public class TemplateApplication {
    public static void main(String[] args) {
        SpringApplication.run(TemplateApplication.class, args);
    }
}
