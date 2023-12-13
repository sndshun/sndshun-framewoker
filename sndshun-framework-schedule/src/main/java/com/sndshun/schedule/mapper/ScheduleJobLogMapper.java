package com.sndshun.schedule.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sndshun.schedule.entity.ScheduleJobLogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 日志(ScheduleJobLog)表数据库访问层
 *
 * @author sndshun
 * @since 2023-12-13 14:22:46
 */
@Mapper
public interface ScheduleJobLogMapper extends BaseMapper<ScheduleJobLogEntity> {

}
