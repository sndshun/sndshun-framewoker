package com.sndshun.commons.tools;

import java.util.regex.Pattern;

/**
 * @Author sndshun
 * @Date 2023/11/17 8:02
 * @Version 1.0
 * @Description: 字符串工具
 */
public class StringUtils {

    private static final Pattern SPACE_NEWLINE_PATTERN = Pattern.compile("\\s+");

    /**
     * 删除空格和换行符
     *
     * @param html [HTML全文]
     * @return {@link String}
     */
    public static String removeSpaceAndNewline(String html) {
        return SPACE_NEWLINE_PATTERN.matcher(html).replaceAll(" ");
    }

    /**
     * 验证为假
     *
     * @param str 真假
     */
    public static void isBooleanFalse(Boolean str) {
        if (!str) {
            throw new RuntimeException();
        }
    }

    public static boolean isEmpty(Object str) {
        return (str == null || "".equals(str));
    }

    /**
     * 验证为真
     *
     * @param str 真
     */
    public static void isBooleanTrue(Boolean str) {
        if (str) {
            throw new RuntimeException();
        }
    }
}
