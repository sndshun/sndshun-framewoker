package com.sndshun.dict.entity;


import java.util.Date;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * 业务字典表(DictBiz)表实体类
 *
 * @author sndshun
 * @since 2023-11-17 09:34:25
 */
@TableName("dict_biz")
public class DictBizEntity extends Model<DictBizEntity> {
    /**
     * 主键
     */

    @TableId
    private Long id;
    /**
     * 父主键
     */

    private Long parentId;
    /**
     * 字典码
     */

    private String code;
    /**
     * 字典值
     */

    private String dictKey;
    /**
     * 字典名称
     */

    private String dictValue;
    /**
     * 排序
     */

    private Integer sort;
    /**
     * 字典备注
     */

    private String remark;
    /**
     * 是否已封存
     */

    private Integer isSealed;
    /**
     * 乐观锁
     */

    private Integer version;
    /**
     * 租户号
     */

    private Long tenantId;
    /**
     * 逻辑删除;0：正常 1：删除
     */

    private Integer logicDelete;
    /**
     * 创建人
     */

    private Long createdBy;
    /**
     * 创建时间
     */

    private Date createdTime;
    /**
     * 最后更新人
     */

    private Long updatedBy;
    /**
     * 最后更新时间
     */

    private Date updatedTime;

    public Long getId() {
        return id;
    }

    public DictBizEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getParentId() {
        return parentId;
    }

    public DictBizEntity setParentId(Long parentId) {
        this.parentId = parentId;
        return this;
    }

    public String getCode() {
        return code;
    }

    public DictBizEntity setCode(String code) {
        this.code = code;
        return this;
    }

    public String getDictKey() {
        return dictKey;
    }

    public DictBizEntity setDictKey(String dictKey) {
        this.dictKey = dictKey;
        return this;
    }

    public String getDictValue() {
        return dictValue;
    }

    public DictBizEntity setDictValue(String dictValue) {
        this.dictValue = dictValue;
        return this;
    }

    public Integer getSort() {
        return sort;
    }

    public DictBizEntity setSort(Integer sort) {
        this.sort = sort;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public DictBizEntity setRemark(String remark) {
        this.remark = remark;
        return this;
    }

    public Integer getIsSealed() {
        return isSealed;
    }

    public DictBizEntity setIsSealed(Integer isSealed) {
        this.isSealed = isSealed;
        return this;
    }

    public Integer getVersion() {
        return version;
    }

    public DictBizEntity setVersion(Integer version) {
        this.version = version;
        return this;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public DictBizEntity setTenantId(Long tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    public Integer getLogicDelete() {
        return logicDelete;
    }

    public DictBizEntity setLogicDelete(Integer logicDelete) {
        this.logicDelete = logicDelete;
        return this;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public DictBizEntity setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public DictBizEntity setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public DictBizEntity setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public DictBizEntity setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
        return this;
    }
}
