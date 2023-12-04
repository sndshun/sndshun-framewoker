package com.sndshun.blog.controller.endpoint;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sndshun.blog.entity.BlogPostEntity;
import com.sndshun.blog.service.BlogPostService;
import com.sndshun.commons.tools.Result;
import com.sndshun.web.pojo.QueryPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blog/endpoint/post")
public class BlogPostEndpointController {

    private final BlogPostService blogPostService;

    @Autowired
    public BlogPostEndpointController(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }

    @GetMapping("page")
    public Result<?> getPostPage(QueryPage page) {
        Page<BlogPostEntity> query = new Page<BlogPostEntity>().setCurrent(page.getCurrent()).setSize(page.getSize());
        return Result.ok(blogPostService.getPostPage(query));
    }


}
