package com.sndshun.rbac0.entity;


import java.util.Date;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 角色权限映射表
 * <p>
 * (Rbac0RolePermission)表实体类
 *
 * @author sndshun
 * @since 2023-12-14 03:50:51
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("rbac0_role_permission")
public class Rbac0RolePermissionEntity extends Model<Rbac0RolePermissionEntity> {
    /**
     * 角色id
     */

    private Integer roleId;
    /**
     * 权限id
     */

    private Integer permsId;
    /**
     * 更新者
     */

    private Integer updateBy;
    /**
     * 创建者
     */

    private Integer createBy;
    /**
     * 更新时间
     */

    private Date updateTime;
    /**
     * 创建时间
     */

    private Date createTime;


}
