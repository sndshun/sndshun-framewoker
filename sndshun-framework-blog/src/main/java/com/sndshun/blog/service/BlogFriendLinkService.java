package com.sndshun.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sndshun.blog.entity.BlogFriendLinkEntity;

import java.util.List;

/**
 * 友链表(BlogFriendLink)表服务接口
 *
 * @author sndshun
 * @since 2023-12-03 18:37:42
 */
public interface BlogFriendLinkService extends IService<BlogFriendLinkEntity> {

    /**
     * 查询所有友链
     * @return {@link List }<{@link BlogFriendLinkEntity }>
     * @author sndshun
     * @date 2023/12/03 07:42:12
     */
    List<BlogFriendLinkEntity> getAll();

}
