package com.sndshun.template.entity;


import java.util.Date;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 模板主体;(TemplateModel)表实体类
 *
 * @author sndshun
 * @since 2023-11-17 04:45:40
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("template_model")
public class TemplateModelEntity extends Model<TemplateModelEntity> {
     /**
     * id
     */
    
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
     /**
     * 模板名
     */
    
    private String name;
     /**
     * 模板类型
     */
    
    private Long type;
     /**
     * 模板内容
     */
    
    private String context;
     /**
     * 模板预览
     */
    
    private Byte[] preview;
     /**
     * 租户号
     */
    
    private Long tenantId;
     /**
     * 乐观锁
     */
    @Version
    private Integer version;
     /**
     * 逻辑删除;0：正常 1：删除
     */
    @TableLogic
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


    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    public Serializable pkVal() {
        return this.id;
    }
}
