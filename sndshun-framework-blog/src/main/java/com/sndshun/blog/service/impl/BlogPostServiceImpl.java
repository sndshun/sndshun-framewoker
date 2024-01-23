package com.sndshun.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sndshun.blog.constant.PublishStatus;
import com.sndshun.blog.entity.BlogPostEntity;
import com.sndshun.blog.mapper.BlogPostMapper;
import com.sndshun.blog.service.BlogPostService;
import org.springframework.cache.annotation.Cacheable;

import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 文章表(BlogPost)表服务实现类
 *
 * @author sndshun
 * @since 2023-12-02 22:03:59
 */
@Service("blogPostService")
public class BlogPostServiceImpl extends ServiceImpl<BlogPostMapper, BlogPostEntity> implements BlogPostService {


    @Cacheable(cacheNames = "blog:post", key = "#page.current+':'+#page.size")
    @Override
    public Page<BlogPostEntity> getPostPageCache(Page<BlogPostEntity> page) {
        LambdaQueryWrapper<BlogPostEntity> select = Wrappers.<BlogPostEntity>lambdaQuery()
                .select(BlogPostEntity::getId,
                        BlogPostEntity::getTitle,
                        BlogPostEntity::getSummary,
                        BlogPostEntity::getPublishedTime,
                        BlogPostEntity::getCoverImageUrl,
                        BlogPostEntity::getLikes,
                        BlogPostEntity::getComments)
                .eq(BlogPostEntity::getIsPublished, PublishStatus.PUBLISHED.getCode())
                .orderByAsc(BlogPostEntity::getSort)
                .orderByDesc(BlogPostEntity::getPublishedTime);
        return super.page(page, select);
    }

    @Cacheable(cacheNames = "blog:post:id", key = "#id")
    @Override
    public BlogPostEntity getPostByIdCache(Long id) {
        LambdaQueryWrapper<BlogPostEntity> select = Wrappers.<BlogPostEntity>lambdaQuery()
                .select(BlogPostEntity::getId,
                        BlogPostEntity::getTitle,
                        BlogPostEntity::getSummary,
                        BlogPostEntity::getPublishedTime,
                        BlogPostEntity::getCoverImageUrl,
                        BlogPostEntity::getLikes,
                        BlogPostEntity::getComments,
                        BlogPostEntity::getContent)
                .eq(BlogPostEntity::getId, id)
                .eq(BlogPostEntity::getIsPublished, PublishStatus.PUBLISHED.getCode());
        return super.getOne(select);
    }

    @Override
    public Map<Integer, List<BlogPostEntity>> getPostArchive() {
        LambdaQueryWrapper<BlogPostEntity> select = Wrappers.<BlogPostEntity>lambdaQuery()
                .select(BlogPostEntity::getId,
                        BlogPostEntity::getTitle,
                        BlogPostEntity::getSummary,
                        BlogPostEntity::getPublishedTime,
                        BlogPostEntity::getCoverImageUrl,
                        BlogPostEntity::getLikes)
                .eq(BlogPostEntity::getIsPublished, PublishStatus.PUBLISHED.getCode())
                .orderByDesc(BlogPostEntity::getPublishedTime);
        List<BlogPostEntity> list = super.list(select);

        Map<Integer, List<BlogPostEntity>> collect = list.stream()
                .sorted(Comparator.comparing(BlogPostEntity::getPublishedTime).reversed())
                .collect(Collectors.groupingBy(post -> post.getPublishedTime().getYear() + 1900,
                        LinkedHashMap::new,
                        Collectors.toList()));
        return collect;
    }
}
