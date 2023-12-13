package com.sndshun.rbac0.controller.admin;


import com.sndshun.commons.tools.Result;
import com.sndshun.rbac0.entity.Rbac0RoleEntity;
import com.sndshun.rbac0.service.Rbac0RoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 用户角色表(Rbac0Role)表控制层
 *
 * @author sndshun
 * @since 2023-12-14 03:50:51
 */
@RestController
@RequestMapping("rbac/admin/role")
public class Rbac0RoleAdminController {
    /**
     * 服务对象
     */
    @Resource
    private Rbac0RoleService rbac0RoleService;

    /**
     * 分页查询所有数据
     *
     * @param page      分页对象
     * @param rbac0Role 查询实体
     * @return 所有数据
     */
    @GetMapping
    public Result<Page<Rbac0RoleEntity>> selectPage(Page<Rbac0RoleEntity> page, Rbac0RoleEntity rbac0Role) {
        return Result.ok(this.rbac0RoleService.page(page, new QueryWrapper<>(rbac0Role)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public Result<Rbac0RoleEntity> selectOne(@PathVariable Serializable id) {
        return Result.ok(this.rbac0RoleService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param rbac0Role 实体对象
     * @return 新增结果
     */
    @PostMapping
    public Result<Boolean> insert(@RequestBody Rbac0RoleEntity rbac0Role) {
        return Result.ok(this.rbac0RoleService.save(rbac0Role));
    }

    /**
     * 修改数据
     *
     * @param rbac0Role 实体对象
     * @return 修改结果
     */
    @PutMapping
    public Result<Boolean> update(@RequestBody Rbac0RoleEntity rbac0Role) {
        return Result.ok(this.rbac0RoleService.updateById(rbac0Role));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除结果
     */
    @DeleteMapping("/{id}")
    public Result<Boolean> delete(@PathVariable Long id) {
        return Result.ok(this.rbac0RoleService.removeById(id));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping("batch")
    public Result<Boolean> deleteBatch(@RequestBody List<Long> idList) {
        return Result.ok(this.rbac0RoleService.removeByIds(idList));
    }
}
