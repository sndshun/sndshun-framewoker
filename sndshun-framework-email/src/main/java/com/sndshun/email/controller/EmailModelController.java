package com.sndshun.email.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sndshun.commons.tools.Result;
import com.sndshun.email.entity.EmailModelEntity;
import com.sndshun.email.service.EmailModelService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 邮件主体(EmailModel)表控制层
 *
 * @author sndshun
 * @since 2023-11-16 20:29:07
 */
@RestController
@RequestMapping("email")
public class EmailModelController {
    /**
     * 服务对象
     */
    @Resource
    private EmailModelService emailModelService;

    /**
     * 分页查询所有数据
     *
     * @param page       分页对象
     * @param emailModel 查询实体
     * @return 所有数据
     */
    @GetMapping
    public Result<?> selectAll(Page<EmailModelEntity> page, EmailModelEntity emailModel) {
        return Result.ok(this.emailModelService.page(page, new QueryWrapper<>(emailModel)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public Result<?> selectOne(@PathVariable Serializable id) {
        return Result.ok(this.emailModelService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param emailModel 实体对象
     * @return 新增结果
     */
    @PostMapping
    public Result<?> insert(@RequestBody EmailModelEntity emailModel) {
        return Result.ok(this.emailModelService.save(emailModel));
    }

    /**
     * 修改数据
     *
     * @param emailModel 实体对象
     * @return 修改结果
     */
    @PutMapping
    public Result<?> update(@RequestBody EmailModelEntity emailModel) {
        return Result.ok(this.emailModelService.updateById(emailModel));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public Result<?> delete(@RequestBody List<Long> idList) {
        return Result.ok(this.emailModelService.removeByIds(idList));
    }
}
