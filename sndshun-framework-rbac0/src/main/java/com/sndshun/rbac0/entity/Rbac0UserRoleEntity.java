package com.sndshun.rbac0.entity;


import java.util.Date;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户角色关联表(Rbac0UserRole)表实体类
 *
 * @author sndshun
 * @since 2023-12-14 03:50:51
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("rbac0_user_role")
public class Rbac0UserRoleEntity extends Model<Rbac0UserRoleEntity> {
    /**
     * id
     */

    private Integer id;
    /**
     * 用户id
     */

    private Integer userId;
    /**
     * 角色id
     */

    private Integer roleId;
    /**
     * 更新时间
     */

    private Date updateTime;
    /**
     * 创建时间
     */

    private Date createTime;
    /**
     * 更新者
     */

    private Integer updateBy;
    /**
     * 创建者
     */

    private Integer createBy;


}
