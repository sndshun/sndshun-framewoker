package com.sndshun.rbac0.pojo.dto;

import com.sndshun.rbac0.entity.Rbac0PermissionEntity;
import com.sndshun.rbac0.entity.Rbac0RoleEntity;
import com.sndshun.rbac0.entity.UserEntity;
import lombok.*;

import java.util.List;

/**
 * 用户 rbac0 信息
 * @author sndshun
 * @date 2024/02/06 07:00:45
 */
@Getter
@Setter
public class UserRbac0InfoDto extends UserEntity {
    /**
     * 用户权限列表
     */
    private List<Rbac0PermissionEntity> permissions;

    /**
     * 角色列表
     */
    private List<Rbac0RoleEntity> roles;

    @Override
    public String toString() {
        return "UserRbac0InfoDto{" +
                "permissions=" + permissions +
                ", roles=" + roles +
                "} " + super.toString();
    }
}
