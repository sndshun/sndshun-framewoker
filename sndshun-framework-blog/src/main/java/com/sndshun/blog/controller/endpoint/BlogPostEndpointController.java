package com.sndshun.blog.controller.endpoint;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sndshun.blog.annotation.VisitLog;
import com.sndshun.blog.entity.BlogPostEntity;
import com.sndshun.blog.enums.VisitEnum;
import com.sndshun.blog.service.BlogPostService;
import com.sndshun.commons.tools.Result;
import com.sndshun.web.pojo.QueryPage;
import com.sndshun.web.utils.JacksonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;



import java.util.List;
import java.util.Map;


/**
 * @author sndshun
 */
@RestController
@RequestMapping("/blog/endpoint/post")
public class BlogPostEndpointController {

    private final BlogPostService blogPostService;

    @Autowired
    public BlogPostEndpointController(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }

    /** 分页查询文章
     * @param page 页
     * @return {@link Result }<{@link ? }>
     * @author sndshun
     * @date 2023/12/05 09:36:29
     */
    @VisitLog(VisitEnum.INDEX)
    @GetMapping("page")
    public Result<?> getPostPage(QueryPage page) {
        Page<BlogPostEntity> query = new Page<BlogPostEntity>().setCurrent(page.getCurrent()).setSize(page.getSize());
        return Result.ok(blogPostService.getPostPage(query));
    }

    /**
     * 文章归档查询
     * @return {@link Result }<{@link ? }> 为了前端保证顺序用数组传递
     * @author sndshun
     * @date 2023/12/05 09:36:41
     */
    @VisitLog(VisitEnum.ARCHIVE)
    @Cacheable(cacheNames = "blog:post",key = "#root.methodName")
    @GetMapping("archive")
    public Result<?> getPostArchive() {
        ArrayNode arrayNode = JacksonUtil.createArrayNode();
        Map<Integer, List<BlogPostEntity>> postArchive = blogPostService.getPostArchive();
        postArchive.forEach((key,value)->{
            ObjectNode objectNode = JacksonUtil.createObjectNode();
            objectNode.put(String.valueOf(key),JacksonUtil.objToJsonNode(value));
            arrayNode.add(objectNode);
        });
        return Result.ok(arrayNode);
    }


}
