package com.sndshun.file;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author mapleie
 */
@SpringBootApplication(scanBasePackages = "com.sndshun")
@MapperScan("com.sndshun.file.mapper")
public class OssApplication {
    public static void main(String[] args) {
        SpringApplication.run(OssApplication.class, args);
    }
}
