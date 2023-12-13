package com.sndshun.rbac0.controller.admin;


import com.sndshun.commons.tools.Result;
import com.sndshun.rbac0.entity.Rbac0PermissionEntity;
import com.sndshun.rbac0.service.Rbac0PermissionService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 权限表(Rbac0Permission)表控制层
 *
 * @author sndshun
 * @since 2023-12-14 03:57:58
 */
@RestController
@RequestMapping("rbac/admin/permission")
public class Rbac0PermissionAdminController {
    /**
     * 服务对象
     */
    @Resource
    private Rbac0PermissionService rbac0PermissionService;

    /**
     * 分页查询所有数据
     *
     * @param page            分页对象
     * @param rbac0Permission 查询实体
     * @return 所有数据
     */
    @GetMapping
    public Result<Page<Rbac0PermissionEntity>> selectPage(Page<Rbac0PermissionEntity> page, Rbac0PermissionEntity rbac0Permission) {
        return Result.ok(this.rbac0PermissionService.page(page, new QueryWrapper<>(rbac0Permission)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public Result<Rbac0PermissionEntity> selectOne(@PathVariable Serializable id) {
        return Result.ok(this.rbac0PermissionService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param rbac0Permission 实体对象
     * @return 新增结果
     */
    @PostMapping
    public Result<Boolean> insert(@RequestBody Rbac0PermissionEntity rbac0Permission) {
        return Result.ok(this.rbac0PermissionService.save(rbac0Permission));
    }

    /**
     * 修改数据
     *
     * @param rbac0Permission 实体对象
     * @return 修改结果
     */
    @PutMapping
    public Result<Boolean> update(@RequestBody Rbac0PermissionEntity rbac0Permission) {
        return Result.ok(this.rbac0PermissionService.updateById(rbac0Permission));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除结果
     */
    @DeleteMapping("/{id}")
    public Result<Boolean> delete(@PathVariable Long id) {
        return Result.ok(this.rbac0PermissionService.removeById(id));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping("batch")
    public Result<Boolean> deleteBatch(@RequestBody List<Long> idList) {
        return Result.ok(this.rbac0PermissionService.removeByIds(idList));
    }
}
