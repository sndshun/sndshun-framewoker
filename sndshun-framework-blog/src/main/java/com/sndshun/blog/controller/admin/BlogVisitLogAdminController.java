package com.sndshun.blog.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sndshun.blog.annotation.VisitLog;
import com.sndshun.blog.entity.BlogVisitLogEntity;
import com.sndshun.blog.enums.VisitEnum;
import com.sndshun.blog.service.BlogVisitLogService;
import com.sndshun.commons.tools.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 访客日志控制层
 *
 * @author maple
 */
@RestController
@RequestMapping("/blog/admin/visit")
public class BlogVisitLogAdminController {

    private final BlogVisitLogService blogVisitLogService;

    @Autowired
    public BlogVisitLogAdminController(BlogVisitLogService blogVisitLogService) {
        this.blogVisitLogService = blogVisitLogService;
    }

    /**
     * 分页查询访客日志
     *
     * @param page         分页
     * @param blogVisitLog 实体类
     * @return 结果
     */
    @VisitLog(VisitEnum.VISIT_LOG_PAGE)
    @GetMapping
    public Result<?> getAllVisitLog(Page<BlogVisitLogEntity> page, BlogVisitLogEntity blogVisitLog) {
        LambdaQueryWrapper<BlogVisitLogEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.orderByDesc(BlogVisitLogEntity::getCreateTime);
        Page<BlogVisitLogEntity> result = this.blogVisitLogService.page(page, lambdaQueryWrapper);
        return Result.ok(result);
    }
}
