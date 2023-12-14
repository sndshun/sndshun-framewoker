package com.sndshun.schedule.core;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/** 重新job工厂类 让spring自动注入调度任务需要注入的依赖
 * @author sndshun
 * @date 2023/12/13 09:48:28
 */

@Component
public class JobFactory extends AdaptableJobFactory {
    //这个对象Spring会帮我们自动注入进来,也属于Spring技术范畴.
    @Resource
    private AutowireCapableBeanFactory capableBeanFactory;

    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        //调用父类的方法
        Object jobInstance = super.createJobInstance(bundle);
        //进行注入
        capableBeanFactory.autowireBean(jobInstance);
        return jobInstance;
    }
}