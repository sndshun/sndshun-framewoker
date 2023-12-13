package com.sndshun.schedule.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sndshun.commons.tools.Result;
import com.sndshun.schedule.entity.ScheduleJobEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sndshun.schedule.entity.ScheduleJobLogEntity;

import java.util.List;

/**
 * @author maple
 * @description 针对表【schedule_job(定时任务)】的数据库操作Service
 * @createDate 2023-12-13 11:07:34
 */
public interface ScheduleJobService extends IService<ScheduleJobEntity> {

    /**
     * 分页查询定时任务列表
     *
     * @param page           条件
     * @param scheduleJobLog 条件
     * @return 结果
     */
    Page<ScheduleJobEntity> getScheduleJobPageCache(Page<ScheduleJobEntity> page, ScheduleJobEntity scheduleJobLog);

    /**
     * 添加定时任务
     *
     * @param scheduleJob 对象
     * @return 结果
     */
    Result<?> saveJob(ScheduleJobEntity scheduleJob);

    /**
     * 修改定时任务
     *
     * @param scheduleJob 对象
     * @return 结果
     */
    Result<?> updateJob(ScheduleJobEntity scheduleJob);

    /**
     * 删除定时任务
     *
     * @param jobId 定时任务ID
     * @return 结果
     */
    Result<?> deleteJobById(Long jobId);

    /**
     * 运行定时任务
     *
     * @param jobId 定时任务ID
     * @return 结果
     */
    Result<?> runJobById(Long jobId);

    /**
     * 根据任务ID修改状态
     *
     * @param jobId  Id
     * @param status 状态
     * @return 结果
     */
    Result<?> updateJobStatusById(Long jobId, Integer status);

}
