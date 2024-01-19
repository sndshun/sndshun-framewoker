package com.sndshun.blog.es.impl;

import com.sndshun.blog.es.PostElasticService;
import com.sndshun.blog.pojo.document.BlogPostDocument;
import com.sndshun.blog.service.BlogCategoryService;
import com.sndshun.blog.service.BlogPostService;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.IdsQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.HighlightQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * 实现类
 *
 * @param <M>  泛型
 * @param <T>  泛型
 * @param <ID> 泛型
 */
@Service
public class PostElasticServiceImpl<M extends ElasticsearchRepository<T, ID>, T, ID> implements PostElasticService<T, ID> {
    //复杂用这个
    private final ElasticsearchRestTemplate elasticsearchRestTemplate;
    private final BlogCategoryService blogCategoryService;
    private final BlogPostService blogPostService;
    //简单用这个
    @Resource
    private M getRepository;

    @Autowired
    public PostElasticServiceImpl(ElasticsearchRestTemplate elasticsearchRestTemplate, BlogCategoryService blogCategoryService, BlogPostService blogPostService) {
        this.elasticsearchRestTemplate = elasticsearchRestTemplate;
        this.blogCategoryService = blogCategoryService;
        this.blogPostService = blogPostService;
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
    public void deleteById(ID id) {
        this.getRepository.deleteById(id);
    }

    @Override
    public List<BlogPostDocument> searchHighlight(String keyword, String index) {
        List<BlogPostDocument> blogPostDocuments = new ArrayList<>();
        //匹配查询
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("title", keyword);
        //高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("title");
        highlightBuilder.requireFieldMatch(false);//多个高亮关闭
        highlightBuilder.preTags("<span style='color:red'>");
        highlightBuilder.postTags("</span>");
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


    @Override
    public ElasticsearchRepository getRepository() {
        return this.getRepository;
    }
}
