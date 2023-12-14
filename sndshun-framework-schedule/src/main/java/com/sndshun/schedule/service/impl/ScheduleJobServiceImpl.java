package com.sndshun.schedule.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sndshun.commons.config.ResultCode;
import com.sndshun.commons.tools.StringUtils;
import com.sndshun.schedule.core.QuartzManager;
import com.sndshun.schedule.core.constant.Status;
import com.sndshun.schedule.entity.ScheduleJobEntity;
import com.sndshun.schedule.mapper.ScheduleJobMapper;
import com.sndshun.schedule.service.ScheduleJobService;
import lombok.extern.slf4j.Slf4j;
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

    private final QuartzManager quartzManager;

    @Autowired
    public ScheduleJobServiceImpl(QuartzManager quartzManager) {
        this.quartzManager = quartzManager;
    }


    @Override
    public Page<ScheduleJobEntity> getScheduleJobPageCache(Page<ScheduleJobEntity> page, ScheduleJobEntity scheduleJob) {
        return super.page(page, new QueryWrapper<>(scheduleJob));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean saveJob(ScheduleJobEntity scheduleJob) {
        int insert = this.baseMapper.insert(scheduleJob);
        StringUtils.isIntLessThanOne(insert, ResultCode.FAIL);
        //添加定时任务
        quartzManager.addJob(scheduleJob);
        return insert > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateJob(ScheduleJobEntity scheduleJob) {
        int update = this.baseMapper.updateById(scheduleJob);
        StringUtils.isIntLessThanOne(update, ResultCode.FAIL);
        //修改定时任务
        quartzManager.updateJobCron(scheduleJob);
        return update > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteJobById(Long jobId) {
        int delete = this.baseMapper.deleteById(jobId);
        StringUtils.isIntLessThanOne(delete, ResultCode.FAIL);
        //删除定时任务
        quartzManager.deleteJob(jobId);
        return delete > 0;
    }

    @Override
    public Boolean runJobById(Long jobId) {
        try {
            quartzManager.runJobNow(jobId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean pauseJobById(Long id) {
        LambdaUpdateWrapper<ScheduleJobEntity> wrapper = new LambdaUpdateWrapper<>();
        wrapper.set(ScheduleJobEntity::getStatus, Status.PAUSE.getValue());
        wrapper.eq(ScheduleJobEntity::getJobId, id);
        boolean update = super.update(wrapper);
        quartzManager.pauseJob(id);
        return update;
    }

    @Override
    public Boolean resumeJobById(Long id) {
        LambdaUpdateWrapper<ScheduleJobEntity> wrapper = new LambdaUpdateWrapper<>();
        wrapper.set(ScheduleJobEntity::getStatus, Status.WAIT.getValue());
        wrapper.eq(ScheduleJobEntity::getJobId, id);
        boolean update = super.update(wrapper);
        quartzManager.resumeJob(id);
        return update;
    }
}




