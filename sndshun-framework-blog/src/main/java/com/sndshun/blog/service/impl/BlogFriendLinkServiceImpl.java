package com.sndshun.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sndshun.blog.mapper.BlogFriendLinkMapper;
import com.sndshun.blog.entity.BlogFriendLinkEntity;
import com.sndshun.blog.service.BlogFriendLinkService;
import com.sndshun.commons.constant.Status;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 友链表(BlogFriendLink)表服务实现类
 *
 * @author sndshun
 * @since 2023-12-03 18:37:42
 */
@Service("blogFriendLinkService")
public class BlogFriendLinkServiceImpl extends ServiceImpl<BlogFriendLinkMapper, BlogFriendLinkEntity> implements BlogFriendLinkService {

    //@Cacheable(cacheNames = "blog:link",key = "#root.methodName")
    @Override
    public List<BlogFriendLinkEntity> getAll() {
        LambdaQueryWrapper<BlogFriendLinkEntity> select = Wrappers.<BlogFriendLinkEntity>lambdaQuery().select(BlogFriendLinkEntity::getId,
                        BlogFriendLinkEntity::getName,
                        BlogFriendLinkEntity::getDescription,
                        BlogFriendLinkEntity::getLogoUrl,
                        BlogFriendLinkEntity::getUrl)
                .eq(BlogFriendLinkEntity::getIsActive, Status.YES.getValue())
                .eq(BlogFriendLinkEntity::getIsPendingApproval, Status.NO.getValue());
        return super.list(select);
    }
}
