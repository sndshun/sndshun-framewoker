package com.sndshun.rbac0.entity;


import java.util.Date;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.*;

/**
 * 临时用户表(User)表实体类
 *
 * @author sndshun
 * @since 2024-02-06 06:48:54
 */

@Getter
@Setter
@ToString
@TableName("user")
public class UserEntity extends Model<UserEntity>{
    /**
     * 用户id
     */

    private Integer id;
    /**
     * 用户昵称
     */

    private String name;
    /**
     * 联系电话
     */

    private String phone;
    /**
     * 用户性别(0：未知，1：男，2：女)
     */

    private Integer sex;
    /**
     * 联系地址
     */

    private String address;
    /**
     * 联系邮箱
     */

    private String email;
    /**
     * 头像
     */

    private String profile;
    /**
     * 更新时间
     */

    private Date updateTime;
    /**
     * 创建时间
     */

    private Date createTime;


}
