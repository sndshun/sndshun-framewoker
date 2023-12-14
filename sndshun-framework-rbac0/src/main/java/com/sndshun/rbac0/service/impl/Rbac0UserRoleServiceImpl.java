package com.sndshun.rbac0.service.impl;

import com.github.yulichang.base.MPJBaseServiceImpl;
import com.sndshun.rbac0.entity.Rbac0UserRoleEntity;
import com.sndshun.rbac0.mapper.Rbac0UserRoleMapper;
import com.sndshun.rbac0.service.Rbac0UserRoleService;
import org.springframework.stereotype.Service;

/**
 * 用户角色关联表(Rbac0UserRole)表服务实现类
 *
 * @author sndshun
 * @since 2023-12-14 03:50:51
 */
@Service("rbac0UserRoleService")
public class Rbac0UserRoleServiceImpl extends MPJBaseServiceImpl<Rbac0UserRoleMapper, Rbac0UserRoleEntity> implements Rbac0UserRoleService {

}
