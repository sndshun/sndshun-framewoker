package com.sndshun.rbac0.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.sndshun.rbac0.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 临时用户表(User)表数据库访问层
 *
 * @author sndshun
 * @since 2024-02-06 06:48:54
 */
@Mapper
public interface UserMapper extends MPJBaseMapper<UserEntity> {

}
