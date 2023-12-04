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
     * 获取桶中某个文件，此操作需要对此Object具有读权限。
     *
     * @param bucketName  桶名称
     * @param ossFilePath 对象存储地址
     * @return 结果
     */
    InputStream getBucketObject(String bucketName, String ossFilePath);

    /**
     * 查询桶的对象信息
     *
     * @param bucketName 桶名
     * @param recursive  是否递归查询
     * @return 结果
     */
    Result<String> getBucketObjectMsg(String bucketName, boolean recursive);

    /**
     * 初始化默认存储桶
     */
    void initDefaultBucket();
}
