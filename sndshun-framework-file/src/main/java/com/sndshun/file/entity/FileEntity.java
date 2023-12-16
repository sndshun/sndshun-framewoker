package com.sndshun.file.entity;


import java.util.Date;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 文件表(File)表实体类
 *
 * @author sndshun
 * @since 2023-12-16 00:11:22
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("file")
public class FileEntity extends Model<FileEntity> {
    /**
     * 文件ID
     */

    private Long id;
    /**
     * 文件名
     */

    private String name;
    /**
     * 文件存储路径
     */

    private String filePath;

    /**
     * 拼接路径
     */
    private String connectPath;
    /**
     * 文件大小（字节）
     */

    private Long fileSize;
    /**
     * 文件类型
     */

    private String fileType;
    /**
     * 文件格式
     */

    private String fileFormat;
    /**
     * 文件描述
     */

    private String description;
    /**
     * 文件标签，逗号分隔
     */

    private String tags;
    /**
     * 存储桶
     */

    private String bucket;
    /**
     * 上传人
     */

    private Long uploadedBy;
    /**
     * 上传时间
     */

    private Date uploadTime;
    /**
     * 最后访问时间
     */

    private Date lastAccessedTime;
    /**
     * 下载次数
     */

    private Integer downloadCount;
    /**
     * 是否公开; 0: 不公开, 1: 公开
     */

    private Integer isPublic;
    /**
     * 是否特色文件; 0: 普通文件, 1: 特色文件
     */

    private Integer isFeatured;
    /**
     * 加密算法
     */

    private String encryptionAlgorithm;
    /**
     * 加密密钥
     */

    private String encryptionKey;
    /**
     * 文件过期时间
     */

    private Date expirationTime;
    /**
     * 文件哈希值
     */

    private String fileHash;
    /**
     * 文件MIME类型
     */

    private String mimeType;
    /**
     * 原始文件名
     */

    private String originalFileName;
    /**
     * 缩略图路径
     */

    private String thumbnailPath;
    /**
     * 预览文件路径
     */

    private String previewPath;
    /**
     * 乐观锁
     */

    private Integer version;
    /**
     * 逻辑删除;0：正常 1：删除
     */

    private Integer logicDelete;
    /**
     * 创建人
     */

    private Long createdBy;
    /**
     * 创建时间
     */

    private Date createdTime;
    /**
     * 最后更新人
     */

    private Long updatedBy;
    /**
     * 最后更新时间
     */

    private Date updatedTime;


}
