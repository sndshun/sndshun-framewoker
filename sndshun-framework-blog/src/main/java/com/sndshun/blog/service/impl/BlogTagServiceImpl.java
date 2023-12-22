package com.sndshun.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sndshun.blog.mapper.BlogTagMapper;
import com.sndshun.blog.entity.BlogTagEntity;
import com.sndshun.blog.service.BlogTagService;
import com.sndshun.commons.constant.Status;
import com.sndshun.commons.tools.Result;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 标签表(BlogTag)表服务实现类
 *
 * @author sndshun
 * @since 2023-12-03 18:37:42
 */
@Service("blogTagService")
public class BlogTagServiceImpl extends ServiceImpl<BlogTagMapper, BlogTagEntity> implements BlogTagService {

    @Cacheable(cacheNames = "blog:tag",key = "#root.methodName")
    @Override
    public List<BlogTagEntity> getAllCaChe() {
        LambdaQueryWrapper<BlogTagEntity> select = Wrappers.<BlogTagEntity>lambdaQuery().select(BlogTagEntity::getId,
                BlogTagEntity::getName,
                BlogTagEntity::getDescription,
                BlogTagEntity::getSlug,
                BlogTagEntity::getCategoryId,
                BlogTagEntity::getCategoryName)
                .eq(BlogTagEntity::getIsActive, Status.YES.getValue());
        return super.list(select);
    }

    @Cacheable(cacheNames = "blog:tag",key = "#root.methodName")
    @Override
    public Map<Long, String> getTagsMapCache() {
        LambdaQueryWrapper<BlogTagEntity> select = Wrappers.<BlogTagEntity>lambdaQuery()
                .select(BlogTagEntity::getId,
                        BlogTagEntity::getName)
                .eq(BlogTagEntity::getIsActive, Status.YES.getValue());
        List<BlogTagEntity> list = super.list(select);
        return list.stream().collect(Collectors.toMap(BlogTagEntity::getId, BlogTagEntity::getName));
    }


}
