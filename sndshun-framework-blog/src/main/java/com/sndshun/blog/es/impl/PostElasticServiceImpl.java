package com.sndshun.blog.es.impl;

import com.sndshun.blog.es.PostElasticService;
import com.sndshun.blog.pojo.document.BlogPostDocument;
import com.sndshun.blog.service.BlogCategoryService;
import com.sndshun.blog.service.BlogPostService;
import org.elasticsearch.index.query.*;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 实现类
 *
 */
@Service
public class PostElasticServiceImpl implements PostElasticService {
    //复杂用这个
    private final ElasticsearchRestTemplate elasticsearchRestTemplate;
    private final BlogCategoryService blogCategoryService;
    private final BlogPostService blogPostService;
    //简单用这个

    @Autowired
    public PostElasticServiceImpl(ElasticsearchRestTemplate elasticsearchRestTemplate, BlogCategoryService blogCategoryService, BlogPostService blogPostService) {
        this.elasticsearchRestTemplate = elasticsearchRestTemplate;
        this.blogCategoryService = blogCategoryService;
        this.blogPostService = blogPostService;
    }

    @Override
    public List<BlogPostDocument> search(String keyword) {
        //创建查询构建器创建查询构建器
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        //创建布尔查询
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        //创建多字段查询
        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(keyword, "title", "content", "summary", "category", "authorName");
        //设置多字段查询的相关属性
        multiMatchQueryBuilder.field("title", 10);
        multiMatchQueryBuilder.field("content", 5);
        //匹配标签
        List<String> terms = new ArrayList<>();
        terms.add(keyword);
        TermsSetQueryBuilder tagsQueryBuilder = new TermsSetQueryBuilder("tags", terms).setMinimumShouldMatchScript(new Script("Math.min(params.num_terms, doc['tagCount'].value)"));
        boolQueryBuilder.should(tagsQueryBuilder);
        //将多字段查询添加到布尔查询中
        boolQueryBuilder.should(multiMatchQueryBuilder);
        //将布尔查询添加到查询构建器中
        queryBuilder.withQuery(boolQueryBuilder)
                .withPageable(PageRequest.of(0, 20));
        //高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder()
                .field("title")
                .field("content")
                .field("tags")
                .field("summary")
                .field("category")
                .field("authorName");

        queryBuilder.withHighlightBuilder(highlightBuilder);
        //执行查询
        NativeSearchQuery searchQuery = queryBuilder.build();
        SearchHits<BlogPostDocument> search = elasticsearchRestTemplate.search(searchQuery, BlogPostDocument.class);
        //处理结果，将高亮文本替换
        List<BlogPostDocument> list = new ArrayList<>();
        for (SearchHit<BlogPostDocument> hit : search.getSearchHits()) {
            BlogPostDocument content = hit.getContent();
            if (hit.getHighlightFields().get("title") != null) {
                content.setTitle(hit.getHighlightFields().get("title").get(0));
            }
            if (hit.getHighlightFields().get("tags") != null) {
                content.setContent(hit.getHighlightFields().get("tags").get(0));
            }
            if (hit.getHighlightFields().get("category") != null) {
                content.setSummary(hit.getHighlightFields().get("category").get(0));
            }
            if (hit.getHighlightFields().get("authorName") != null) {
                content.setAuthorName(hit.getHighlightFields().get("authorName").get(0));
            }
            if (hit.getHighlightFields().get("summary") != null) {
                content.setSummary(String.join("", hit.getHighlightFields().get("summary")));
            }else {
                content.setSummary("");
            }
            if (hit.getHighlightFields().get("content") != null) {
                content.setContent(String.join("", hit.getHighlightFields().get("content")));
            } else {
                content.setContent("");
            }
            list.add(content);
        }
        return list;
    }

    @Override
    public boolean createIndex() {
        IndexOperations indexOps = elasticsearchRestTemplate.indexOps(BlogPostDocument.class);
        boolean flag = indexOps.create();
        if (flag) {
            //推送映射
            Document mapping = indexOps.createMapping();
            return indexOps.putMapping(mapping);
        }
        return false;
    }

    @Override
    public boolean deleteIndex(String index) {
        IndexOperations indexOps = elasticsearchRestTemplate.indexOps(BlogPostDocument.class);
        return indexOps.delete();
    }

    @Override
    public BlogPostDocument selectAllById(String id, String index) {
        IdsQueryBuilder idsQueryBuilder = QueryBuilders.idsQuery();
        //根据id
        idsQueryBuilder.addIds(id);
        //查询
        NativeSearchQuery query = new NativeSearchQueryBuilder().withQuery(idsQueryBuilder).build();
        SearchHit<BlogPostDocument> documentSearchHit = elasticsearchRestTemplate.searchOne(query, BlogPostDocument.class, IndexCoordinates.of(index));
        return documentSearchHit.getContent();
    }

    @Override
    public void deleteById(Serializable id) {
        this.elasticsearchRestTemplate.delete(id.toString(), BlogPostDocument.class);
    }

    @Override
    public List<BlogPostDocument> searchHighlight(String keyword, String index) {
        List<BlogPostDocument> blogPostDocuments = new ArrayList<>();
        //匹配查询
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("title", keyword);
        //高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("title");
        highlightBuilder.field("content");
        highlightBuilder.requireFieldMatch(false);//多个高亮关闭
        //检索
        NativeSearchQuery query = new NativeSearchQueryBuilder().withQuery(matchQueryBuilder).withHighlightBuilder(highlightBuilder).build();
        //结果
        SearchHits<BlogPostDocument> search = elasticsearchRestTemplate.search(query, BlogPostDocument.class, IndexCoordinates.of(index));
        for (SearchHit<BlogPostDocument> searchHit : search) {

            BlogPostDocument content = searchHit.getContent();

            //将高亮的字段取出来
            List<String> requestBody = searchHit.getHighlightField("title");
            String highText = "";
            for (String s : requestBody) {
                highText = s;
            }
            //重新对字段赋值
            content.setTitle(highText);
            blogPostDocuments.add(content);
        }
        return blogPostDocuments;
    }

    @Override
    public List<BlogPostDocument> selectPage(int page, int size, String index) {
        List<BlogPostDocument> blogPostDocumentList = new ArrayList<>();
        try {
            NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
            builder.withPageable(PageRequest.of(page - 1, size));

            NativeSearchQuery query = builder.build();
            SearchHits<BlogPostDocument> search = elasticsearchRestTemplate.search(query, BlogPostDocument.class, IndexCoordinates.of(index));

            search.forEach((hits) -> blogPostDocumentList.add(hits.getContent()));
            return blogPostDocumentList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<BlogPostDocument> selectByViewCountDesc(String index) {
        List<BlogPostDocument> blogPostDocuments = new ArrayList<>();
        try {
            NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        } catch (Exception e) {

        }
        return null;
    }

    @Override
    public List<BlogPostDocument> selectCombinedSearch(String value1, String value2, String index) {
        List<BlogPostDocument> blogPostDocuments = new ArrayList<>();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        // must表示同时满足，should满足其中一个，must_not表示同时不满足
        boolQueryBuilder.must(QueryBuilders.matchQuery("title", value1));
        boolQueryBuilder.must(QueryBuilders.matchQuery("title", value2));
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        builder.withFilter(boolQueryBuilder);
        NativeSearchQuery query = builder.build();
        SearchHits<BlogPostDocument> search = elasticsearchRestTemplate.search(query, BlogPostDocument.class, IndexCoordinates.of(index));
        search.forEach(min -> {
            blogPostDocuments.add(min.getContent());
        });
        return blogPostDocuments;
    }

}
