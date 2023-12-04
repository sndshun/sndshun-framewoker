package com.sndshun.file.vo.minio;


import lombok.Data;


import java.util.Map;

/**
 * @author mapleie
 */
@Data
public class MinioBucketVo {

    /**
     * 资源标记
     */
    public String etag;

    /**
     * 对象名
     */
    public String objectName;

    /**
     * 分区日期时间
     */
    private String zonedDateTime;

    /**
     * 显示名称
     */
    private String displayName;

    /**
     * 大小(字节)
     */
    private long size;

    /**
     * 存储类
     */
    private String storageClass;

    /**
     * 是否最新的
     */
    private boolean isLatest;

    /**
     * 版本号
     */
    private String versionId;

    /**
     * 用户数据
     */
    private Map<String, String> userMetadata;

    /**
     * 子目录
     */
    private boolean isDir;

    public String getEtag() {
        return etag;
    }

    public MinioBucketVo setEtag(String etag) {
        this.etag = etag;
        return this;
    }

    public String getObjectName() {
        return objectName;
    }

    public MinioBucketVo setObjectName(String objectName) {
        this.objectName = objectName;
        return this;
    }

    public String getZonedDateTime() {
        return zonedDateTime;
    }

    public MinioBucketVo setZonedDateTime(String zonedDateTime) {
        this.zonedDateTime = zonedDateTime;
        return this;
    }

    public String getDisplayName() {
        return displayName;
    }

    public MinioBucketVo setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public long getSize() {
        return size;
    }

    public MinioBucketVo setSize(long size) {
        this.size = size;
        return this;
    }

    public String getStorageClass() {
        return storageClass;
    }

    public MinioBucketVo setStorageClass(String storageClass) {
        this.storageClass = storageClass;
        return this;
    }

    public boolean isLatest() {
        return isLatest;
    }

    public MinioBucketVo setLatest(boolean latest) {
        isLatest = latest;
        return this;
    }

    public String getVersionId() {
        return versionId;
    }

    public MinioBucketVo setVersionId(String versionId) {
        this.versionId = versionId;
        return this;
    }

    public Map<String, String> getUserMetadata() {
        return userMetadata;
    }

    public MinioBucketVo setUserMetadata(Map<String, String> userMetadata) {
        this.userMetadata = userMetadata;
        return this;
    }

    public boolean isDir() {
        return isDir;
    }

    public MinioBucketVo setDir(boolean dir) {
        isDir = dir;
        return this;
    }
}
