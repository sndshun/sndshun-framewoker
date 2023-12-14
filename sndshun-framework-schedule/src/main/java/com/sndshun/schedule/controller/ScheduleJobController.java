package com.sndshun.schedule.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sndshun.commons.tools.Result;
import com.sndshun.schedule.entity.ScheduleJobEntity;
import com.sndshun.schedule.service.ScheduleJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

/**
 * 定时任务控制层
 *
 * @author maple
 */
@RestController
@RequestMapping("/blog/schedule")
public class ScheduleJobController {
    private final ScheduleJobService scheduleJobService;

    @Autowired
    public ScheduleJobController(ScheduleJobService scheduleJobService) {
        this.scheduleJobService = scheduleJobService;
    }

    /**
     * 分页查询所有数据
     *
     * @param page        分页对象
     * @param scheduleJob 查询实体
     * @return 所有数据
     */
    @GetMapping
    public Result<Page<ScheduleJobEntity>> selectPage(Page<ScheduleJobEntity> page, ScheduleJobEntity scheduleJob) {
        Page<ScheduleJobEntity> page1 = this.scheduleJobService.getScheduleJobPageCache(page, scheduleJob);
        return Result.ok(page1);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public Result<ScheduleJobEntity> selectOne(@PathVariable Serializable id) {
        return Result.ok(this.scheduleJobService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param scheduleJob 实体对象
     * @return 新增结果
     */
    @PostMapping
    public Result<?> insert(@RequestBody ScheduleJobEntity scheduleJob) {
        return Result.ok(this.scheduleJobService.saveJob(scheduleJob));
    }

    /**
     * 修改数据
     *
     * @param scheduleJob 实体对象
     * @return 修改结果
     */
    @PutMapping
    public Result<?> update(@RequestBody ScheduleJobEntity scheduleJob) {
        return Result.ok(this.scheduleJobService.updateJob(scheduleJob));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除结果
     */
    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        return Result.ok(this.scheduleJobService.deleteJobById(id));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping("?")
    public Result<Boolean> deleteBatch(@RequestBody List<Long> idList) {
        return Result.ok(this.scheduleJobService.removeByIds(idList));
    }

    /**
     * 运行任务
     *
     * @param id 主键
     * @return 运行结果
     */
    @PostMapping("/run/{id}")
    public Result<?> runTask(@PathVariable Long id) {
        return Result.ok(this.scheduleJobService.runJobById(id));
    }

    /**
     * 暂停任务
     *
     * @param jobId  Id
     * @return 结果
     */
    @PostMapping("/pause")
    public Result<?> pauseJob(@RequestParam("jobId") Long jobId) {
        return Result.ok(this.scheduleJobService.pauseJobById(jobId));
    }

    /**
     * 恢复任务
     *
     * @param jobId  Id
     * @return 结果
     */
    @PostMapping("/resume")
    public Result<?> resumeJob(@RequestParam("jobId") Long jobId) {
        return Result.ok(this.scheduleJobService.resumeJobById(jobId));
    }
}
