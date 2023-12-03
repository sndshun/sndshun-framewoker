package com.sndshun.blog.entity;


import java.util.Date;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分类表(BlogCategory)表实体类
 *
 * @author sndshun
 * @since 2023-12-03 18:37:42
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("blog_category")
public class BlogCategoryEntity extends Model<BlogCategoryEntity> {
    /**
     * 分类ID
     */

    private Long id;
    /**
     * 分类名称
     */

    private String name;
    /**
     * 分类描述
     */

    private String description;
    /**
     * 是分类的别名，可用于生成友好的 URL
     */

    private String slug;
    /**
     * 分类下有几篇文章
     */
    private Integer postCount;
    /**
     * 父分类ID，表示层级关系
     */

    private Long parentId;
    /**
     * 显示顺序
     */

    private Integer sort;
    /**
     * 是否启用; 0: 禁用, 1: 启用
     */

    private Integer isActive;
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
