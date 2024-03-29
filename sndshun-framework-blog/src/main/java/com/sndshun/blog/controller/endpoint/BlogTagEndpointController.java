package com.sndshun.blog.controller.endpoint;

import com.sndshun.blog.annotation.VisitLog;
import com.sndshun.blog.entity.BlogTagEntity;
import com.sndshun.blog.enums.VisitEnum;
import com.sndshun.blog.service.BlogTagService;
import com.sndshun.commons.tools.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author sndshun
 */
@RestController
@RequestMapping("/blog/endpoint/tag")
public class BlogTagEndpointController {
    private final BlogTagService blogTagService;

    @Autowired
    public BlogTagEndpointController(BlogTagService blogTagService) {
        this.blogTagService = blogTagService;
    }

    /**
     * 查询所有标签
     *
     * @return {@link Result }<{@link List }<{@link BlogTagEntity }>>
     * @author sndshun
     * @date 2023/12/03 07:47:42
     */
    @VisitLog(VisitEnum.TAG)
    @GetMapping("/group")
    public Result<Map<String, List<BlogTagEntity>>> allGroupCategory() {
        List<BlogTagEntity> all = blogTagService.getAllCaChe();
        Map<String, List<BlogTagEntity>> collect = all.stream().collect(Collectors.groupingBy(BlogTagEntity::getCategoryName, Collectors.toList()));
        return Result.ok(collect);
    }

    @PostMapping
    public Result<Boolean> saveTag(@RequestBody BlogTagEntity tagEntity) {
        boolean result = this.blogTagService.save(tagEntity);
        return Result.ok(result);
    }

    @PutMapping
    public Result<Boolean> updTag(@RequestBody BlogTagEntity tagEntity) {
        boolean result = this.blogTagService.updateById(tagEntity);
        return Result.ok(result);
    }
}
