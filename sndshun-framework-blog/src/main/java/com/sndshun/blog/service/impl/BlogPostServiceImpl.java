package com.sndshun.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sndshun.blog.constant.PublishStatus;
import com.sndshun.blog.entity.BlogPostEntity;
import com.sndshun.blog.mapper.BlogPostMapper;
import com.sndshun.blog.service.BlogPostService;
import com.sndshun.commons.tools.Result;
import com.sndshun.web.pojo.QueryPage;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 文章表(BlogPost)表服务实现类
 *
 * @author sndshun
 * @since 2023-12-02 22:03:59
 */
@Service("blogPostService")
public class BlogPostServiceImpl extends ServiceImpl<BlogPostMapper, BlogPostEntity> implements BlogPostService {

    @Override
    public Page<BlogPostEntity> getPostPage(Page<BlogPostEntity> page) {
        LambdaQueryWrapper<BlogPostEntity> select = Wrappers.<BlogPostEntity>lambdaQuery()
                .select(BlogPostEntity::getId,
                        BlogPostEntity::getTitle,
                        BlogPostEntity::getSummary,
                        BlogPostEntity::getPublishedTime,
                        BlogPostEntity::getCoverImageUrl,
                        BlogPostEntity::getLikes,
                        BlogPostEntity::getComments)
                .eq(BlogPostEntity::getIsPublished, PublishStatus.PUBLISHED.getCode());
        return super.page(page,select);
    }
}
