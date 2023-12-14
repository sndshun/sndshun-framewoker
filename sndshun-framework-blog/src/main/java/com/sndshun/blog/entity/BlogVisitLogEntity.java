package com.sndshun.blog.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author maple
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("blog_visit_log")
public class BlogVisitLogEntity extends Model<BlogVisitLogEntity> {
    /**
     * 访问日志ID
     */
    private Long id;
    /**
     * 访客标识码
     */
    private String uuid;
    /**
     * 请求接口
     */
    private String uri;
    /**
     * 请求方式
     */
    private String method;
    /**
     * 请求参数
     */
    private String param;
    /**
     * 访问行为
     */
    private String behavior;
    /**
     * 访问内容
     */
    private String content;
    /**
     * 备注
     */
    private String remark;
    /**
     * ip
     */
    private String ip;
    /**
     * 操作系统
     */
    private String os;
    /**
     * 浏览器
     */
    private String browser;
    /**
     * 请求耗时（毫秒）
     */
    private Integer times;
    /**
     * 访问时间
     */
    private Date createTime;
    /**
     * user-agent用户代理 信息
     */
    private String userAgent;

    public BlogVisitLogEntity(String uuid, String uri, String method, String param, String behavior, String content, String remark, String ip,  String os, String browser, Integer times, Date createTime, String userAgent) {
        this.uuid = uuid;
        this.uri = uri;
        this.method = method;
        this.param = param;
        this.behavior = behavior;
        this.content = content;
        this.remark = remark;
        this.ip = ip;
        this.os = os;
        this.browser = browser;
        this.times = times;
        this.createTime = createTime;
        this.userAgent = userAgent;
    }

    public Long getId() {
        return id;
    }

    public BlogVisitLogEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUuid() {
        return uuid;
    }

    public BlogVisitLogEntity setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getUri() {
        return uri;
    }

    public BlogVisitLogEntity setUri(String uri) {
        this.uri = uri;
        return this;
    }

    public String getMethod() {
        return method;
    }

    public BlogVisitLogEntity setMethod(String method) {
        this.method = method;
        return this;
    }

    public String getParam() {
        return param;
    }

    public BlogVisitLogEntity setParam(String param) {
        this.param = param;
        return this;
    }

    public String getBehavior() {
        return behavior;
    }

    public BlogVisitLogEntity setBehavior(String behavior) {
        this.behavior = behavior;
        return this;
    }

    public String getContent() {
        return content;
    }

    public BlogVisitLogEntity setContent(String content) {
        this.content = content;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public BlogVisitLogEntity setRemark(String remark) {
        this.remark = remark;
        return this;
    }

    public String getIp() {
        return ip;
    }

    public BlogVisitLogEntity setIp(String ip) {
        this.ip = ip;
        return this;
    }

    public String getOs() {
        return os;
    }

    public BlogVisitLogEntity setOs(String os) {
        this.os = os;
        return this;
    }

    public String getBrowser() {
        return browser;
    }

    public BlogVisitLogEntity setBrowser(String browser) {
        this.browser = browser;
        return this;
    }

    public Integer getTimes() {
        return times;
    }

    public BlogVisitLogEntity setTimes(Integer times) {
        this.times = times;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public BlogVisitLogEntity setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public BlogVisitLogEntity setUserAgent(String userAgent) {
        this.userAgent = userAgent;
        return this;
    }
}
