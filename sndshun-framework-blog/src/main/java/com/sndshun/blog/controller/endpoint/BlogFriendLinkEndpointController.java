package com.sndshun.blog.controller.endpoint;

import com.sndshun.blog.entity.BlogFriendLinkEntity;
import com.sndshun.blog.service.BlogFriendLinkService;
import com.sndshun.commons.tools.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/blog/endpoint/friendLink")
public class BlogFriendLinkEndpointController {
    @Resource
    private BlogFriendLinkService blogFriendLinkService;


    /**
     * 获取所有可展示友链 审核通过 启用的
     * @return {@link Result }<{@link List }<{@link BlogFriendLinkEntity }>>
     * @author sndshun
     * @date 2023/12/03 07:51:34
     */
    @GetMapping("all")
    public Result<List<BlogFriendLinkEntity>> getAll() {
        return Result.ok(blogFriendLinkService.getAll());
    }


}
