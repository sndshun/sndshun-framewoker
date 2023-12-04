package com.sndshun.dict.entity;


import java.util.Date;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * 字典表(Dict)表实体类
 *
 * @author sndshun
 * @since 2023-11-17 09:34:24
 */

@TableName("dict")
public class DictEntity extends Model<DictEntity> {
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
     * 创建人
     */

    private Long createdBy;
    /**
     * 逻辑删除;0：正常 1：删除
     */

    private Integer logicDelete;
    /**
     * 最后更新人
     */

    private Long updatedBy;
    /**
     * 创建时间
     */

    private Date createdTime;
    /**
     * 最后更新时间
     */

    private Date updatedTime;


    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    public Serializable pkVal() {
        return this.id;
    }

    public Long getId() {
        return id;
    }

    public DictEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getParentId() {
        return parentId;
    }

    public DictEntity setParentId(Long parentId) {
        this.parentId = parentId;
        return this;
    }

    public String getCode() {
        return code;
    }

    public DictEntity setCode(String code) {
        this.code = code;
        return this;
    }

    public String getDictKey() {
        return dictKey;
    }

    public DictEntity setDictKey(String dictKey) {
        this.dictKey = dictKey;
        return this;
    }

    public String getDictValue() {
        return dictValue;
    }

    public DictEntity setDictValue(String dictValue) {
        this.dictValue = dictValue;
        return this;
    }

    public Integer getSort() {
        return sort;
    }

    public DictEntity setSort(Integer sort) {
        this.sort = sort;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public DictEntity setRemark(String remark) {
        this.remark = remark;
        return this;
    }

    public Integer getIsSealed() {
        return isSealed;
    }

    public DictEntity setIsSealed(Integer isSealed) {
        this.isSealed = isSealed;
        return this;
    }

    public Integer getVersion() {
        return version;
    }

    public DictEntity setVersion(Integer version) {
        this.version = version;
        return this;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public DictEntity setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public Integer getLogicDelete() {
        return logicDelete;
    }

    public DictEntity setLogicDelete(Integer logicDelete) {
        this.logicDelete = logicDelete;
        return this;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public DictEntity setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public DictEntity setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public DictEntity setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
        return this;
    }
}
