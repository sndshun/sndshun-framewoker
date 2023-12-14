package com.sndshun.rbac0.entity;


import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.List;

import com.github.yulichang.annotation.EntityMapping;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户角色表(Rbac0Role)表实体类
 *
 * @author sndshun
 * @since 2023-12-14 03:50:51
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("rbac0_role")
public class Rbac0RoleEntity extends Model<Rbac0RoleEntity> {
    /**
     * 角色id
     */

    private Integer id;
    /**
     * 角色
     */

    private String roleName;
    /**
     * 描述
     */

    private String description;
    /**
     * 修改者
     */

    private Integer updateBy;
    /**
     * 创建者
     */

    private Integer createBy;

    private Date updateTime;

    private Date createTime;

}
