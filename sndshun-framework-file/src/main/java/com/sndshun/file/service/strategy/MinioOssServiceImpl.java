package com.sndshun.file.service.strategy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sndshun.commons.config.ResultCode;
import com.sndshun.commons.tools.Result;
import com.sndshun.commons.tools.StringUtils;
import com.sndshun.file.config.OssProperties;
import com.sndshun.file.service.OssService;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.errors.*;
import io.minio.messages.Bucket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Minio 对象存储服务
 *
 * @author mapleie
 */
@Slf4j
@Service
public class MinioOssServiceImpl implements OssService {
    /**
     * MinIO 客户端
     */
    private final MinioClient minioClient;
    /**
     * MinIO 配置类
     */
    private final OssProperties ossProperties;

    @Resource
    private ObjectMapper objectMapper;

    @Autowired
    public MinioOssServiceImpl(MinioClient minioClient, OssProperties ossProperties) {
        this.minioClient = minioClient;
        this.ossProperties = ossProperties;
    }


    @Override
    public Result<String> listBuckets() {
        try {
            log.info("MinioOssServiceImpl listBuckets start");
            List<Bucket> buckets = minioClient.listBuckets();
            log.info("MinioOssServiceImpl listBuckets end");
            return Result.ok(objectMapper.writeValueAsString(buckets));
        } catch (ServerException | InsufficientDataException | ErrorResponseException | IOException |
                 NoSuchAlgorithmException | InvalidKeyException | InvalidResponseException | XmlParserException |
                 InternalException e) {
            log.error(e.getLocalizedMessage());
            return Result.error(ResultCode.ERROR);
        }
    }

    @Override
    public Result<String> bucketExists(String bucketName) {
        try {
            log.info("MinioOssServiceImpl bucketExists start");
            boolean result = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            log.info("MinioOssServiceImpl bucketExists end");
            return result ? Result.ok(ResultCode.OSS_BUCKET_EXIST) : Result.error(ResultCode.OSS_BUCKET_IS_EXIST);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return Result.error(ResultCode.OSS_BUCKET_IS_EXIST);
        }
    }

    @Override
    public Result<String> makeBucket(String bucketName) {
        try {
            log.info("MinioOssServiceImpl makeBucket start");
            boolean result = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            StringUtils.isBooleanTrue(result);
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            log.info("MinioOssServiceImpl makeBucket end");
            return Result.ok(ResultCode.SUCCESS);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return Result.error(ResultCode.ERROR);
        }
    }
}
