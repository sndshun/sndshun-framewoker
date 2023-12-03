package com.sndshun.blog.controller.endpoint;

import com.sndshun.blog.entity.BlogTagEntity;
import com.sndshun.blog.service.BlogTagService;
import com.sndshun.commons.tools.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/blog/endpoint/tag")
public class BlogTagEndpointController {
    @Resource
    private BlogTagService blogTagService;

    /**
     * 查询所有标签
     * @return {@link Result }<{@link List }<{@link BlogTagEntity }>>
     * @author sndshun
     * @date 2023/12/03 07:47:42
     */
    @GetMapping("/group")
    public Result<Map<String,List<BlogTagEntity>>> allGroupCategory() {
        List<BlogTagEntity> all = blogTagService.getAll();
        Map<String, List<BlogTagEntity>> collect = all.stream().collect(Collectors.groupingBy(BlogTagEntity::getCategoryName, Collectors.toList()));
        return Result.ok(collect);
    }


}
