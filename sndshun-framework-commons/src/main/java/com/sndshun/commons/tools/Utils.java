package com.sndshun.commons.tools;

/**
 * 公共工具类
 *
 * @author maple
 */
public class Utils {

    /**
     * 截取字符串
     *
     * @param str   原始字符串
     * @param start 起始位置
     * @param end   结束位置
     * @return 结果
     */
    public static String substring(String str, int start, int end) {
        if (str == null || "".equals(str)) {
            return "";
        }
        if (start < 0 || end < 0) {
            return str;
        }
        if (end > str.length()) {
            end = str.length();
        }
        if (start >= end) {
            return "";
        }
        return str.substring(start, end);
    }
}
