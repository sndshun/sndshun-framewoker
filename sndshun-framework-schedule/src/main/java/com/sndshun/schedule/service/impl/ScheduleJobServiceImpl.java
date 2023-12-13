package com.sndshun.schedule.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sndshun.commons.config.ResultCode;
import com.sndshun.commons.tools.Result;
import com.sndshun.commons.tools.StringUtils;
import com.sndshun.schedule.entity.ScheduleJobEntity;
import com.sndshun.schedule.service.ScheduleJobService;
import com.sndshun.schedule.mapper.ScheduleJobMapper;
import com.sndshun.schedule.utils.ScheduleUtils;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



/**
 * @author maple
 * @description 针对表【schedule_job(定时任务)】的数据库操作Service实现
 * @createDate 2023-12-13 11:07:34
 */

@Slf4j
@Service
public class ScheduleJobServiceImpl extends ServiceImpl<ScheduleJobMapper, ScheduleJobEntity> implements ScheduleJobService {

    private final Scheduler scheduler;

    @Autowired
    public ScheduleJobServiceImpl(Scheduler scheduler) {
        this.scheduler = scheduler;
    }



    @Override
    public Page<ScheduleJobEntity> getScheduleJobPageCache(Page<ScheduleJobEntity> page, ScheduleJobEntity scheduleJob) {
        return super.page(page, new QueryWrapper<>(scheduleJob));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<?> saveJob(ScheduleJobEntity scheduleJob) {
        int insert = this.baseMapper.insert(scheduleJob);
        StringUtils.isIntLessThanOne(insert, ResultCode.FAIL);
        //添加定时任务
        ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
        return Result.ok(ResultCode.SUCCESS);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<?> updateJob(ScheduleJobEntity scheduleJob) {
        int update = this.baseMapper.updateById(scheduleJob);
        StringUtils.isIntLessThanOne(update, ResultCode.FAIL);
        //修改定时任务
        ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
        return Result.ok(ResultCode.SUCCESS);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<?> deleteJobById(Long jobId) {
        int delete = this.baseMapper.deleteById(jobId);
        StringUtils.isIntLessThanOne(delete, ResultCode.FAIL);
        //删除定时任务
        ScheduleUtils.deleteScheduleJob(scheduler, jobId);
        return Result.ok(ResultCode.SUCCESS);
    }

    @Override
    public Result<?> runJobById(Long jobId) {
        try {
            ScheduleJobEntity jobEntity = this.baseMapper.selectById(jobId);
            StringUtils.isEmpty(jobEntity, ResultCode.SCHEDULE_RUN_SEARCH_ERROR);
            ScheduleUtils.run(scheduler, jobEntity);
            return Result.ok(ResultCode.SUCCESS);
        } catch (Exception e) {
            return Result.error(ResultCode.SCHEDULE_RUN_ERROR);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<?> updateJobStatusById(Long jobId, Integer status) {
        try {
            LambdaUpdateWrapper<ScheduleJobEntity> wrapper = new LambdaUpdateWrapper<>();
            wrapper.set(ScheduleJobEntity::getStatus, status);
            wrapper.eq(ScheduleJobEntity::getJobId, jobId);
            int update = this.baseMapper.update(wrapper);

            StringUtils.isIntLessThanOne(update, ResultCode.FAIL);

            selectRequiredAccordingToStatus(status, jobId);

            return Result.ok(ResultCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(ResultCode.FAIL);
        }
    }

    /**
     * 根据状态选择所需的
     *
     * @param status 状态
     */
    private void selectRequiredAccordingToStatus(Integer status, Long jobId) {
        if (status == 1) {
            ScheduleUtils.resumeJob(scheduler, jobId);
        } else {
            ScheduleUtils.pauseJob(scheduler, jobId);
        }
    }
}




