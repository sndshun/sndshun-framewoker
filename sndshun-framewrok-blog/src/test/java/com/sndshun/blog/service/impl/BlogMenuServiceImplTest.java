package com.sndshun.blog.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sndshun.blog.service.BlogMenuService;
import com.sndshun.blog.vo.BlogMenuTreeVo;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@MapperScan("com.sndshun.blog.mapper")
class BlogMenuServiceImplTest {
    @Resource
    private BlogMenuService blogMenuService;

    @Test
    void blogMenuTreeAll() throws JsonProcessingException {

        List<BlogMenuTreeVo> blogMenuTreeVoList = blogMenuService.blogMenuTreeAll();

        ObjectMapper om=new ObjectMapper();
        om.enable(SerializationFeature.INDENT_OUTPUT); //格式化输出
        String s = om.writeValueAsString(blogMenuTreeVoList);
        System.out.println(s);

    }

    @Test
    void blogMenuTree() throws JsonProcessingException {
        List<BlogMenuTreeVo> blogMenuTreeVoList = blogMenuService.blogMenuTree();

        ObjectMapper om=new ObjectMapper();
        om.enable(SerializationFeature.INDENT_OUTPUT); //格式化输出
        String s = om.writeValueAsString(blogMenuTreeVoList);
        System.out.println(s);
    }
}