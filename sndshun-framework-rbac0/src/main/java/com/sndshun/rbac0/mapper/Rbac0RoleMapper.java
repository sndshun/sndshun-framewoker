package com.sndshun.rbac0.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.sndshun.rbac0.entity.Rbac0RoleEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户角色表(Rbac0Role)表数据库访问层
 *
 * @author sndshun
 * @since 2023-12-14 03:50:51
 */
@Mapper
public interface Rbac0RoleMapper extends MPJBaseMapper<Rbac0RoleEntity> {

}
