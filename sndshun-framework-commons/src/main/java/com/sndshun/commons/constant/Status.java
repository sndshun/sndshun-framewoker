package com.sndshun.commons.constant;

/**
 * 系统状态表示
 * @author sndshun
 * @date 2023/11/28 11:22:43
 */

public enum Status {
    NO(0),
    YES(1);

    private final int value;

    Status(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
