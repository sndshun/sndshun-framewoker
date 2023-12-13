package com.sndshun.schedule.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sndshun.commons.tools.Result;
import com.sndshun.schedule.entity.ScheduleJobEntity;
import com.baomidou.mybatisplus.extension.service.IService;

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
    Boolean saveJob(ScheduleJobEntity scheduleJob);

    /**
     * 修改定时任务
     *
     * @param scheduleJob 对象
     * @return 结果
     */
    Boolean updateJob(ScheduleJobEntity scheduleJob);

    /**
     * 删除定时任务
     *
     * @param jobId 定时任务ID
     * @return 结果
     */
    Boolean deleteJobById(Long jobId);

    /**
     * 立即执行
     *
     * @param jobId 定时任务ID
     * @return 结果
     */
    Boolean runJobById(Long jobId);

    /**
     * 暂停任务
     * @param id 编号
     * @return {@link Boolean }
     * @author sndshun
     * @date 2023/12/13 10:53:26
     */
    Boolean pauseJobById(Long id);

    /** 恢复任务
     * @param id 编号
     * @return {@link Boolean }
     * @author sndshun
     * @date 2023/12/13 10:54:07
     */
    Boolean resumeJobById(Long id);

}
