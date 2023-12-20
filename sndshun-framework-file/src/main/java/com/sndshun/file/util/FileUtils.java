package com.sndshun.file.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.util.StrUtil;
import com.sndshun.file.pojo.dto.FileAttributesDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.UUID;

/**
 * 文件工具
 *
 * @author mapleie
 */
@Slf4j
public class FileUtils {

    public static final String tempPath;


    static {
        tempPath = crateTempPath();
    }

    private static String crateTempPath() {
        File upload = null;
        try {
            File path = new File(ResourceUtils.getURL("classpath:").getPath());
            if (!path.exists()) {
                path = new File("");
            }
            upload = new File(path.getAbsolutePath(), "temp/");
            if (!upload.exists()) {
                upload.mkdirs();
            }
        } catch (FileNotFoundException e) {
            log.error("系统临时目录创建失败---------:", e);
        }
        return upload.getAbsolutePath() + StrUtil.SLASH;
    }


    /**
     * 生成随机文件名，防止重复
     *
     * @param originalFilename 原始文件名
     * @return
     */
    public static String generateOssUuidFileName(String originalFilename) {
        return StrUtil.SLASH + DateUtil.format(new Date(), "yyyy-MM-dd") + StrUtil.SLASH + UUID.randomUUID() + StrUtil.SLASH + originalFilename;
    }

    /**
     * 验证文件名是否合法
     *
     * @param filename 文件名
     * @return boolean
     * @author sndshun
     * @date 2023/12/16 12:57:00
     */
    public static boolean validateStringFilenameUsingRegex(String filename) {
        if (filename == null) {
            return false;
        }
        return filename.matches("^[A-za-z0-9.-]{1,255}$");
    }

    /**
     * 文件hash值运算
     *
     * @param inputStream 输入流
     * @return {@link String }
     * @author sndshun
     * @date 2023/12/20 02:35:24
     */
    public static String calculateFileHash(InputStream inputStream) throws IOException, NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        try (DigestInputStream digestInputStream = new DigestInputStream(inputStream, md)) {
            // 读取文件流，同时计算哈希值
            byte[] buffer = new byte[8192];
            while (digestInputStream.read(buffer) != -1) {
                // 读取操作
            }
        }
        // 获取计算得到的哈希值
        byte[] hashBytes = md.digest();

        // 将字节数组转换为十六进制字符串
        StringBuilder hashStringBuilder = new StringBuilder();
        for (byte hashByte : hashBytes) {
            hashStringBuilder.append(String.format("%02x", hashByte));
        }

        return hashStringBuilder.toString();
    }

    /**
     * 获取文件头信息
     *
     * @param file 输入流
     * @return {@link String }
     * @author sndshun
     * @date 2023/12/16 08:14:48
     */
    public static String getFileTypeByHeader(MultipartFile file) throws IOException {
        try (InputStream inputStream = file.getInputStream()) {
            byte[] headerBytes = new byte[8];
            int bytesRead = inputStream.read(headerBytes);

            StringBuilder hexBuilder = new StringBuilder();
            for (byte b : headerBytes) {
                hexBuilder.append(String.format("%02X", b));
            }
            return hexBuilder.toString();
        }
    }

    /**
     * 通过读取流的方式获取文件大小
     *
     * @param inputStream 输入流
     * @return long
     * @author sndshun
     * @date 2023/12/16 08:22:10
     */
    public static long getSizeFromInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        long size = 0;
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            size += bytesRead;
        }
        return size;
    }

    /**
     * 保存临时文件
     *
     * @param multipartFile multipart 文件
     * @return {@link File }
     * @author sndshun
     * @date 2023/12/20 04:01:02
     */
    public static File saveMultipartFileToTempFile(MultipartFile multipartFile) throws IOException {
        File tempFile = new File(tempPath + multipartFile.getOriginalFilename());
        multipartFile.transferTo(tempFile);
        return tempFile;
    }

    /**
     * 获取文件属性
     *
     * @param file 文件
     * @return {@link FileAttributesDto }
     * @author sndshun
     * @date 2023/12/20 04:40:18
     */
    public static FileAttributesDto getFileAttributes(File file) {
        FileAttributesDto dto = new FileAttributesDto();
        // 一次性获取文件的基本属性
        try {
            BasicFileAttributes fileAttributes = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
            String hashCode = calculateFileHash(Files.newInputStream(file.toPath()));

            dto.setName(file.getName());
            dto.setFileType(FileTypeUtil.getType(file));
            dto.setFileHash(hashCode);
            dto.setSize(fileAttributes.size());
        } catch (IOException | NoSuchAlgorithmException e) {
            log.error("文件属性读取失败：", e);
        }

        return dto;
    }
}
