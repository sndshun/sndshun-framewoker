package com.sndshun.schedule.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sndshun.commons.tools.Result;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sndshun.schedule.entity.ScheduleJobLogEntity;
import com.sndshun.schedule.service.ScheduleJobLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

/**
 * 日志(ScheduleJobLog)表控制层
 *
 * @author sndshun
 * @since 2023-12-13 14:22:47
 */
@RestController
@RequestMapping("scheduleJobLog")
public class ScheduleJobLogController {
    /**
     * 服务对象
     */
    private final ScheduleJobLogService scheduleJobLogService;

    @Autowired
    public ScheduleJobLogController(ScheduleJobLogService scheduleJobLogService) {
        this.scheduleJobLogService = scheduleJobLogService;
    }

    /**
     * 分页查询所有数据
     *
     * @param page           分页对象
     * @param scheduleJobLog 查询实体
     * @return 所有数据
     */
    @GetMapping
    public Result<Page<ScheduleJobLogEntity>> selectPage(Page<ScheduleJobLogEntity> page, ScheduleJobLogEntity scheduleJobLog) {
        Page<ScheduleJobLogEntity> page1 = this.scheduleJobLogService.page(page, new QueryWrapper<>(scheduleJobLog));
        return Result.ok(page1);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public Result<ScheduleJobLogEntity> selectOne(@PathVariable Serializable id) {
        return Result.ok(this.scheduleJobLogService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param scheduleJobLog 实体对象
     * @return 新增结果
     */
    @PostMapping
    public Result<Boolean> insert(@RequestBody ScheduleJobLogEntity scheduleJobLog) {
        return Result.ok(this.scheduleJobLogService.save(scheduleJobLog));
    }

    /**
     * 修改数据
     *
     * @param scheduleJobLog 实体对象
     * @return 修改结果
     */
    @PutMapping
    public Result<Boolean> update(@RequestBody ScheduleJobLogEntity scheduleJobLog) {
        return Result.ok(this.scheduleJobLogService.updateById(scheduleJobLog));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除结果
     */
    @DeleteMapping("/{id}")
    public Result<Boolean> delete(@PathVariable Long id) {
        return Result.ok(this.scheduleJobLogService.removeById(id));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping("batch")
    public Result<Boolean> deleteBatch(@RequestBody List<Long> idList) {
        return Result.ok(this.scheduleJobLogService.removeByIds(idList));
    }
}
