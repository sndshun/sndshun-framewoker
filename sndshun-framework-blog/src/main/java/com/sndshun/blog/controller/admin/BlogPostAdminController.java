package com.sndshun.blog.controller.admin;


import com.sndshun.blog.annotation.VisitLog;
import com.sndshun.blog.enums.VisitEnum;
import com.sndshun.commons.tools.Result;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sndshun.blog.entity.BlogPostEntity;
import com.sndshun.blog.service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 文章表(BlogPost)表控制层
 *
 * @author sndshun
 * @since 2023-12-02 22:03:59
 */
@RestController
@RequestMapping("blog/admin/post")
public class BlogPostAdminController {
    /**
     * 服务对象
     */
    private final BlogPostService blogPostService;

    @Autowired
    public BlogPostAdminController(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }

    /**
     * 分页查询所有数据
     *
     * @param page     分页对象
     * @param blogPost 查询实体
     * @return 所有数据
     */
    @GetMapping
    public Result<Page<BlogPostEntity>> selectPage(Page<BlogPostEntity> page, BlogPostEntity blogPost) {
        LambdaQueryWrapper<BlogPostEntity> select = Wrappers.<BlogPostEntity>lambdaQuery()
                .select(BlogPostEntity::getId,
                        BlogPostEntity::getLikes,
                        BlogPostEntity::getTitle,
                        BlogPostEntity::getLikes,
                        BlogPostEntity::getTags,
                        BlogPostEntity::getPublishedTime,
                        BlogPostEntity::getCategoryId,
                        BlogPostEntity::getType,
                        BlogPostEntity::getComments,
                        BlogPostEntity::getCoverImageUrl)
                .eq(null!=blogPost.getIsPublished(),BlogPostEntity::getIsPublished,blogPost.getIsPublished())
                .eq(null!=blogPost.getLogicDelete(),BlogPostEntity::getLogicDelete,blogPost.getLogicDelete());
        return Result.ok(this.blogPostService.page(page, select));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public Result<BlogPostEntity> selectOne(@PathVariable Serializable id) {
        return Result.ok(this.blogPostService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param blogPost 实体对象
     * @return 新增结果
     */
    @VisitLog(VisitEnum.ARCHIVE)
    @CacheEvict(cacheNames = "blog:post",allEntries = true,condition = "#blogPost.isPublished==1")
    @PostMapping
    public Result<Boolean> insert(@RequestBody BlogPostEntity blogPost) {
        if (null == blogPost.getPublishedTime()) {
            blogPost.setPublishedTime(new Date());
        }
        return Result.ok(this.blogPostService.save(blogPost));
    }

    /**
     * 修改数据
     *
     * @param blogPost 实体对象
     * @return 修改结果
     */
    @CacheEvict(cacheNames = "blog:post",allEntries = true)
    @PutMapping
    public Result<Boolean> update(@RequestBody BlogPostEntity blogPost) {
        if (null == blogPost.getPublishedTime()) {
            blogPost.setPublishedTime(new Date());
        }
        return Result.ok(this.blogPostService.updateById(blogPost));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除结果
     */
    @CacheEvict(cacheNames = "blog:post",allEntries = true)
    @DeleteMapping("/{id}")
    public Result<Boolean> delete(@PathVariable Long id) {
        return Result.ok(this.blogPostService.removeById(id));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @CacheEvict(cacheNames = "blog:post",allEntries = true)
    @DeleteMapping("batch")
    public Result<Boolean> deleteBatch(@RequestBody List<Long> idList) {
        return Result.ok(this.blogPostService.removeByIds(idList));
    }
}
