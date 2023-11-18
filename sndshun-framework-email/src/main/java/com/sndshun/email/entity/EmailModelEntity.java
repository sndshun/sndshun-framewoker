package com.sndshun.email.entity;


import java.util.Date;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 邮件主体(EmailModel)表实体类
 *
 * @author sndshun
 * @since 2023-11-16 20:29:07
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("email_model")
public class EmailModelEntity extends Model<EmailModelEntity> {
    /**
     * id
     */

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    /**
     * 主体
     */

    private String subject;
    /**
     * 发件人
     */

    private String fromAddress;
    /**
     * 收件人
     */

    private String receiveAddress;
    /**
     * 发送时间
     */

    private Date sendDate;
    /**
     * 是否已读
     */

    private Integer seen;
    /**
     * 优先级
     */

    private String priority;
    /**
     * 是否需要回执
     */

    private Integer replySign;
    /**
     * 邮箱大小 bit
     */

    private Long size;
    /**
     * 模板id 0:不使用模板
     */

    private Integer templateId;
    /**
     * 模板元数据
     */

    private String templateData;
    /**
     * 正文
     */

    private String content;
    /**
     * 第几封邮件
     */

    private Integer messageNumber;
    /**
     * 附件
     */

    private String attachment;
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
