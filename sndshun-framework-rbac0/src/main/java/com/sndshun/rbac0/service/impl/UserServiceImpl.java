package com.sndshun.rbac0.service.impl;

import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.toolkit.JoinWrappers;
import com.github.yulichang.toolkit.TableList;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.sndshun.rbac0.entity.*;
import com.sndshun.rbac0.mapper.UserMapper;
import com.sndshun.rbac0.pojo.dto.UserRbac0InfoDto;
import com.sndshun.rbac0.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 临时用户表(User)表服务实现类
 *
 * @author sndshun
 * @since 2024-02-06 06:48:54
 */
@Service("userService")
public class UserServiceImpl extends MPJBaseServiceImpl<UserMapper, UserEntity> implements UserService {

    @Override
    public List<Rbac0RoleEntity> selectRolesByUserId(Long userId) {
        MPJLambdaWrapper<UserEntity> select = JoinWrappers.lambda(UserEntity.class)
                .select(Rbac0RoleEntity::getId, Rbac0RoleEntity::getRoleName, Rbac0RoleEntity::getDescription)
                .leftJoin(Rbac0UserRoleEntity.class, Rbac0UserRoleEntity::getUserId, UserEntity::getId)
                .leftJoin(Rbac0RoleEntity.class,Rbac0RoleEntity::getId, Rbac0UserRoleEntity::getRoleId)
                .eq(UserEntity::getId, userId);
        return super.selectJoinList(Rbac0RoleEntity.class,select);
    }

    @Override
    public List<Rbac0PermissionEntity> selectPermissionsByUserId(Long userId) {
        MPJLambdaWrapper<UserEntity> select = JoinWrappers.lambda(UserEntity.class)
                .select(Rbac0PermissionEntity::getId,Rbac0PermissionEntity::getPermissionCode,Rbac0PermissionEntity::getPermissionName)
                .leftJoin(Rbac0UserRoleEntity.class, Rbac0UserRoleEntity::getUserId, UserEntity::getId)
                .leftJoin(Rbac0RolePermissionEntity.class,Rbac0RolePermissionEntity::getRoleId, Rbac0UserRoleEntity::getRoleId)
                .leftJoin(Rbac0PermissionEntity.class,Rbac0PermissionEntity::getId, Rbac0RolePermissionEntity::getPermsId)
                .eq(UserEntity::getId, userId);
        return super.selectJoinList(Rbac0PermissionEntity.class,select);
    }

    @Override
    public UserRbac0InfoDto selectUserRbac0Info(Long userId) {
        MPJLambdaWrapper<UserEntity> select = JoinWrappers.lambda(UserEntity.class)
                .selectAll(UserEntity.class)
                .select(Rbac0RoleEntity::getId, Rbac0RoleEntity::getRoleName, Rbac0RoleEntity::getDescription)
                .select(Rbac0PermissionEntity::getId,Rbac0PermissionEntity::getPermissionCode,Rbac0PermissionEntity::getPermissionName)
                .selectCollection(Rbac0PermissionEntity.class,UserRbac0InfoDto::getPermissions)
                .selectCollection(Rbac0RoleEntity.class,UserRbac0InfoDto::getRoles)
                .leftJoin(Rbac0UserRoleEntity.class, Rbac0UserRoleEntity::getUserId, UserEntity::getId)
                .leftJoin(Rbac0RoleEntity.class,Rbac0RoleEntity::getId, Rbac0UserRoleEntity::getRoleId)
                .leftJoin(Rbac0RolePermissionEntity.class,Rbac0RolePermissionEntity::getRoleId, Rbac0UserRoleEntity::getRoleId)
                .leftJoin(Rbac0PermissionEntity.class,Rbac0PermissionEntity::getId, Rbac0RolePermissionEntity::getPermsId)
                .eq(UserEntity::getId, userId);
        return super.selectJoinOne(UserRbac0InfoDto.class,select);
    }
}
