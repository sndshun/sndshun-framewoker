package com.sndshun.file.controller;

import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.core.util.StrUtil;
import com.sndshun.commons.config.ResultCode;
import com.sndshun.commons.tools.Result;
import com.sndshun.commons.tools.StringUtils;
import com.sndshun.file.config.OssProperties;
import com.sndshun.file.entity.FileEntity;
import com.sndshun.file.pojo.dto.FileAttributesDto;
import com.sndshun.file.pojo.dto.OssFileDto;
import com.sndshun.file.service.FileService;
import com.sndshun.file.service.OssService;
import com.sndshun.file.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * 文件对象控制层
 *
 * @author mapleie
 */
@RestController
@RequestMapping("/file")
public class FileController extends BaseController {

    private final String bucketName = "file";
    private final OssService ossService;

    private final FileService fileService;

    private final OssProperties ossProperties;

    @Autowired
    public FileController(OssService ossService, FileService fileService,OssProperties ossProperties) {
        this.ossService = ossService;
        this.fileService = fileService;
        this.ossProperties=ossProperties;

        Boolean b = ossService.bucketExists(bucketName);
        if (!b) {
            ossService.makeBucket(bucketName);
        }
    }


    /**
     * 秒传 需前端验证md5
     *
     * @param md5  MD5型
     * @param hash 散 列
     * @return {@link Result }<{@link OssFileDto }>
     * @author sndshun
     * @date 2023/12/16 01:28:26
     */
    public Result<OssFileDto> isFileAlreadyUploaded(String md5, @RequestHeader("content-md5") String hash) {
        FileEntity fileEntity = null;
        if (StrUtil.isNotEmpty(md5)) {
            fileEntity = fileService.getFileByHash(md5);
        } else {
            fileEntity = fileService.getFileByHash(hash);
        }

        if (null != fileEntity) {
            return Result.ok(new OssFileDto().setState(true).setOssFilePath(fileEntity.getFilePath()).setOriginalFileName(fileEntity.getOriginalFileName()));
        }
        return Result.error(ResultCode.OSS_FILE_NOT);
    }


    /**
     * 文件上传接口
     * todo 需优化
     * @param file 文件
     * @return {@link Result }<{@link OssFileDto }>
     * @author sndshun
     * @date 2023/12/16 12:23:28
     */
    @PostMapping("upload")
    @Transactional(rollbackFor = Exception.class)
    public Result<OssFileDto> uploadFile(MultipartFile file) throws IOException, NoSuchAlgorithmException {
        if (file.isEmpty()) return Result.error(ResultCode.OSS_FILE_EMPTY);

        //创建临时文件
        File tempFile = FileUtils.saveMultipartFileToTempFile(file);

        //获取基本信息
        FileAttributesDto fileAttributes = FileUtils.getFileAttributes(tempFile);
        fileAttributes.setMimeType(file.getContentType());

        return handleFileUpload(fileAttributes, tempFile);
    }

    /**
     * 网络文件获取
     * todo 需优化
     * @param url 链接
     * @return {@link Result }<{@link OssFileDto }>
     * @author sndshun
     * @date 2023/12/16 12:23:28
     */
    @PostMapping("path")
    @Transactional(rollbackFor = Exception.class)
    public Result<OssFileDto> uploadFilePath(@RequestBody Map<String,String> map) throws IOException {
        try {
            URL webUrl = new URL(map.get("url"));

            // 获取基本信息
            String contentType = URLConnection.guessContentTypeFromName(map.get("url"));
            String originalFilename = FileNameUtil.getName(map.get("url"));

            return Result.ok(null);
        } catch (IOException e) {
            log.error("文件上传失败---------->", e);
            return Result.error(ResultCode.ERROR);
        }
    }

    private Result<OssFileDto> handleFileUpload(FileAttributesDto fileAttributes,File file)
            throws IOException, NoSuchAlgorithmException {

        OssFileDto fileDto = new OssFileDto();


        //获取基本信息
        String pathAndName = FileUtils.generateOssUuidFileName(fileAttributes.getName());
        String type = FileNameUtil.getSuffix(fileAttributes.getName());

        if (!FileUtils.validateStringFilenameUsingRegex(fileAttributes.getName())) return Result.error(ResultCode.OSS_FILE_NAME_ERROR);
        if (StrUtil.isEmpty(type)) return Result.error(ResultCode.OSS_FILE_NAME_ERROR);
        if (!type.equals(fileAttributes.getFileType())) return Result.error(ResultCode.OSS_FILE_TYPE_ERROR);

        //验证hash
        FileEntity fileByHash = fileService.getFileByHash(fileAttributes.getFileHash());
        if (null != fileByHash) {
            fileDto.setState(true);
            fileDto.setOssFilePath(fileByHash.getConnectPath());
            fileDto.setOriginalFileName(fileAttributes.getName());
            fileByHash.setOriginalFileName(fileAttributes.getName());
            fileService.save(fileByHash);
            return Result.ok(fileDto);
        }


        //保存入库
        FileEntity fileEntity = new FileEntity();
        fileEntity.setBucket(bucketName);
        fileEntity.setOriginalFileName(fileAttributes.getName());
        fileEntity.setName(fileAttributes.getName());
        fileEntity.setFileSize(fileAttributes.getSize());
        fileEntity.setFileType(fileAttributes.getFileType());
        fileEntity.setMimeType(fileAttributes.getMimeType());
        fileEntity.setFileHash(fileAttributes.getFileHash());
        fileEntity.setFilePath(pathAndName);
        fileEntity.setConnectPath(StrUtil.SLASH+ossProperties.getType().getName()+StrUtil.SLASH+bucketName+pathAndName);
        fileService.save(fileEntity);

        fileDto.setState(true);
        fileDto.setOssFilePath(fileEntity.getConnectPath());
        fileDto.setOriginalFileName(fileAttributes.getName());
        // 文件上传
        ossService.upload(Files.newInputStream(file.toPath()), bucketName, pathAndName);
        boolean delete = file.delete();
        return Result.ok(fileDto);
    }


    @GetMapping("/bucket/list")
    public Result<String> bucketList() {
        log.info("FileController bucketList start");
        Result<String> result = ossService.listBuckets();
        log.info("FileController bucketList end");
        return result;
    }

    /**
     * 桶是否存在
     *
     * @param bucketName 桶名
     * @return 是否存在
     */
    @GetMapping("/exist/{bucketName}")
    public Result<Boolean> bucketExists(@PathVariable String bucketName) {
        log.info("FileController bucketExists start");
        boolean empty = StringUtils.isEmpty(bucketName);
        StringUtils.isBooleanTrue(empty);
        Boolean result = ossService.bucketExists(bucketName);
        log.info("FileController bucketExists end");
        return Result.ok(result);
    }

    @GetMapping("/bucket/{bucketName}")
    public Result<String> getBucketObjectMsg(@PathVariable String bucketName) {
        log.info("FileController bucketExists start");
        boolean empty = StringUtils.isEmpty(bucketName);
        StringUtils.isBooleanTrue(empty);
        Result<String> result = ossService.getBucketObjectMsg(bucketName, true);
        log.info("FileController bucketExists end");
        return result;
    }
}
