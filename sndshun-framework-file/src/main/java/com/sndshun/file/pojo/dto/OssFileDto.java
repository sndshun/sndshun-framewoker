package com.sndshun.file.pojo.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 文件对象
 *
 * @author mapleie
 */
@Data
@Accessors(chain = true)
public class OssFileDto {
    /**
     * OSS 存储时文件路径
     */
    String ossFilePath;

    /**
     * 原始文件名
     */
    String originalFileName;

    /**
     * 上传状态
     */
    Boolean state;
}
