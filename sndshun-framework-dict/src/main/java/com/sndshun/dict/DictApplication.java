package com.sndshun.dict;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author sndshun
 * @Date 2023/11/17 9:29
 * @Version 1.0
 * @Description: 字典
 */
@SpringBootApplication(scanBasePackages = "com.sndshun")
@MapperScan("com.sndshun.dict.mapper")
public class DictApplication {
    public static void main(String[] args) {
        SpringApplication.run(DictApplication.class, args);
    }
}
