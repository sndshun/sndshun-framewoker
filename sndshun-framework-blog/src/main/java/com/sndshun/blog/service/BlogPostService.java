package com.sndshun.blog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sndshun.blog.entity.BlogPostEntity;
import com.sndshun.commons.tools.Result;

import java.util.List;
import java.util.Map;

/**
 * 文章表(BlogPost)表服务接口
 *
 * @author sndshun
 * @since 2023-12-02 22:03:59
 */
public interface BlogPostService extends IService<BlogPostEntity> {

    /**
     * 分页查询博客文章
     *
     * @param page 页
     * @return {@link Result }<{@link List }<{@link BlogPostEntity }>>
     * @author sndshun
     * @date 2023/12/02 10:13:40
     */
    Page<BlogPostEntity> getPostPageCache(Page<BlogPostEntity> page);

    /**
     * 根据id查询文章详情
     *
     * @param id 编号
     * @return {@link BlogPostEntity }
     * @author sndshun
     * @date 2023/12/11 12:14:13
     */
    BlogPostEntity getPostByIdCache(Long id);

    /**
     * 查询文章归档页
     *
     * @return {@link List }<{@link BlogPostEntity }>
     * @author sndshun
     * @date 2023/12/05 09:28:13
     */
    Map<Integer, List<BlogPostEntity>> getPostArchive();

    /**
     * 根据postId更新浏览量
     *
     * @param postId 文章ID
     */
    void updateViewsToRedis(Long postId);

    /**
     * 通过哈希键获取值
     *
     * @param hash 哈希
     * @param key  唯一键
     * @return 结果
     */
    Object getValueByHashKey(String hash, Object key);
}
