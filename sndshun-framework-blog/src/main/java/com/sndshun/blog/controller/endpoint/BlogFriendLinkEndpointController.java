package com.sndshun.blog.controller.endpoint;

import com.sndshun.blog.annotation.VisitLog;
import com.sndshun.blog.entity.BlogFriendLinkEntity;
import com.sndshun.blog.enums.VisitEnum;
import com.sndshun.blog.service.BlogFriendLinkService;
import com.sndshun.commons.tools.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author sndshun
 */
@RestController
@RequestMapping("/blog/endpoint/friendLink")
public class BlogFriendLinkEndpointController {

    private final BlogFriendLinkService blogFriendLinkService;

    @Autowired
    public BlogFriendLinkEndpointController(BlogFriendLinkService blogFriendLinkService) {
        this.blogFriendLinkService = blogFriendLinkService;
    }


    /**
     * 获取所有可展示友链 审核通过 启用的
     *
     * @return {@link Result }<{@link List }<{@link BlogFriendLinkEntity }>>
     * @author sndshun
     * @date 2023/12/03 07:51:34
     */
    @VisitLog(VisitEnum.FRIEND)
    @GetMapping("all")
    public Result<List<BlogFriendLinkEntity>> getAll() {
        return Result.ok(blogFriendLinkService.getAllCache());
    }


}
