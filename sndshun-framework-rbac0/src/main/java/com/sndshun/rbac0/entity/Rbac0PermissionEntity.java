package com.sndshun.rbac0.entity;


import java.util.Date;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 权限表(Rbac0Permission)表实体类
 *
 * @author sndshun
 * @since 2023-12-14 03:57:57
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("rbac0_permission")
public class Rbac0PermissionEntity extends Model<Rbac0PermissionEntity> {

    private Integer id;
    /**
     * 菜单，前端判断是否显示
     */

    private String menuCode;
    /**
     * 菜单释义
     */

    private String menuName;
    /**
     * 权限代码
     */

    private String permissionCode;
    /**
     * 权限释义
     */

    private String permissionName;
    /**
     * 是否本菜单必选权限, 1.必选 2非必选 通常是"列表"权限是必选
     */

    private Integer requiredPermission;
    /**
     * 最后修改时间
     */

    private Date updateTime;
    /**
     * 最后创建时间
     */

    private Date createTime;


}
