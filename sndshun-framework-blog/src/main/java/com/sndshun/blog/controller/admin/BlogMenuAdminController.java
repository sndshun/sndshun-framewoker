package com.sndshun.blog.controller.admin;


import com.sndshun.blog.vo.BlogMenuTreeVo;
import com.sndshun.commons.tools.Result;
import com.sndshun.blog.entity.BlogMenuEntity;
import com.sndshun.blog.service.BlogMenuService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

/**
 * 博客菜单(BlogMenu)管理控制层
 *
 * @author sndshun
 * @since 2023-11-27 17:05:42
 */
@RestController
@RequestMapping("blog/admin/menu")
public class BlogMenuAdminController {
    /**
     * 服务对象
     */
    private final BlogMenuService blogMenuService;
    @Autowired
    public BlogMenuAdminController(BlogMenuService blogMenuService) {
        this.blogMenuService = blogMenuService;
    }

    /**
     * 分页查询所有数据
     *
     * @param page     分页对象
     * @param blogMenu 查询实体
     * @return 所有数据
     */
    @GetMapping
    public Result<Page<BlogMenuEntity>> selectAll(Page<BlogMenuEntity> page, BlogMenuEntity blogMenu) {
        return Result.ok(this.blogMenuService.page(page, new QueryWrapper<>(blogMenu)));
    }

    /**
     * 查询所有菜单树形结构展示
     *
     * @return {@link Result }<{@link ? }>
     * @author sndshun
     * @date 2023/11/28 11:42:53
     */
    @GetMapping("tree/all")
    public Result<List<BlogMenuTreeVo>> selectTreeAll() {
        return Result.ok(this.blogMenuService.blogMenuTreeAllCaChe());
    }

    /**查询博客管理端菜单
     * @return {@link Result }<{@link ? }>
     * @author sndshun
     * @date 2023/12/08 10:47:43
     */
    @GetMapping("tree")
    public Result<List<BlogMenuTreeVo>> selectTree() {
        return Result.ok(this.blogMenuService.blogMenuTreeCaChe());
    }
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public Result<BlogMenuEntity> selectOne(@PathVariable Serializable id) {
        return Result.ok(this.blogMenuService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param blogMenu 实体对象
     * @return 新增结果
     */
    @PostMapping
    public Result<Boolean> insert(@RequestBody BlogMenuEntity blogMenu) {
        return Result.ok(this.blogMenuService.save(blogMenu));
    }

    /**
     * 修改数据
     *
     * @param blogMenu 实体对象
     * @return 修改结果
     */
    @PutMapping
    public Result<Boolean> update(@RequestBody BlogMenuEntity blogMenu) {
        return Result.ok(this.blogMenuService.updateById(blogMenu));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public Result<Boolean> delete(@RequestBody List<Long> idList) {
        return Result.ok(this.blogMenuService.removeByIds(idList));
    }
}
