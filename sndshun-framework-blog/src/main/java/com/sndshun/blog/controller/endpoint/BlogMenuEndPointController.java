package com.sndshun.blog.controller.endpoint;


import com.sndshun.blog.entity.BlogMenuEntity;
import com.sndshun.blog.service.BlogMenuService;
import com.sndshun.blog.pojo.vo.BlogMenuTreeVo;
import com.sndshun.commons.tools.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 博客菜单(BlogMenu)端点控制层
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
    private final BlogMenuService blogMenuService;
    @Autowired
    public BlogMenuEndPointController(BlogMenuService blogMenuService) {
        this.blogMenuService = blogMenuService;
    }

    /**
     * 查询所有菜单树形结构展示
     * @return {@link Result }<{@link ? }>
     * @author sndshun
     * @date 2023/11/28 11:43:39
     */
    @GetMapping("tree")
    public Result<List<BlogMenuTreeVo>> selectTree() {
        return Result.ok(this.blogMenuService.blogMenuTreeCaChe());
    }


    /**
     * 查询所有博客导航栏
     * @return {@link Result }<{@link ? }>
     * @author sndshun
     * @date 2023/12/01 10:31:15
     */
    @GetMapping("nav")
    public Result<List<BlogMenuEntity>> menu() {
        return Result.ok(blogMenuService.blogMenuByParentIdCache(HOME_MENU));
    }

}
