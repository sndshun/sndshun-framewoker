package com.sndshun.blog.controller.endpoint;

import com.sndshun.blog.service.BlogArchiveService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/blog/endpoint/archive")
public class BlogArchiveEndpointController {
    @Resource
    private BlogArchiveService blogArchiveService;



}
