package com.sndshun.blog.entity;


import java.util.Date;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 标签表(BlogTag)表实体类
 *
 * @author sndshun
 * @since 2023-12-03 18:37:42
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("blog_tag")
public class BlogTagEntity extends Model<BlogTagEntity> {
    /**
     * 标签ID
     */

    private Long id;
    /**
     * 标签名称
     */

    private String name;
    /**
     * 标签描述
     */

    private String description;
    /**
     * 标签别名
     */

    private String slug;
    /**
     * 是否启用; 0: 禁用, 1: 启用
     */

    private Integer isActive;

    /**
     * 所属分类id
     */
    private Long categoryId;
    /**
     * 所属分类名
     */
    private String categoryName;
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
