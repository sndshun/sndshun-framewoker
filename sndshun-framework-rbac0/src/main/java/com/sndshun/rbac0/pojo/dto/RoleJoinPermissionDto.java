package com.sndshun.rbac0.pojo.dto;

import com.sndshun.rbac0.entity.Rbac0PermissionEntity;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class RoleJoinPermissionDto {
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

    /**
     * 查询地址 (一对多)
     */
    private List<Rbac0PermissionEntity> permissions;

}
