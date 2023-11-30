package com.sndshun.api;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;

@OpenAPIDefinition(info = @Info(title = "字典查询服务",version = "1.0"))
public class DictApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(DictApiApplication.class, args);
    }
}