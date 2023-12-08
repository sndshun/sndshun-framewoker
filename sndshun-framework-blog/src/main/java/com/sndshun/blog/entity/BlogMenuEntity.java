package com.sndshun.blog.entity;


import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 博客菜单(BlogMenu)表实体类
 *
 * @author sndshun
 * @since 2023-11-27 16:30:02
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("blog_menu")
public class BlogMenuEntity extends Model<BlogMenuEntity> {
    /**
     * 主键
     */

    private Long id;
    /**
     * 父id
     */

    private Integer parentId;
    /**
     * 菜单名
     */

    private String name;
    /**
     * 菜单路径
     */

    private String path;
    /**
     * 组件
     */

    private String component;
    /**
     * 菜单icon
     */

    private String icon;
    /**
     * 排序
     */

    private Integer sort;
    /**
     * 是否隐藏  0否1是
     */

    private Integer isHidden;
    /**
     * 类型 0 博客菜单 1 博客后台菜单
     */
    private Integer type;
    /**
     * 租户号
     */

    private Long tenantId;
    /**
     * 最后更新时间
     */

    private Date updatedTime;
    /**
     * 最后更新人
     */

    private Long updatedBy;
    /**
     * 创建时间
     */

    private Date createdTime;
    /**
     * 创建人
     */

    private Long createdBy;
    /**
     * 逻辑删除;0：正常 1：删除
     */

    private Integer logicDelete;
    /**
     * 乐观锁
     */

    private Integer version;


}
