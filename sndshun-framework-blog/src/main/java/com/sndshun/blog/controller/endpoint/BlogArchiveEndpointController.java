package com.sndshun.blog.controller.endpoint;

import com.sndshun.blog.service.BlogArchiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blog/endpoint/archive")
public class BlogArchiveEndpointController {

    private final BlogArchiveService blogArchiveService;

    @Autowired
    public BlogArchiveEndpointController(BlogArchiveService blogArchiveService) {
        this.blogArchiveService = blogArchiveService;
    }
}
