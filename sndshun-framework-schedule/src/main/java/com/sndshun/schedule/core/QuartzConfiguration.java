package com.sndshun.schedule.core;

import org.quartz.Scheduler;
import org.quartz.spi.JobFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.annotation.Resource;

@Configuration
public class QuartzConfiguration {

    @Resource
    private JobFactory jobFactory;

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        try {
            schedulerFactoryBean.setOverwriteExistingJobs(true);
            schedulerFactoryBean.setJobFactory(jobFactory); //使用自定义工厂
        } catch (Exception e) {
            e.printStackTrace();
        }
        return schedulerFactoryBean;
    }


    // 创建schedule调度器
    //todo 调度异常后 暂停任务 数据库任务状态改为异常 发送邮件警告，邮件应该在全局异常拦截发吧 或者mq?redis6的mq?
    @Bean(name = "scheduler")
    public Scheduler scheduler() {
        return schedulerFactoryBean().getScheduler();
    }

}