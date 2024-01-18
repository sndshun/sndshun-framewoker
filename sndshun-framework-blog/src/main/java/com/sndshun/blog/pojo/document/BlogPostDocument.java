package com.sndshun.blog.pojo.document;


import com.sndshun.blog.entity.BlogPostEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 文章表(BlogPost)表实体类
 *
 * @author sndshun
 * @since 2023-12-02 22:03:11
 */
@Document(indexName = "blog",createIndex = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogPostDocument {
    /**
     * 文章ID
     */
    @Id
    @Field(type = FieldType.Long)
    private Long id;
    /**
     * 标题
     */
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String title;
    /**
     * 内容
     */
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String content;

    /**
     * 封面图片URL
     */
    @Field(index = false,type = FieldType.Keyword)
    private String coverImageUrl;
    /**
     * 简介
     */
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String summary;
    /**
     * 分类ID
     */
    @Field(type = FieldType.Text)
    private String category;
    /**
     * 标签，逗号分隔
     */
    @Field(type = FieldType.Keyword)
    private List<String> tags;

    /**
     * 排序
     */
    @Field(index = false,type = FieldType.Integer)
    private Integer sort;
    /**
     * 发布时间
     */
    @Field(index = false,type = FieldType.Date,format = DateFormat.date_time)
    private Date publishedTime;
    /**
     * 作者姓名
     */
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String authorName;
    /**
     * 作者简介
     */
    @Field(index = false,type = FieldType.Text)
    private String authorBio;
    /**
     * SEO标题
     */
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String seoTitle;
    /**
     * SEO描述
     */
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String seoDescription;

    /**
     * 最后更新时间
     */
    @Field(index = false,type = FieldType.Date,format = DateFormat.date_time)
    private Date updatedTime;

    /**
     * 创建时间
     */
    @Field(index = false,type = FieldType.Date,format = DateFormat.date_time)
    private Date createdTime;


    public static BlogPostDocument convertToBlogPostDocument(BlogPostEntity item, Map<Long,String> categoryMap) {
        if (item == null) {
            return null;
        }
        BlogPostDocument result = new BlogPostDocument();
        result.setId(item.getId());
        result.setTitle(item.getTitle());
        result.setContent(item.getContent());
        result.setCoverImageUrl(item.getCoverImageUrl());
        result.setSummary(item.getSummary());
        result.setCategory(categoryMap.get(item.getCategoryId()));
        result.setTags(Arrays.asList(item.getTags().split(",")));
        result.setSort(item.getSort());
        result.setPublishedTime(item.getPublishedTime());
        result.setAuthorName(item.getAuthorName());
        result.setAuthorBio(item.getAuthorBio());
        result.setSeoTitle(item.getSeoTitle());
        result.setSeoDescription(item.getSeoDescription());
        result.setUpdatedTime(item.getUpdatedTime());
        result.setCreatedTime(item.getCreatedTime());
        return result;
    }

    public static List<BlogPostDocument> convertToBlogPostEntityList(List<BlogPostEntity> list, Map<Long,String> categoryMap) {
        if (list.isEmpty()) {
            return new ArrayList<>();
        }
        return list.stream().map(item->convertToBlogPostDocument(item,categoryMap)).collect(Collectors.toList());
    }
}
