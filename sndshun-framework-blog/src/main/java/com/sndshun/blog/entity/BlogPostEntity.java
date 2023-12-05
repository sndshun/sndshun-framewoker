package com.sndshun.blog.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 文章表(BlogPost)表实体类
 *
 * @author sndshun
 * @since 2023-12-02 22:03:11
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("blog_post")
public class BlogPostEntity extends Model<BlogPostEntity> {
    /**
     * 文章ID
     */

    private Long id;
    /**
     * 标题
     */

    private String title;
    /**
     * 内容
     */

    private String content;
    /**
     * 点赞数
     */

    private Integer likes;
    /**
     * 评论数
     */

    private Integer comments;
    /**
     * 封面图片URL
     */

    private String coverImageUrl;
    /**
     * 简介
     */

    private String summary;
    /**
     * 浏览次数
     */

    private Integer viewCount;
    /**
     * 是否已发布; 0: 未发布, 1: 已发布
     */

    private Integer isPublished;
    /**
     * 分类ID
     */

    private Long categoryId;
    /**
     * 标签，逗号分隔
     */

    private String tags;
    /**
     * 访问密码
     */

    private String password;
    /**
     * 文章类型;1原创 2转载 3翻译
     */

    private Integer type;
    /**
     * 原文链接
     */

    private String sourceUrl;
    /**
     * 是否允许评论; 0: 不允许, 1: 允许
     */

    private Integer allowComments;
    /**
     * 是否允许点赞; 0: 不允许, 1: 允许
     */

    private Integer allowLikes;
    /**
     * 是否特色文章; 0: 普通文章, 1: 特色文章
     */

    private Integer isFeatured;
    /**
     * 发布时间
     */

    private Date publishedTime;
    /**
     * 作者姓名
     */

    private String authorName;
    /**
     * 作者简介
     */

    private String authorBio;
    /**
     * SEO标题
     */

    private String seoTitle;
    /**
     * SEO描述
     */

    private String seoDescription;
    /**
     * 租户号
     */

    private Long tenantId;
    /**
     * 最后更新时间
     */

    private Date updatedTime;
    /**
     * 乐观锁
     */

    private Integer version;
    /**
     * 逻辑删除;0：正常 1：删除
     */

    private Integer logicDelete;
    /**
     * 创建时间
     */

    private Date createdTime;
    /**
     * 创建人
     */

    private Long createdBy;
    /**
     * 最后更新人
     */

    private Long updatedBy;


}
