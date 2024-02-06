package com.sndshun.rbac0.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 用户角色表(Rbac0Role)表实体类
 *
 * @author sndshun
 * @since 2023-12-14 03:50:51
 */

@Getter
@Setter
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
