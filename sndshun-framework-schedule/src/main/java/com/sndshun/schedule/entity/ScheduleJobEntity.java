package com.sndshun.schedule.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 定时任务(ScheduleJob)表实体类
 *
 * @author sndshun
 * @since 2023-12-14 01:14:14
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("schedule_job")
public class ScheduleJobEntity extends Model<ScheduleJobEntity> {
     /**
     * 任务id
     */
     @TableId
    private Long jobId;
     /**
     * spring bean名称;全路径-包名.类名
     */

    private String beanName;
     /**
     * 方法名
     */

    private String methodName;
     /**
     * 参数
     */

    private String params;
     /**
     * cron表达式
     */

    private String cron;
     /**
     * 分组;
     */
     @TableField("`group`")
    private String group;
     /**
     * 任务状态(0：启动 1：未启动)
     */
     @TableField("`status`")
    private Integer status;
     /**
     * 备注
     */

    private String remark;
     /**
     * 租户号;
     */

    private Long tenantId;
     /**
     * 乐观锁;
     */

    private Integer version;
     /**
     * 逻辑删除;0：正常 1：删除
     */

    private Integer logicDelete;
     /**
     * 创建人;
     */

    private Long createdBy;
     /**
     * 创建时间;
     */

    private Date createdTime;
     /**
     * 最后更新人;
     */

    private Long updatedBy;
     /**
     * 最后更新时间;
     */

    private Date updatedTime;


}
