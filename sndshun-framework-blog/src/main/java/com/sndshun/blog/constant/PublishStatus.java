package com.sndshun.blog.constant;

/** 文章状态
 * @author sndshun
 * @date 2023/12/02 10:28:25
 */

public enum PublishStatus {
    UNPUBLISHED(0, "未发布"),
    PUBLISHED(1, "已发布"),
    DRAFT(2, "草稿");

    private final int code;
    private final String description;

    PublishStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static PublishStatus getByCode(int code) {
        for (PublishStatus status : values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid PublishStatus code: " + code);
    }
}
