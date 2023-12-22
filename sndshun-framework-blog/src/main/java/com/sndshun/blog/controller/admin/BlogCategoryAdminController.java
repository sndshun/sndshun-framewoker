package com.sndshun.blog.controller.admin;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sndshun.blog.entity.BlogCategoryEntity;
import com.sndshun.blog.service.BlogCategoryService;
import com.sndshun.blog.vo.BlogCategoryTreeVo;
import com.sndshun.commons.tools.Result;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 分类表(BlogCategory)表控制层
 *
 * @author sndshun
 * @since 2023-12-12 01:38:26
 */
@RestController
@RequestMapping("blog/admin/category")
public class BlogCategoryAdminController {
    /**
     * 服务对象
     */
    @Resource
    private BlogCategoryService blogCategoryService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param blogCategory 查询实体
     * @return 所有数据
     */
    @GetMapping
    public Result<Page<BlogCategoryEntity>> selectPage(Page<BlogCategoryEntity> page, BlogCategoryEntity blogCategory) {
        return Result.ok(this.blogCategoryService.page(page, new QueryWrapper<>(blogCategory)));
    }

    /**
     * 以树型数据返回所有分类
     * @return {@link Result }<{@link ? }>
     * @author sndshun
     * @date 2023/12/12 01:49:42
     */
    @Cacheable(cacheNames = "blog:category",key = "#root.methodName")
    @GetMapping("tree")
    public Result<List<BlogCategoryTreeVo>> getAdminTree() {
        return Result.ok(this.blogCategoryService.getCategoryAllTree());
    }

    /** 返回所有分类map形式
     * @return {@link Result }<{@link Map }<{@link String },{@link String }>>
     * @author sndshun
     * @date 2023/12/21 09:10:41
     */
    @Cacheable(cacheNames = "blog:category",key = "#root.methodName")
    @GetMapping("map")
    public Result<Map<Long,String>> getAdminMap() {
        List<BlogCategoryEntity> list = this.blogCategoryService.getCategoryDict();
        Map<Long, String> collect = list.stream().collect(Collectors.toMap(BlogCategoryEntity::getId, BlogCategoryEntity::getName));
        return Result.ok(collect);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public Result<BlogCategoryEntity> selectOne(@PathVariable Serializable id) {
        return Result.ok(this.blogCategoryService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param blogCategory 实体对象
     * @return 新增结果
     */
    @PostMapping
    public Result<Boolean> insert(@RequestBody BlogCategoryEntity blogCategory) {
        return Result.ok(this.blogCategoryService.save(blogCategory));
    }

    /**
     * 修改数据
     *
     * @param blogCategory 实体对象
     * @return 修改结果
     */
    @PutMapping
    public Result<Boolean> update(@RequestBody BlogCategoryEntity blogCategory) {
        return Result.ok(this.blogCategoryService.updateById(blogCategory));
    }
    
    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除结果
     */
    @DeleteMapping("/{id}")
    public Result<Boolean> delete(@PathVariable Long id) {
        return Result.ok(this.blogCategoryService.removeById(id));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping("batch")
    public Result<Boolean> deleteBatch(@RequestBody List<Long> idList) {
        return Result.ok(this.blogCategoryService.removeByIds(idList));
    }
}
