package com.sndshun.file.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 对象存储类型
 *
 * @author mapleie
 */

@Getter
@AllArgsConstructor
public enum OssType {
    /**
     * Minio 对象存储
     */
    MINIO("minio", 1),

    /**
     * 华为 OBS
     */
    OBS("obs", 2),

    /**
     * 七牛 OSS
     */
    QINIU("qiniu", 3),
    /**
     * 腾讯 COS
     */
    COS("tencent", 4),

    /**
     * 阿里巴巴 OSS
     */
    ALIBABA("alibaba", 5),
    ;

    /**
     * 名称
     */
    final String name;
    /**
     * 类型
     */
    final int type;
}
