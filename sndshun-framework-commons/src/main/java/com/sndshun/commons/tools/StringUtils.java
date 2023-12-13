package com.sndshun.commons.tools;

import com.sndshun.commons.config.ResultCode;
import com.sndshun.commons.exception.BusinessException;

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
            throw new RuntimeException("结果为假");
        }
    }

    public static boolean isEmpty(Object str) {
        return (str == null || "".equals(str));
    }

    /**
     * 验证是否为空（无返回结果）
     *
     * @param str        未知数
     * @param resultCode 结果值
     */
    public static void isEmpty(Object str, ResultCode resultCode) {
        if (str == null || "".equals(str)) {
            throw new BusinessException(resultCode);
        }
    }

    /**
     * 验证为真
     *
     * @param str 真
     */
    public static void isBooleanTrue(Boolean str) {
        if (str) {
            throw new RuntimeException("结果不为真");
        }
    }

    /**
     * 数字小于1
     *
     * @param num        数字
     * @param resultCode 信息
     */
    public static void isIntLessThanOne(int num, ResultCode resultCode) {
        if (num == 0) {
            throw new BusinessException(resultCode);
        }
    }
}
