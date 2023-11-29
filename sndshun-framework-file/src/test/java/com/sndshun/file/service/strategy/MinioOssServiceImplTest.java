package com.sndshun.file.service.strategy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sndshun.commons.tools.Result;
import com.sndshun.file.service.OssService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MinioOssServiceImplTest {

    @Resource
    private OssService ossService;
    private ObjectMapper objectMapper;


    public MinioOssServiceImplTest() {
        objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @Test
    void listBuckets() throws JsonProcessingException {
        Result<String> stringResult = ossService.listBuckets();
        String s = objectMapper.writeValueAsString(stringResult);
        System.out.println(s);
    }

    @Test
    void bucketExists() throws JsonProcessingException {
        Result<String> stringResult = ossService.bucketExists("test");
        String s = objectMapper.writeValueAsString(stringResult);
        System.out.println(s);
    }

    @Test
    void makeBucket() throws JsonProcessingException {
        Result<String> stringResult = ossService.makeBucket("test");
        String s = objectMapper.writeValueAsString(stringResult);
        System.out.println(s);
    }
}