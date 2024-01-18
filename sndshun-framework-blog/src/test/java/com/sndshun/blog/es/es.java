package com.sndshun.blog.es;

import com.sndshun.blog.entity.BlogCategoryEntity;
import com.sndshun.blog.entity.BlogPostEntity;
import com.sndshun.blog.pojo.document.BlogPostDocument;
import com.sndshun.blog.service.BlogCategoryService;
import com.sndshun.blog.service.BlogPostService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexedObjectInformation;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class es {

    @Resource
    private ElasticsearchRestTemplate elasticsearchRestTemplate;
    @Resource
    private BlogPostService blogPostService;
    @Resource
    private BlogCategoryService blogCategoryService;

    @Test
    void test() {
        Map<Long, String> categoryNameMapCache = blogCategoryService.getCategoryNameMapCache();
        List<BlogPostEntity> list = blogPostService.list();

        List<BlogPostDocument> blogPostDocuments = BlogPostDocument.convertToBlogPostEntityList(list,categoryNameMapCache);
        List<IndexQuery> indexQueries = new ArrayList<>();
        for (BlogPostDocument blogPostDocument : blogPostDocuments) {
            // 创建索引查询对象 创建索引查询对象
            IndexQuery indexQuery = new IndexQueryBuilder().withId(blogPostDocument.getId().toString()).withObject(blogPostDocument).build();
            indexQueries.add(indexQuery);
        }
        List<IndexedObjectInformation> indexedObjectInformations = elasticsearchRestTemplate.bulkIndex(indexQueries, BlogPostDocument.class);
        indexedObjectInformations.forEach(System.out::println);
    }


}