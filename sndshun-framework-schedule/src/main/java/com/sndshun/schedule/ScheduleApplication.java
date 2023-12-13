package com.sndshun.schedule;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 定时任务
 *
 * @author maple
 */
@EnableAsync
@SpringBootApplication(scanBasePackages = "com.sndshun")
@MapperScan("com.sndshun.schedule.mapper")
public class ScheduleApplication {
    public static void main(String[] args) {
        SpringApplication.run(ScheduleApplication.class, args);
    }

}
