package com.sndshun.rbac0.controller.admin;


import com.sndshun.commons.tools.Result;
import com.sndshun.rbac0.entity.Rbac0UserRoleEntity;
import com.sndshun.rbac0.service.Rbac0UserRoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 用户角色关联表(Rbac0UserRole)表控制层
 *
 * @author sndshun
 * @since 2023-12-14 03:50:52
 */
@RestController
@RequestMapping("rbac/admin/user_role")
public class Rbac0UserRoleAdminController {
    /**
     * 服务对象
     */
    @Resource
    private Rbac0UserRoleService rbac0UserRoleService;

    /**
     * 分页查询所有数据
     *
     * @param page          分页对象
     * @param rbac0UserRole 查询实体
     * @return 所有数据
     */
    @GetMapping
    public Result<Page<Rbac0UserRoleEntity>> selectPage(Page<Rbac0UserRoleEntity> page, Rbac0UserRoleEntity rbac0UserRole) {
        return Result.ok(this.rbac0UserRoleService.page(page, new QueryWrapper<>(rbac0UserRole)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public Result<Rbac0UserRoleEntity> selectOne(@PathVariable Serializable id) {
        return Result.ok(this.rbac0UserRoleService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param rbac0UserRole 实体对象
     * @return 新增结果
     */
    @PostMapping
    public Result<Boolean> insert(@RequestBody Rbac0UserRoleEntity rbac0UserRole) {
        return Result.ok(this.rbac0UserRoleService.save(rbac0UserRole));
    }

    /**
     * 修改数据
     *
     * @param rbac0UserRole 实体对象
     * @return 修改结果
     */
    @PutMapping
    public Result<Boolean> update(@RequestBody Rbac0UserRoleEntity rbac0UserRole) {
        return Result.ok(this.rbac0UserRoleService.updateById(rbac0UserRole));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除结果
     */
    @DeleteMapping("/{id}")
    public Result<Boolean> delete(@PathVariable Long id) {
        return Result.ok(this.rbac0UserRoleService.removeById(id));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping("batch")
    public Result<Boolean> deleteBatch(@RequestBody List<Long> idList) {
        return Result.ok(this.rbac0UserRoleService.removeByIds(idList));
    }
}
