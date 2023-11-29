package com.sndshun.file.service;


import com.sndshun.commons.tools.Result;

import java.io.InputStream;


/**
 * 文件服务接口
 *
 * @author mapleie
 */
public interface OssService {

    /**
     * 查询所有存储桶
     *
     * @return Bucket 集合
     */
    Result<String> listBuckets();

    /**
     * 桶是否存在
     *
     * @param bucketName 桶名
     * @return 是否存在
     */
    Result<String> bucketExists(String bucketName);

    /**
     * 创建存储桶
     *
     * @param bucketName 桶名
     * @return 成功与否
     */
    Result<String> makeBucket(String bucketName);

    /**
     * 删除一个空桶
     *
     * @param bucketName 桶名称
     * @return 成功与否
     */
    Result<String> removeBucket(String bucketName);

    /**
     * 上传文件
     *
     * @param inputStream      流
     * @param originalFileName 原始文件名
     * @param bucketName       桶名
     * @return OssFile
     */
    Result<String> upload(InputStream inputStream, String bucketName, String originalFileName);

    /**
     * 初始化默认存储桶
     */
    void initDefaultBucket();
}
