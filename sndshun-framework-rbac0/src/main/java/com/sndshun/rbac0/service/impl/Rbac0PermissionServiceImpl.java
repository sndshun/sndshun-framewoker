package com.sndshun.rbac0.service.impl;

import com.github.yulichang.base.MPJBaseServiceImpl;
import com.sndshun.rbac0.entity.Rbac0PermissionEntity;
import com.sndshun.rbac0.mapper.Rbac0PermissionMapper;
import com.sndshun.rbac0.service.Rbac0PermissionService;
import org.springframework.stereotype.Service;

/**
 * 权限表(Rbac0Permission)表服务实现类
 *
 * @author sndshun
 * @since 2023-12-14 03:57:58
 */
@Service("rbac0PermissionService")
public class Rbac0PermissionServiceImpl extends MPJBaseServiceImpl<Rbac0PermissionMapper, Rbac0PermissionEntity> implements Rbac0PermissionService {

}
