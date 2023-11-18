package com.sndshun.commons.tools;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * @Author sndshun
 * @Date 2023/11/17 7:21
 * @Version 1.0
 * @Description: 工具类
 */
public class JsoupUtils {

    /**
     * 是否 格式化 HTML 标签
     *
     * @param html [HTML全文]
     * @return {@link String}
     */
    public static String isFormatHtmlTags(String html,boolean flag) {
        Document document = Jsoup.parse(html);
        document.outputSettings().prettyPrint(flag);
        document.outputSettings().outline(flag);
        document.outputSettings().indentAmount(0);
        return document.html();
    }
}
