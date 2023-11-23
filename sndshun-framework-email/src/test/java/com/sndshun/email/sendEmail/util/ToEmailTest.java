package com.sndshun.email.sendEmail.util;

import com.sndshun.commons.tools.FreemarkerUtils;
import com.sndshun.template.entity.TemplateModelEntity;
import com.sndshun.template.service.TemplateModelService;
import freemarker.template.TemplateException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author sndshun
 * @Date 2023/11/17 0:11
 * @Version 1.0
 * @Description: todo 整体待修改
 */
@SpringBootTest
class ToEmailTest {
    @Resource
    private ToEmail toEmail;

    @Resource
    private TemplateModelService templateModelService;



    @Test
    void sendEmail() throws Exception{

        toEmail.sendEmail();
    }

    @Test
    void test() throws IOException, TemplateException {
        String s = FreemarkerUtils.readTemplateFileFromClasspath("templates/index.html");

        Map<String, Object> map = new HashMap<>();
        map.put("name", "王大锤");
        String s1 = FreemarkerUtils.processTemplate(s, map);


        TemplateModelEntity entity = new TemplateModelEntity();
        entity.setName("欢迎来到本站");
        entity.setType(1L);
        entity.setContext(s);
        templateModelService.save(entity);

    }
}