package com.sndshun.blog.constant;

/**博客菜单类型
 * @author sndshun
 * @date 2023/12/08 10:42:22
 */

public enum MenuType {
    BLOGMENU(0,"博客菜单"),
    BLOGADMINMENU(1,"博客管理菜单");

    private final int code;
    private final String description;

    MenuType(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
