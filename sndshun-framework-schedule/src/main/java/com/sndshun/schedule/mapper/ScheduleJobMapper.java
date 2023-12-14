package com.sndshun.schedule.mapper;

import com.sndshun.schedule.entity.ScheduleJobEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author maple
* @description 针对表【schedule_job(定时任务)】的数据库操作Mapper
* @createDate 2023-12-13 11:07:34
* @Entity com.sndshun.schedule.entity.ScheduleJobEntity
*/
@Mapper
public interface ScheduleJobMapper extends BaseMapper<ScheduleJobEntity> {

}




