package com.sndshun.blog.controller.admin;


import com.sndshun.commons.tools.Result;
import com.sndshun.blog.entity.BlogVisitUserEntity;
import com.sndshun.blog.service.BlogVisitUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 访客用户(BlogVisitUser)表控制层
 *
 * @author sndshun
 * @since 2023-12-11 13:33:07
 */
@RestController
@RequestMapping("blogVisitUser")
public class BlogVisitUserAdminController {
    /**
     * 服务对象
     */
    @Resource
    private BlogVisitUserService blogVisitUserService;

    /**
     * 分页查询所有数据
     *
     * @param page          分页对象
     * @param blogVisitUser 查询实体
     * @return 所有数据
     */
    @GetMapping
    public Result<Page<BlogVisitUserEntity>> selectPage(Page<BlogVisitUserEntity> page, BlogVisitUserEntity blogVisitUser) {
        return Result.ok(this.blogVisitUserService.page(page, new QueryWrapper<>(blogVisitUser)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public Result<BlogVisitUserEntity> selectOne(@PathVariable Serializable id) {
        return Result.ok(this.blogVisitUserService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param blogVisitUser 实体对象
     * @return 新增结果
     */
    @PostMapping
    public Result<Boolean> insert(@RequestBody BlogVisitUserEntity blogVisitUser) {
        return Result.ok(this.blogVisitUserService.save(blogVisitUser));
    }

    /**
     * 修改数据
     *
     * @param blogVisitUser 实体对象
     * @return 修改结果
     */
    @PutMapping
    public Result<Boolean> update(@RequestBody BlogVisitUserEntity blogVisitUser) {
        return Result.ok(this.blogVisitUserService.updateById(blogVisitUser));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除结果
     */
    @DeleteMapping("/{id}")
    public Result<Boolean> delete(@PathVariable Long id) {
        return Result.ok(this.blogVisitUserService.removeById(id));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping("batch")
    public Result<Boolean> deleteBatch(@RequestBody List<Long> idList) {
        return Result.ok(this.blogVisitUserService.removeByIds(idList));
    }
}
