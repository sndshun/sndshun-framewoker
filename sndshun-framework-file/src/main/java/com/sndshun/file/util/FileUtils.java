package com.sndshun.file.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
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
public class FileUtils {

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
        return filename.matches("^[A-za-z0-9.]{1,255}$");
    }

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

    /**获取文件头信息
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
}
