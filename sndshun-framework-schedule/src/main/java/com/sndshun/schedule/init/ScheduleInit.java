package com.sndshun.schedule.init;

import com.sndshun.schedule.entity.ScheduleJobEntity;
import com.sndshun.schedule.service.ScheduleJobService;
import com.sndshun.schedule.utils.ScheduleUtils;
import lombok.extern.slf4j.Slf4j;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * 定时任务初始化
 *
 * @author maple
 */
@Slf4j
@Configuration
public class ScheduleInit {
    private final ScheduleJobService scheduleJobService;

    private final Scheduler scheduler;

    @Autowired
    public ScheduleInit(ScheduleJobService scheduleJobService, Scheduler scheduler) {
        this.scheduleJobService = scheduleJobService;
        this.scheduler = scheduler;
    }

    /**
     * 初始化 定时器
     */
    @PostConstruct
    public void init() {
        System.out.println("初始化定时器 开始");
        log.info("初始化定时器 开始");
        List<ScheduleJobEntity> scheduleJobList = this.scheduleJobService.list();
        for (ScheduleJobEntity scheduleJob : scheduleJobList) {
            CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(scheduler, scheduleJob.getJobId());
            //如果不存在，则创建
            if (cronTrigger == null) {
                ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
            } else {
                ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
            }
        }
        log.info("初始化定时器 完毕");
    }
}
