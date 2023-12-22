package com.sndshun.blog.controller.admin;


import com.sndshun.commons.tools.Result;
import com.sndshun.blog.entity.BlogTagEntity;
import com.sndshun.blog.service.BlogTagService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 标签表(BlogTag)表控制层
 *
 * @author sndshun
 * @since 2023-12-22 13:35:22
 */
@RestController
@RequestMapping("blog/admin/tag")
public class BlogTagAdminController {
    /**
     * 服务对象
     */
    @Resource
    private BlogTagService blogTagService;

    /**
     * 分页查询所有数据
     *
     * @param page    分页对象
     * @param blogTag 查询实体
     * @return 所有数据
     */
    @GetMapping
    public Result<Page<BlogTagEntity>> selectPage(Page<BlogTagEntity> page, BlogTagEntity blogTag) {
        return Result.ok(this.blogTagService.page(page, new QueryWrapper<>(blogTag)));
    }

    @GetMapping("map")
    public Result<Map<Long,String>> getTagsMap() {
        return Result.ok(blogTagService.getTagsMapCache());
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public Result<BlogTagEntity> selectOne(@PathVariable Serializable id) {
        return Result.ok(this.blogTagService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param blogTag 实体对象
     * @return 新增结果
     */
    @PostMapping
    public Result<Boolean> insert(@RequestBody BlogTagEntity blogTag) {
        return Result.ok(this.blogTagService.save(blogTag));
    }

    /**
     * 修改数据
     *
     * @param blogTag 实体对象
     * @return 修改结果
     */
    @PutMapping
    public Result<Boolean> update(@RequestBody BlogTagEntity blogTag) {
        return Result.ok(this.blogTagService.updateById(blogTag));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除结果
     */
    @DeleteMapping("/{id}")
    public Result<Boolean> delete(@PathVariable Long id) {
        return Result.ok(this.blogTagService.removeById(id));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping("batch")
    public Result<Boolean> deleteBatch(@RequestBody List<Long> idList) {
        return Result.ok(this.blogTagService.removeByIds(idList));
    }
}
