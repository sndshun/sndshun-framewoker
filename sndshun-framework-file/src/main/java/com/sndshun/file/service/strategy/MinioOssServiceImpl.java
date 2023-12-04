package com.sndshun.file.service.strategy;

import cn.hutool.core.util.StrUtil;
import com.qcloud.cos.utils.Jackson;
import com.sndshun.commons.config.ResultCode;
import com.sndshun.commons.tools.DateUtils;
import com.sndshun.commons.tools.Result;
import com.sndshun.commons.tools.StringUtils;
import com.sndshun.file.config.OssProperties;
import com.sndshun.file.convert.MinioConvert;
import com.sndshun.file.entity.OssFile;
import com.sndshun.file.service.OssService;
import com.sndshun.file.util.FileUtils;
import com.sndshun.file.vo.minio.BucketVo;
import com.sndshun.file.vo.minio.MinioBucketVo;
import io.minio.*;
import io.minio.errors.*;
import io.minio.messages.Bucket;
import io.minio.messages.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

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
            List<BucketVo> bucketVos = new ArrayList<>();
            buckets.forEach(item -> {
                Date date = DateUtils.toDate(item.creationDate());
                String creationDate = DateUtils.dateToStringYyyyMMddHHMmSs(date);
                BucketVo bucketVo = new BucketVo();
                bucketVo.setBucketName(item.name()).setCreateTime(creationDate);
                bucketVos.add(bucketVo);
            });
            String result = Jackson.toJsonString(bucketVos);
            log.info("MinioOssServiceImpl listBuckets end");
            return Result.ok(result);
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
            createBucket(bucketName);
            log.info("MinioOssServiceImpl makeBucket end");
            return Result.ok(ResultCode.SUCCESS);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return Result.error(ResultCode.ERROR);
        }
    }

    @Override
    public Result<String> removeBucket(String bucketName) {
        try {
            log.info("MinioOssServiceImpl makeBucket start");
            RemoveBucketArgs removeBucketArgs = RemoveBucketArgs.builder().bucket(bucketName).build();
            minioClient.removeBucket(removeBucketArgs);
            log.info("MinioOssServiceImpl makeBucket end");
            return Result.ok(ResultCode.OSS_DEL_OK);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            log.info("MinioOssServiceImpl makeBucket end");
            return Result.error(ResultCode.OSS_DEL_ERROR);
        }
    }

    @Override
    public Result<String> upload(InputStream inputStream, String bucketName, String originalFileName) {
        String uuidFileName = FileUtils.generateOssUuidFileName(originalFileName);
        try {
            if (StrUtil.isEmpty(bucketName)) {
                bucketName = ossProperties.getDefaultBucketName();
            }
            uploadFileStream(bucketName, originalFileName, inputStream);
            OssFile ossFile = new OssFile(uuidFileName, originalFileName);
            return Result.ok(ResultCode.OSS_UPLOAD_OK, ossFile.toString());
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return Result.error(ResultCode.OSS_UPLOAD_ERROR);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                    log.error(e.getLocalizedMessage());
                }
            }
        }
    }

    @Override
    public InputStream getBucketObject(String bucketName, String ossFilePath) {
        log.info("MinioOssServiceImpl getBucketObject start");
        try {
            GetObjectArgs getObjectArgs = GetObjectArgs.builder().bucket(bucketName).object(ossFilePath).build();
            GetObjectResponse object = minioClient.getObject(getObjectArgs);
            log.info("MinioOssServiceImpl getBucketObject end");
            return object;
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public Result<String> getBucketObjectMsg(String bucketName, boolean recursive) {
        log.info("MinioOssServiceImpl getBucketObjectMsg start");
        try {
            ListObjectsArgs listObjectsArgs = ListObjectsArgs.builder().bucket(bucketName).recursive(recursive).build();
            Iterable<io.minio.Result<Item>> itemLists = minioClient.listObjects(listObjectsArgs);
            Set<MinioBucketVo> minioBucketVos = new HashSet<>();
            itemLists.forEach(items -> {
                try {
                    Item item = items.get();
                    MinioBucketVo minioBucketVo = MinioConvert.itemToMinioBucketVo(item);
                    minioBucketVos.add(minioBucketVo);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            String result = Jackson.toJsonString(minioBucketVos);
            log.info("MinioOssServiceImpl getBucketObjectMsg end");
            return minioBucketVos.size() > 0 ? Result.ok(result) : Result.error(ResultCode.OSS_SEARCH_BUCKET_ERROR);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            log.info("MinioOssServiceImpl getBucketObjectMsg end");
            return Result.error(ResultCode.OSS_SEARCH_BUCKET_ERROR);
        }
    }

    @Override
    @PostConstruct
    public void initDefaultBucket() {
        try {
            log.info("MinioOssServiceImpl initDefaultBucket start");
            String defaultBucketName = ossProperties.getDefaultBucketName();
            boolean exist = bucketExist(defaultBucketName);
            StringUtils.isBooleanTrue(exist);
            /*不存在手动创建*/
            makeBucket(ossProperties.getDefaultBucketName());
            log.info("初始化默认桶成功~~~~~");
            log.info("MinioOssServiceImpl initDefaultBucket end");
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
        }
    }

    /**
     * 验证桶名称是否存在
     *
     * @param bucketName 桶名称
     * @return 真假
     * @throws Exception 异常
     */
    private boolean bucketExist(String bucketName) throws Exception {
        return minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
    }

    /**
     * 如果一个桶不存在，则创建该桶
     *
     * @param bucketName 桶名称
     * @throws Exception 异常
     */
    private void createBucket(String bucketName) throws Exception {
        if (!bucketExist(bucketName)) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        }
    }

    /**
     * 通过流上传文件
     *
     * @param bucketName  存储桶
     * @param fileName    文件名
     * @param inputStream 文件流
     */
    public void uploadFileStream(String bucketName, String fileName, InputStream inputStream) throws Exception {
        minioClient.putObject(PutObjectArgs.builder().bucket(bucketName).object(fileName).stream(inputStream, inputStream.available(), -1).build());
    }
}
