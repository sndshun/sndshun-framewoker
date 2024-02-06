package com.sndshun.rbac0.service.impl;

import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.toolkit.JoinWrappers;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.sndshun.rbac0.entity.Rbac0PermissionEntity;
import com.sndshun.rbac0.entity.Rbac0RoleEntity;
import com.sndshun.rbac0.entity.Rbac0RolePermissionEntity;
import com.sndshun.rbac0.entity.Rbac0UserRoleEntity;
import com.sndshun.rbac0.mapper.Rbac0RoleMapper;
import com.sndshun.rbac0.pojo.dto.RoleJoinPermissionDto;
import com.sndshun.rbac0.service.Rbac0RolePermissionService;
import com.sndshun.rbac0.service.Rbac0RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户角色表(Rbac0Role)表服务实现类
 *
 * @author sndshun
 * @since 2023-12-14 03:50:51
 */
@Service("rbac0RoleService")
public class Rbac0RoleServiceImpl extends MPJBaseServiceImpl<Rbac0RoleMapper, Rbac0RoleEntity> implements Rbac0RoleService {
    @Override
    public RoleJoinPermissionDto selectRolePermission(Integer id) {
        MPJLambdaWrapper<Rbac0RoleEntity> select = JoinWrappers.lambda(Rbac0RoleEntity.class)
                .selectAll(Rbac0RoleEntity.class)
                .selectAll(Rbac0PermissionEntity.class)
                .selectCollection(Rbac0PermissionEntity.class, RoleJoinPermissionDto::getPermissions)
                .leftJoin(Rbac0RolePermissionEntity.class, Rbac0RolePermissionEntity::getRoleId,Rbac0RoleEntity::getId)
                .leftJoin(Rbac0PermissionEntity.class, Rbac0PermissionEntity::getId, Rbac0RolePermissionEntity::getPermsId)
                .eq(Rbac0RoleEntity::getId, id);
        return super.selectJoinOne(RoleJoinPermissionDto.class, select);
    }
}
