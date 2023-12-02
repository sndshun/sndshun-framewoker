package com.sndshun.blog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sndshun.blog.entity.BlogPostEntity;
import com.sndshun.commons.tools.Result;
import com.sndshun.web.pojo.QueryPage;

import java.util.List;

/**
 * 文章表(BlogPost)表服务接口
 *
 * @author sndshun
 * @since 2023-12-02 22:03:59
 */
public interface BlogPostService extends IService<BlogPostEntity> {

    /**
     * 分页查询博客文章
     * @param page 页
     * @return {@link Result }<{@link List }<{@link BlogPostEntity }>>
     * @author sndshun
     * @date 2023/12/02 10:13:40
     */
    Page<BlogPostEntity> getPostPage(Page<BlogPostEntity> page);

}
