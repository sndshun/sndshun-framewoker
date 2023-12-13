package com.sndshun.rbac0.controller.admin;


import com.sndshun.commons.tools.Result;
import com.sndshun.rbac0.entity.Rbac0RolePermissionEntity;
import com.sndshun.rbac0.service.Rbac0RolePermissionService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 角色权限映射表
 * <p>
 * (Rbac0RolePermission)表控制层
 *
 * @author sndshun
 * @since 2023-12-14 03:50:51
 */
@RestController
@RequestMapping("rbac/admin/role_permission")
public class Rbac0RolePermissionAdminController {
    /**
     * 服务对象
     */
    @Resource
    private Rbac0RolePermissionService rbac0RolePermissionService;

    /**
     * 分页查询所有数据
     *
     * @param page                分页对象
     * @param rbac0RolePermission 查询实体
     * @return 所有数据
     */
    @GetMapping
    public Result<Page<Rbac0RolePermissionEntity>> selectPage(Page<Rbac0RolePermissionEntity> page, Rbac0RolePermissionEntity rbac0RolePermission) {
        return Result.ok(this.rbac0RolePermissionService.page(page, new QueryWrapper<>(rbac0RolePermission)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public Result<Rbac0RolePermissionEntity> selectOne(@PathVariable Serializable id) {
        return Result.ok(this.rbac0RolePermissionService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param rbac0RolePermission 实体对象
     * @return 新增结果
     */
    @PostMapping
    public Result<Boolean> insert(@RequestBody Rbac0RolePermissionEntity rbac0RolePermission) {
        return Result.ok(this.rbac0RolePermissionService.save(rbac0RolePermission));
    }

    /**
     * 修改数据
     *
     * @param rbac0RolePermission 实体对象
     * @return 修改结果
     */
    @PutMapping
    public Result<Boolean> update(@RequestBody Rbac0RolePermissionEntity rbac0RolePermission) {
        return Result.ok(this.rbac0RolePermissionService.updateById(rbac0RolePermission));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除结果
     */
    @DeleteMapping("/{id}")
    public Result<Boolean> delete(@PathVariable Long id) {
        return Result.ok(this.rbac0RolePermissionService.removeById(id));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping("batch")
    public Result<Boolean> deleteBatch(@RequestBody List<Long> idList) {
        return Result.ok(this.rbac0RolePermissionService.removeByIds(idList));
    }
}
