package com.sndshun.commons.tools;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Scanner;

/**
 * FreeMarker 实用程序
 *
 * @author sndshun
 * @date 2023/11/17
 */
public class FreemarkerUtils {
    private static Configuration configuration;

    static {
        // 初始化FreeMarker配置
        configuration = new Configuration(Configuration.VERSION_2_3_32);

        // 使用StringTemplateLoader，允许从字符串中加载模板内容
        configuration.setTemplateLoader(new StringTemplateLoader());
        configuration.setDefaultEncoding("UTF-8");
    }


    /**
     * 从Classpath下读取资源文件并转换为字符串
     *
     * @param filePath 文件路径，相对于Classpath
     * @return 文件内容字符串
     * @throws IOException IO异常
     */
    public static String readTemplateFileFromClasspath(String filePath) throws IOException {
        try (InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath)) {
            if (inputStream != null) {
                try (Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8.name())) {
                    return scanner.useDelimiter("\\A").next();
                }
            } else {
                throw new IOException("File not found: " + filePath);
            }
        }
    }

    /**
     * 使用FreeMarker渲染模板并返回结果字符串
     *
     * @param templateContent 模板内容字符串
     * @param model           数据模型
     * @return 渲染后的字符串
     * @throws IOException       IO异常
     * @throws TemplateException FreeMarker模板异常
     */
    public static String processTemplate(String templateContent, Map<String, Object> model)
            throws IOException, TemplateException {
        StringTemplateLoader stringLoader = (StringTemplateLoader) configuration.getTemplateLoader();
        String templateName = "dynamicTemplate"; // 使用一个固定的模板名称

        // 将模板内容添加到StringTemplateLoader中
        stringLoader.putTemplate(templateName, templateContent);

        Template template = configuration.getTemplate(templateName);
        StringWriter writer = new StringWriter();
        template.process(model, writer);
        return writer.toString();
    }


}