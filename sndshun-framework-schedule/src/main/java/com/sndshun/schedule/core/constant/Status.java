package com.sndshun.schedule.core.constant;

/**
 * 系统状态表示
 * @author sndshun
 * @date 2023/11/28 11:22:43
 */

public enum Status {
    PAUSE(0, "暂停中"),
    WAIT(1, "等待运行"),
    RUNNING(2, "运行中"),
    ERROR(4, "异常");

    private final int value;

    private final String description;

    Status(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }
}
