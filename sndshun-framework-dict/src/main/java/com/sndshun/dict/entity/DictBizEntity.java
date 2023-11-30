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
@Data
@AllArgsConstructor
@NoArgsConstructor
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

}
