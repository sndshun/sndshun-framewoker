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
     * 断点下载
     *
     * @param bucketName 桶名称
     * @param fileName   文件名称
     * @param offset     起始字节的位置
     * @param length     要读取的长度
     * @return 二进制流
     */
    InputStream breakpointDownload(String bucketName, String fileName, long offset, long length);

    /**
     * 分片上传
     *
     * @param file        分片文件
     * @param currIndex   当前文件的分片索引
     * @param totalPieces 切片总数（对于同一个文件，请确保切片总数始终不变）
     * @param md5         md5 整体文件MD5
     * @return 剩余未上传的文件索引集合
     */
    Result<String> shardUpload(InputStream file, Integer currIndex, Integer totalPieces, String md5);

    /**
     * 合并分片  并放到指定目录
     *
     * @param bucketName  目标文件桶名
     * @param targetName  目标文件名（含完整路径）
     * @param totalPieces 切片总数（对于同一个文件，请确保切片总数始终不变）
     * @param md5         文件md5
     * @return minio原生对象，记录了文件上传信息
     */
    Result<String> shardMerge(String bucketName, String targetName, Integer totalPieces, String md5);

    /**
     * 初始化默认存储桶
     */
    void initDefaultBucket();
}
