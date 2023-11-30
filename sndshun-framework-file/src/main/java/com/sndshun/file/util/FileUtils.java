package com.sndshun.file.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;

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
}
