package com.sndshun.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sndshun.blog.mapper.ScheduleJobLogMapper;
import com.sndshun.blog.entity.ScheduleJobLogEntity;
import com.sndshun.blog.service.ScheduleJobLogService;
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
