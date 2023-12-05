package com.sndshun.blog.controller.endpoint;

import com.sndshun.blog.service.BlogCategoryService;
import com.sndshun.commons.tools.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blog/endpoint/category")
public class BlogCategoryEndpointController {

    private final BlogCategoryService blogCategoryService;

    @Autowired
    public BlogCategoryEndpointController(BlogCategoryService blogCategoryService) {
        this.blogCategoryService = blogCategoryService;
    }

    @GetMapping("tree")
    public Result<?> tree() {
        return Result.ok(blogCategoryService.getCategoryTree());
    }


}