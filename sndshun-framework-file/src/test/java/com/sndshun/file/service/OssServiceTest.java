package com.sndshun.file.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.common.base.Preconditions;
import com.sndshun.commons.tools.Result;
import javafx.util.Pair;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

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

    @Test
    void getBucketObject() {
        InputStream test = ossService.getBucketObject("test", "files/2023-11-29/18747e3f-ae4c-4e75-a8bb-0a0b5e077a0d/gp.webp");
        System.out.println(test.toString());
    }

    @Test
    void getBucketObjectMsg() {
        Result<String> test = ossService.getBucketObjectMsg("test", true);
        System.out.println(test.getData());
    }
}