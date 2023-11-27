package com.sndshun.blog.controller.endpoint;


import com.sndshun.blog.service.BlogMenuService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 博客菜单(BlogMenu)表控制层
 *
 * @author sndshun
 * @since 2023-11-25 05:46:24
 */
@RestController
@RequestMapping("blog/endpoint/menu")
public class BlogMenuEndPointController {
    /**
     * 服务对象
     */
    @Resource
    private BlogMenuService blogMenuService;


}
