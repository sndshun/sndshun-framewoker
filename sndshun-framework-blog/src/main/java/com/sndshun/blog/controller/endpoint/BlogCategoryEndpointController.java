package com.sndshun.blog.controller.endpoint;

import com.sndshun.blog.annotation.VisitLog;
import com.sndshun.blog.enums.VisitEnum;
import com.sndshun.blog.service.BlogCategoryService;
import com.sndshun.blog.vo.BlogCategoryTreeVo;
import com.sndshun.commons.tools.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author sendshun
 */
@RestController
@RequestMapping("/blog/endpoint/category")
public class BlogCategoryEndpointController {

    private final BlogCategoryService blogCategoryService;

    @Autowired
    public BlogCategoryEndpointController(BlogCategoryService blogCategoryService) {
        this.blogCategoryService = blogCategoryService;
    }

    @VisitLog(VisitEnum.MOMENT)
    @GetMapping("tree")
    public Result<List<BlogCategoryTreeVo>> tree() {
        return Result.ok(blogCategoryService.getCategoryTreeCache());
    }


}
