package com.sndshun.file.pojo.vo.minio;

import lombok.Data;

/**
 * 桶
 *
 * @author mapleie
 */
@Data
public class BucketVo {
    /**
     * 桶名称
     */
    private String bucketName;

    /**
     * 创建时间
     */
    private String createTime;

    public String getBucketName() {
        return bucketName;
    }

    public BucketVo setBucketName(String bucketName) {
        this.bucketName = bucketName;
        return this;
    }

    public String getCreateTime() {
        return createTime;
    }

    public BucketVo setCreateTime(String createTime) {
        this.createTime = createTime;
        return this;
    }
}
