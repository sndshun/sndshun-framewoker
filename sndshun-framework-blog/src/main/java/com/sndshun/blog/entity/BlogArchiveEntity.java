package com.sndshun.blog.entity;


import java.util.Date;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 归档表(BlogArchive)表实体类
 *
 * @author sndshun
 * @since 2023-12-03 18:37:41
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("blog_archive")
public class BlogArchiveEntity extends Model<BlogArchiveEntity> {
    /**
     * 归档ID
     */

    private Long id;
    /**
     * 归档日期
     */

    private Date archiveDate;
    /**
     * 文章数量
     */

    private Integer articleCount;
    /**
     * 租户号
     */

    private Long tenantId;
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
