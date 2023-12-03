package com.sndshun.blog.controller.endpoint;


import com.sndshun.blog.service.BlogMenuService;
import com.sndshun.commons.tools.Result;
import org.springframework.web.bind.annotation.GetMapping;
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

    private static final long HOME_MENU=1;
    /**
     * 服务对象
     */
    @Resource
    private BlogMenuService blogMenuService;


    /**
     * 查询所有菜单树形结构展示
     * @return {@link Result }<{@link ? }>
     * @author sndshun
     * @date 2023/11/28 11:43:39
     */
    @GetMapping("tree")
    public Result<?> selectTree() {
        return Result.ok(this.blogMenuService.blogMenuTree());
    }


    /**
     * 查询所有博客导航栏
     * @return {@link Result }<{@link ? }>
     * @author sndshun
     * @date 2023/12/01 10:31:15
     */
    @GetMapping("nav")
    public Result<?> menu() {
        return Result.ok(blogMenuService.blogMenuByParentId(HOME_MENU));
    }

}
