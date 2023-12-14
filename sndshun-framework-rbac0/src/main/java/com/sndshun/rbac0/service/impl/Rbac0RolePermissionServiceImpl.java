package com.sndshun.rbac0.service.impl;

import com.github.yulichang.base.MPJBaseServiceImpl;
import com.sndshun.rbac0.entity.Rbac0RolePermissionEntity;
import com.sndshun.rbac0.mapper.Rbac0RolePermissionMapper;
import com.sndshun.rbac0.service.Rbac0RolePermissionService;
import org.springframework.stereotype.Service;

/**
 * 角色权限映射表
 * <p>
 * (Rbac0RolePermission)表服务实现类
 *
 * @author sndshun
 * @since 2023-12-14 03:50:51
 */
@Service("rbac0RolePermissionService")
public class Rbac0RolePermissionServiceImpl extends MPJBaseServiceImpl<Rbac0RolePermissionMapper, Rbac0RolePermissionEntity> implements Rbac0RolePermissionService {

}
