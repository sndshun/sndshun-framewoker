package com.sndshun.schedule.job;

import com.sndshun.schedule.constant.Constant;
import com.sndshun.schedule.entity.ScheduleJobEntity;
import com.sndshun.schedule.entity.ScheduleJobLogEntity;
import com.sndshun.schedule.runnable.ScheduleRunnable;
import com.sndshun.schedule.service.ScheduleJobLogService;
import com.sndshun.web.utils.ContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;
import java.util.concurrent.*;


/**
 * 任务调度
 *
 * @author maple
 */
@Slf4j
public class ScheduleJob extends QuartzJobBean {
    /**
     * 手动创建线程
     * corePoolSize:核心池大小
     * maximumPoolSize:最大池大小
     * keepAliveTime:保持活动时间
     * TimeUnit:时间单位为秒
     * ArrayBlockingQueue:阻塞队列
     */
    private static final ExecutorService SERVICE = new ThreadPoolExecutor(1, 1, 60L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1));

    @Override
    protected void executeInternal(JobExecutionContext context) {
        //获取定时任务对象
        ScheduleJobEntity scheduleJob = (ScheduleJobEntity) context.getMergedJobDataMap().get(Constant.JOB_PARAM_KEY);
        //通过bean注入获取到服务
        ScheduleJobLogService scheduleJobLogService = (ScheduleJobLogService) ContextUtils.getBean("scheduleJobLogService");
        //定时任务日志
        ScheduleJobLogEntity jobLog = new ScheduleJobLogEntity();
        jobLog.setJobId(scheduleJob.getJobId());
        jobLog.setBeanName(scheduleJob.getBeanName());
        jobLog.setMethodName(scheduleJob.getMethodName());
        jobLog.setParams(scheduleJob.getParams());
        jobLog.setCreateTime(new Date());
        //任务开始时间
        long startTime = System.currentTimeMillis();
        //执行任务
        log.info("任务准备执行，任务ID：{}", scheduleJob.getJobId());
        try {
            ScheduleRunnable task = new ScheduleRunnable(scheduleJob.getBeanName(), scheduleJob.getMethodName(), scheduleJob.getParams());
            Future<?> future = SERVICE.submit(task);
            future.get();
            //任务执行总时长
            long times = System.currentTimeMillis() - startTime;
            jobLog.setTimes((int) times);
            //任务执行结果
            jobLog.setStatus(1);
            log.info("任务执行成功，任务ID：{}，总共耗时：{} 毫秒", scheduleJob.getJobId(), times);
        } catch (Exception e) {
            //任务执行总时长
            long times = System.currentTimeMillis() - startTime;
            jobLog.setTimes((int) times);
            //任务执行结果
            jobLog.setStatus(0);
            jobLog.setError(e.toString());
            log.error("任务执行失败，任务ID：{}", scheduleJob.getJobId(), e);
        } finally {
            scheduleJobLogService.save(jobLog);
        }
    }
}
