package com.sndshun.blog.entity;


import java.util.Date;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 友链表(BlogFriendLink)表实体类
 *
 * @author sndshun
 * @since 2023-12-03 18:37:42
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("blog_friend_link")
public class BlogFriendLinkEntity extends Model<BlogFriendLinkEntity> {
    /**
     * 友链ID
     */

    private Long id;
    /**
     * 友链名称
     */

    private String name;
    /**
     * 友链URL
     */

    private String url;
    /**
     * 友链描述
     */

    private String description;
    /**
     * 友链Logo URL
     */

    private String logoUrl;
    /**
     * 是否启用; 0: 禁用, 1: 启用
     */

    private Integer isActive;
    /**
     * 是否待审核; 0: 不是, 1: 是;
     */

    private Integer isPendingApproval;
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
