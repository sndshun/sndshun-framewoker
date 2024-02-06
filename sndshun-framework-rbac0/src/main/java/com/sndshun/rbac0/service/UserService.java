package com.sndshun.rbac0.service;

import com.github.yulichang.base.MPJBaseService;
import com.sndshun.rbac0.entity.Rbac0PermissionEntity;
import com.sndshun.rbac0.entity.Rbac0RoleEntity;
import com.sndshun.rbac0.entity.Rbac0RolePermissionEntity;
import com.sndshun.rbac0.entity.UserEntity;
import com.sndshun.rbac0.pojo.dto.UserRbac0InfoDto;

import java.util.List;

/**
 * 临时用户表(User)表服务接口
 *
 * @author sndshun
 * @since 2024-02-06 06:48:54
 */
public interface UserService extends MPJBaseService<UserEntity> {

    /**
     * 查询用户角色
     * @param userId 用户 ID
     * @return {@link List }<{@link Rbac0RoleEntity }>
     * @author sndshun
     * @date 2024/02/06 06:57:30
     */
    List<Rbac0RoleEntity> selectRolesByUserId(Long userId);


    /**
     * 查询用户权限
     * @param userId 用户 ID
     * @return {@link List }<{@link Rbac0RolePermissionEntity }>
     * @author sndshun
     * @date 2024/02/06 06:59:28
     */
    List<Rbac0PermissionEntity> selectPermissionsByUserId(Long userId);

    /**
     * 用户基本信息
     * @param userId 用户 ID
     * @return {@link UserRbac0InfoDto }
     * @author sndshun
     * @date 2024/02/06 07:11:45
     */
    UserRbac0InfoDto selectUserRbac0Info(Long userId);

}
