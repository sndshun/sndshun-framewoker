package com.sndshun.schedule.runnable;

import com.sndshun.web.utils.ContextUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

/**
 * 调度运行
 *
 * @author maple
 */
public class ScheduleRunnable implements Runnable {
    private Object target;

    private Method method;

    private String params;

    public ScheduleRunnable(String beanName, String methodName, String params) throws NoSuchMethodException, SecurityException {
        System.out.println(beanName);
        this.target = ContextUtils.getBean(beanName);
        this.params = params;
        if (StringUtils.hasText(params)) {
            this.method = target.getClass().getDeclaredMethod(methodName, String.class);
        } else {
            this.method = target.getClass().getDeclaredMethod(methodName);
        }
    }

    @Override
    public void run() {
        try {
            ReflectionUtils.makeAccessible(method);
            if (StringUtils.hasText(params)) {
                method.invoke(target, params);
            } else {
                method.invoke(target);
            }
        } catch (Exception e) {
            throw new RuntimeException("执行定时任务失败", e);
        }
    }
}
