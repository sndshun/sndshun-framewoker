package com.sndshun.blog.controller.endpoint;

import com.sndshun.blog.service.BlogCategoryService;
import com.sndshun.commons.tools.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/blog/endpoint/category")
public class BlogCategoryEndpointController {
    @Resource
    private BlogCategoryService blogCategoryService;

    @GetMapping("tree")
    public Result<?> tree() {
        return Result.ok(blogCategoryService.getCategoryTree());
    }


}
