package com.sndshun.schedule.entity;


import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 日志(ScheduleJobLog)表实体类
 *
 * @author sndshun
 * @since 2023-12-13 14:22:46
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("schedule_job_log")
public class ScheduleJobLogEntity extends Model<ScheduleJobLogEntity> {
    /**
     * 任务日志id
     */
    @TableId
    private Long logId;
    /**
     * 任务id
     */

    private Long jobId;
    /**
     * spring bean名称
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
     * 任务执行结果
     */

    private Integer status;
    /**
     * 异常信息
     */

    private String error;
    /**
     * 耗时（单位：毫秒）
     */

    private Integer times;
    /**
     * 创建时间
     */

    private Date createTime;


}
