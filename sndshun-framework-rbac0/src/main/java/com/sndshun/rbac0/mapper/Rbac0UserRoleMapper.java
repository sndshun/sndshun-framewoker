package com.sndshun.rbac0.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.sndshun.rbac0.entity.Rbac0UserRoleEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户角色关联表(Rbac0UserRole)表数据库访问层
 *
 * @author sndshun
 * @since 2023-12-14 03:50:51
 */
@Mapper
public interface Rbac0UserRoleMapper extends MPJBaseMapper<Rbac0UserRoleEntity> {

}
