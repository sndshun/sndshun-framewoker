package com.sndshun.blog.es;


import com.sndshun.blog.entity.BlogPostEntity;
import com.sndshun.blog.pojo.document.BlogPostDocument;
import com.sndshun.blog.service.BlogCategoryService;
import com.sndshun.blog.service.BlogPostService;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

import org.springframework.data.elasticsearch.core.IndexedObjectInformation;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest
@Slf4j
public class es {

    @Resource
    private ElasticsearchRestTemplate elasticsearchRestTemplate;
    @Resource
    private BlogPostService blogPostService;
    @Resource
    private BlogCategoryService blogCategoryService;

    @Resource
    private PostElasticService postElasticService;

    @Test
    void test() {
        Map<Long, String> categoryNameMapCache = blogCategoryService.getCategoryNameMapCache();
        List<BlogPostEntity> list = blogPostService.list();

        List<BlogPostDocument> blogPostDocuments = BlogPostDocument.convertToBlogPostEntityList(list, categoryNameMapCache);
        List<IndexQuery> indexQueries = new ArrayList<>();

        for (BlogPostDocument blogPostDocument : blogPostDocuments) {
            // 创建索引查询对象 创建索引查询对象
            IndexQuery indexQuery = new IndexQueryBuilder().withId(blogPostDocument.getId().toString()).withObject(blogPostDocument).build();
            indexQueries.add(indexQuery);
        }
        List<IndexedObjectInformation> indexedObjectInformations = elasticsearchRestTemplate.bulkIndex(indexQueries, BlogPostDocument.class);
        indexedObjectInformations.forEach(System.out::println);
    }

    @Test
    void test03() {
        boolean index = postElasticService.createIndex();
        log.error("添加索引并映射的结果为：{}", index);
    }

    @Test
    void esDeleteTest04() {
        postElasticService.deleteById(754654);
    }

    @Test
    void esSearchKeyTest06() {
        BlogPostDocument blog = postElasticService.selectAllById("1", "blog");
        System.out.println(blog);
    }

    @Test
    void esDeleteIndexTest() {
        boolean flag = postElasticService.deleteIndex("blog");
        log.error("删除所得结果为：{}", flag);
    }

    @Test
    void esSearchKeyWordHighlight() {
        List<BlogPostDocument> list = postElasticService.searchHighlight("深入", "blog");
        list.forEach(blogPostDocument -> {
            log.error("匹配搜索并高亮的结果为：{}", blogPostDocument);
        });
    }

    @Test
    void esSelectPage() {
        List blog = postElasticService.selectPage(1, 10, "blog");
        blog.forEach(blogs -> {
            log.error("分页查询的结果为：{}", blogs);
        });
    }

    @Test
    void esSelectCombinedSearch() {
        List list = postElasticService.selectCombinedSearch("深入", "理解", "blog");
        list.forEach(bs -> {
            log.error("组合查询的结果为：{}", list);
        });
    }
}