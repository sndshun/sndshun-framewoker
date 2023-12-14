package com.sndshun.schedule.core;

import com.sndshun.schedule.entity.ScheduleJobEntity;
import com.sndshun.schedule.mapper.ScheduleJobMapper;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * @author maple
 */
@Slf4j
@Component
public class QuartzManager {
    @Resource
    private ScheduleJobMapper scheduleJobMapper;

    @Resource
    private Scheduler scheduler;

    /**
     * 初始化 定时器
     */
    @PostConstruct
    public void init() {
        System.out.println("初始化定时任务 开始");
        List<ScheduleJobEntity> scheduleJobList = this.scheduleJobMapper.selectList(null);
        for (ScheduleJobEntity scheduleJob : scheduleJobList) {
            addJob(scheduleJob);
        }
        System.out.println("初始化定时任务 完毕");
    }

    /**
     * 添加任务
     *
     * @param task 任务
     * @author sndshun
     * @date 2023/12/13 10:02:36
     */
    public void addJob(ScheduleJobEntity task) {
        try {
            // 创建jobDetail实例，绑定Job实现类
            // 指明job的名称，所在组的名称，以及绑定job类
            Class<? extends Job> jobClass = (Class<? extends Job>) (Class.forName(task.getBeanName()).newInstance().getClass());
            // 任务名称和组构成任务key
            JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(task.getJobId().toString())
                    .withDescription(task.getRemark()).build();
            // 定义调度触发规则
            // 使用cornTrigger规则
            // 触发器key
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(task.getJobId().toString())
                    .startAt(DateBuilder.futureDate(1, DateBuilder.IntervalUnit.SECOND))
                    .withSchedule(CronScheduleBuilder.cronSchedule(task.getCron())).startNow().build();
            // 把作业和触发器注册到任务调度中
            scheduler.scheduleJob(jobDetail, trigger);
            // 启动
            if (!scheduler.isShutdown()) {
                scheduler.start();
            }
        } catch (SchedulerException e) {
            log.error("QuartzManager-addJob-----新增定时任务异常", e);
        } catch (Exception e) {
            log.error("QuartzManager-addJob-----未预料到的异常，可能是无法通过反射获取任务类", e);
        }
    }

    /**
     * 更新任务 触发
     *
     * @param task 任务
     * @author sndshun
     * @date 2023/12/13 10:02:49
     */
    public void updateJobCron(ScheduleJobEntity task) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(task.getJobId().toString());
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(task.getCron());
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (SchedulerException e) {
            log.error("QuartzManager-addJob-----更新定时任务异常", e);
        }
    }

    /**
     * 暂停任务
     *
     * @param taskKey 任务
     * @author sndshun
     * @date 2023/12/13 10:03:21
     */
    public void pauseJob(Serializable taskKey) {
        try {
            JobKey jobKey = JobKey.jobKey(taskKey.toString());
            scheduler.pauseJob(jobKey);
        } catch (SchedulerException e) {
            log.error("QuartzManager-addJob-----暂停定时任务异常", e);
        }
    }

    /**
     * 恢复任务
     *
     * @param taskKey 任务
     * @author sndshun
     * @date 2023/12/13 10:03:33
     */
    public void resumeJob(Serializable taskKey) {
        try {
            JobKey jobKey = JobKey.jobKey(taskKey.toString());
            scheduler.resumeJob(jobKey);
        } catch (SchedulerException e) {
            log.error("QuartzManager-addJob-----恢复定时任务异常", e);
        }
    }

    /**
     * 删除任务
     *
     * @param taskKey 任务
     * @author sndshun
     * @date 2023/12/13 10:03:50
     */
    public void deleteJob(Serializable taskKey) {
        try {
            JobKey jobKey = JobKey.jobKey(taskKey.toString());
            scheduler.deleteJob(jobKey);
        } catch (SchedulerException e) {
            log.error("QuartzManager-addJob-----删除定时任务异常", e);
        }
    }

    /**
     * 立即执行
     *
     * @param taskKey 任务
     * @author sndshun
     * @date 2023/12/13 10:04:34
     */
    public void runJobNow(Serializable taskKey) {
        try {
            JobKey jobKey = JobKey.jobKey(taskKey.toString());
            scheduler.triggerJob(jobKey);
        } catch (SchedulerException e) {
            log.error("QuartzManager-addJob-----立即执行定时任务异常", e);
        }
    }
}
