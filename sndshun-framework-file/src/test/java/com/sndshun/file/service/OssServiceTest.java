package com.sndshun.file.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sndshun.commons.tools.Result;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OssServiceTest {


    @Resource
    private OssService ossService;
    private ObjectMapper objectMapper;


    public OssServiceTest() {
        objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @Test
    void listBuckets() throws JsonProcessingException {
        Result<String> stringResult = ossService.listBuckets();
        String res = objectMapper.writeValueAsString(stringResult);
        System.out.println(res);
    }

    @Test
    void bucketExists() throws JsonProcessingException {
        Result<String> stringResult = ossService.bucketExists("test");
        String res = objectMapper.writeValueAsString(stringResult);
        System.out.println(res);
    }

    @Test
    void makeBucket() throws JsonProcessingException {
        Result<String> stringResult = ossService.makeBucket("test");
        String res = objectMapper.writeValueAsString(stringResult);
        System.out.println(res);
    }

    @Test
    void removeBucket() throws JsonProcessingException {
        Result<String> stringResult = ossService.removeBucket("test");
        String res = objectMapper.writeValueAsString(stringResult);
        System.out.println(res);
    }

    @Test
    void upload() throws IOException {
        File img = new File("D:/test/gp.webp");
        Result<String> stringResult = ossService.upload(Files.newInputStream(img.toPath()), "test", "gp.webp");
        String res = objectMapper.writeValueAsString(stringResult);
        System.out.println(res);
    }

    @Test
    void initDefaultBucket() {
       ossService.initDefaultBucket();
    }
}