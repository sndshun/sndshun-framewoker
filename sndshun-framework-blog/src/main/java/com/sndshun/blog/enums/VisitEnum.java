package com.sndshun.blog.enums;

/**
 * 访问枚举
 *
 * @author maple
 */

public enum VisitEnum {

    UNKNOWN("未知", "未知"),

    INDEX("访问页面", "首页"),

    ARCHIVE("访问页面", "归档"),
    MOMENT("访问页面", "分类"),

    TAG("访问页面", "标签"),

    FRIEND("访问页面", "友链"),

    ABOUT("访问页面", "关于我"),
    VISIT_LOG_PAGE("访客日志","分页查询"),
    ;

    /**
     * 访问类型
     */
    private String type;
    /**
     * 访问内容
     */
    private String content;

    VisitEnum(String type, String content) {
        this.type = type;
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public String getContent() {
        return content;
    }
}
