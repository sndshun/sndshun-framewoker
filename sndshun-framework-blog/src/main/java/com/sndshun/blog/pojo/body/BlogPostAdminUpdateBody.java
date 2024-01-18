package com.sndshun.blog.pojo.body;


import com.sndshun.blog.entity.BlogPostEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 *
 * @author sndshun
 * @since 2023-12-02 22:03:11
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogPostAdminUpdateBody {
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

    private List<String> tags;
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
     * 排序
     */
    private Integer sort;
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


    public static BlogPostEntity convertToBlogPostEntity(BlogPostAdminUpdateBody item) {
        if (item == null) {
            return null;
        }
        BlogPostEntity result = new BlogPostEntity();
        result.setId(item.getId());
        result.setTitle(item.getTitle());
        result.setContent(item.getContent());
        result.setLikes(item.getLikes());
        result.setComments(item.getComments());
        result.setCoverImageUrl(item.getCoverImageUrl());
        result.setSummary(item.getSummary());
        result.setViewCount(item.getViewCount());
        result.setIsPublished(item.getIsPublished());
        result.setCategoryId(item.getCategoryId());
        result.setTags(String.join(",",item.getTags()));
        result.setPassword(item.getPassword());
        result.setType(item.getType());
        result.setSourceUrl(item.getSourceUrl());
        result.setAllowComments(item.getAllowComments());
        result.setAllowLikes(item.getAllowLikes());
        result.setIsFeatured(item.getIsFeatured());
        result.setSort(item.getSort());
        result.setPublishedTime(item.getPublishedTime());
        result.setAuthorName(item.getAuthorName());
        result.setAuthorBio(item.getAuthorBio());
        result.setSeoTitle(item.getSeoTitle());
        result.setSeoDescription(item.getSeoDescription());
        result.setTenantId(item.getTenantId());
        result.setUpdatedTime(item.getUpdatedTime());
        result.setVersion(item.getVersion());
        result.setLogicDelete(item.getLogicDelete());
        result.setCreatedTime(item.getCreatedTime());
        result.setCreatedBy(item.getCreatedBy());
        result.setUpdatedBy(item.getUpdatedBy());
        return result;
    }
}
