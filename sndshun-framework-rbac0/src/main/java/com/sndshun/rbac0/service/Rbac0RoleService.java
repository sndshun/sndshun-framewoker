package com.sndshun.rbac0.service;

import com.github.yulichang.base.MPJBaseService;
import com.sndshun.rbac0.entity.Rbac0RoleEntity;
import com.sndshun.rbac0.pojo.dto.RoleJoinPermissionDto;

/**
 * 用户角色表(Rbac0Role)表服务接口
 *
 * @author sndshun
 * @since 2023-12-14 03:50:51
 */
public interface Rbac0RoleService extends MPJBaseService<Rbac0RoleEntity> {

    /**查询某个角色下所有权限
     * @param id 编号
     * @return {@link RoleJoinPermissionDto }
     * @author sndshun
     * @date 2023/12/14 05:09:03
     */
    RoleJoinPermissionDto selectRolePermission(Integer id);

}
