package com.sndshun.template.service;

import com.sndshun.commons.tools.FreemarkerUtils;
import com.sndshun.commons.tools.StringUtils;
import com.sndshun.template.entity.TemplateModelEntity;
import freemarker.template.TemplateException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author sndshun
 * @Date 2023/11/17 7:12
 * @Version 1.0
 * @Description:
 */
@SpringBootTest
class TemplateModelServiceTest {
    @Resource
    private TemplateModelService templateModelService;

    @Test
    void test() throws IOException, TemplateException {
        String s = FreemarkerUtils.readTemplateFileFromClasspath("templates/index.html");

        Map<String, Object> map = new HashMap<>();
        map.put("name", "王大锤");
        String s1 = FreemarkerUtils.processTemplate(s, map);


        TemplateModelEntity entity = new TemplateModelEntity();
        entity.setName("欢迎来到本站");
        entity.setType(1L);
        entity.setContext(StringUtils.removeSpaceAndNewline(s));
        templateModelService.save(entity);
    }

    public static void main(String[] args) throws IOException {
        String s = FreemarkerUtils.readTemplateFileFromClasspath("templates/index.html");
        // 仅移除空格和换行，保留标签之间的空格
        System.out.println(StringUtils.removeSpaceAndNewline(s));
    }


}