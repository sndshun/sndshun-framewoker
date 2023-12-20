package com.sndshun.file.pojo.dto;

import lombok.Data;

import java.util.Date;

@Data
public class FileAttributesDto {

    /**
     * 名字
     */
    private String name;

    /**
     * 上次修改时间
     */
    private Date lastModifiedTime;

    /**
     * 创建时间
     */
    private Date creationTime;

    /**
     * 大小
     */
    private Long size;

    /**
     * 文件类型
     */
    private String fileType;

    /**
     * MIME 类型
     */
    private String mimeType;

    /**
     * 散 列
     */
    private String fileHash;
}
