package com.sndshun.schedule.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sndshun.schedule.entity.ScheduleJobLogEntity;
import com.sndshun.schedule.mapper.ScheduleJobLogMapper;
import com.sndshun.schedule.service.ScheduleJobLogService;
import org.springframework.stereotype.Service;

/**
 * 日志(ScheduleJobLog)表服务实现类
 *
 * @author sndshun
 * @since 2023-12-13 14:22:47
 */
@Service("scheduleJobLogService")
public class ScheduleJobLogServiceImpl extends ServiceImpl<ScheduleJobLogMapper, ScheduleJobLogEntity> implements ScheduleJobLogService {

}
