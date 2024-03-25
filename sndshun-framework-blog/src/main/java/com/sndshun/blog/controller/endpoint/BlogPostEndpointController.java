package com.sndshun.blog.controller.endpoint;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sndshun.blog.annotation.VisitLog;
import com.sndshun.blog.entity.BlogPostEntity;
import com.sndshun.blog.enums.VisitEnum;
import com.sndshun.blog.es.BlogPostRepository;
import com.sndshun.blog.es.PostElasticService;
import com.sndshun.blog.pojo.document.BlogPostDocument;
import com.sndshun.blog.service.BlogPostService;
import com.sndshun.commons.tools.Result;
import com.sndshun.web.pojo.QueryPage;
import com.sndshun.web.utils.JacksonUtil;
//import org.elasticsearch.index.query.BoolQueryBuilder;
//import org.elasticsearch.index.query.QueryBuilders;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Marker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
//import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;


/**
 * 分类表(BlogCategory)端点控制层
 *
 * @author sndshun
 * @date 2023/12/12 01:48:52
 */

@RestController
@RequestMapping("/blog/endpoint/post")
@Slf4j
public class BlogPostEndpointController {


    private final BlogPostService blogPostService;

    private final PostElasticService postElasticService;

    @Autowired
    public BlogPostEndpointController(BlogPostService blogPostService, PostElasticService postElasticService) {
        this.blogPostService = blogPostService;
        this.postElasticService = postElasticService;
    }


    /**
     * 分页查询文章
     *
     * @param page 页
     * @return {@link Result }<{@link ? }>
     * @author sndshun
     * @date 2023/12/05 09:36:29
     */
    @VisitLog(VisitEnum.INDEX)
    @GetMapping("page")
    public Result<?> getPostPage(QueryPage page) {
        try {
            new NullPointerException();
        }catch (Exception e) {
            log.error(Marker.ANY_MARKER,e.fillInStackTrace());
        }
        Page<BlogPostEntity> query = new Page<BlogPostEntity>().setCurrent(page.getCurrent()).setSize(page.getSize());
        return Result.ok(blogPostService.getPostPageCache(query));
    }

    /**
     * 根据id查询文章详情
     *
     * @param id 编号
     * @return {@link Result }<{@link ? }>
     * @author sndshun
     * @date 2023/12/11 12:16:31
     */
    @GetMapping("/{id}")
    public Result<BlogPostEntity> getPostById(@PathVariable Long id) {
        return Result.ok(blogPostService.getPostByIdCache(id));
    }

    /**
     * 根据id查询文章内容
     *
     * @param id 编号
     * @return {@link Result }<{@link ? }>
     * @author sndshun
     * @date 2023/12/11 12:16:31
     */
    @GetMapping("/content/{id}")
    public Result<String> getPostContentById(@PathVariable Long id) {
        return Result.ok(blogPostService.getPostByIdCache(id).getContent());
    }

    /**
     * 文章归档查询
     *
     * @return {@link Result }<{@link ? }> 为了前端保证顺序用数组传递
     * @author sndshun
     * @date 2023/12/05 09:36:41
     */
    @VisitLog(VisitEnum.ARCHIVE)
    @Cacheable(cacheNames = "blog:post", key = "#root.methodName")
    @GetMapping("archive")
    public Result<ArrayNode> getPostArchive() {
        ArrayNode arrayNode = JacksonUtil.createArrayNode();
        Map<Integer, List<BlogPostEntity>> postArchive = blogPostService.getPostArchive();
        postArchive.forEach((key, value) -> {
            ObjectNode objectNode = JacksonUtil.createObjectNode();
            objectNode.put(String.valueOf(key), JacksonUtil.objToJsonNode(value));
            arrayNode.add(objectNode);
        });
        return Result.ok(arrayNode);
    }

    //todo 不合理的搜索接口
    @GetMapping("/search")
    public Result<List<BlogPostDocument>> searchBlogPost(@RequestParam("keyword") String keyword) {
        return Result.ok(postElasticService.search(keyword));
    }
}
