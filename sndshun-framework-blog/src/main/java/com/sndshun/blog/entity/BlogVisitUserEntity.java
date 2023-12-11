package com.sndshun.blog.entity;


import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 访客用户(BlogVisitUser)表实体类
 *
 * @author sndshun
 * @since 2023-12-11 13:33:06
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("blog_visit_user")
public class BlogVisitUserEntity extends Model<BlogVisitUserEntity> {
    /**
     * 主键
     */
    @TableId
    private Long vid;
    /**
     * 访客识别码
     */

    private String uuid;
    /**
     * IP
     */

    private String ip;
    /**
     * 国家
     */

    private String country;
    /**
     * 省
     */

    private String prov;
    /**
     * 城市
     */

    private String city;
    /**
     * 经度
     */

    private String lat;
    /**
     * 纬度
     */

    private String lng;
    /**
     * 创建时间
     */

    private Date createTime;


}
