package com.sndshun.blog.entity;


import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableName;


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
     * 半径
     */
    private String radius;

    /**
     * 创建时间
     */

    private Date createTime;

    public String getUuid() {
        return uuid;
    }

    public BlogVisitUserEntity setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getIp() {
        return ip;
    }

    public BlogVisitUserEntity setIp(String ip) {
        this.ip = ip;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public BlogVisitUserEntity setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getProv() {
        return prov;
    }

    public BlogVisitUserEntity setProv(String prov) {
        this.prov = prov;
        return this;
    }

    public String getCity() {
        return city;
    }

    public BlogVisitUserEntity setCity(String city) {
        this.city = city;
        return this;
    }

    public String getLat() {
        return lat;
    }

    public BlogVisitUserEntity setLat(String lat) {
        this.lat = lat;
        return this;
    }

    public String getLng() {
        return lng;
    }

    public BlogVisitUserEntity setLng(String lng) {
        this.lng = lng;
        return this;
    }

    public String getRadius() {
        return radius;
    }

    public BlogVisitUserEntity setRadius(String radius) {
        this.radius = radius;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public BlogVisitUserEntity setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }
}
