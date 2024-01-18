package com.sndshun.blog.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sndshun.blog.service.BlogCategoryService;
import com.sndshun.blog.pojo.vo.BlogCategoryTreeVo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.List;

@SpringBootTest
class BlogCategoryServiceImplTest {
    @Resource
    private BlogCategoryService blogCategoryService;

    @Test
    void getCategoryTree() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        List<BlogCategoryTreeVo> categoryTree = blogCategoryService.getCategoryTreeCache();
        System.out.println(objectMapper.writeValueAsString(categoryTree));
    }
}