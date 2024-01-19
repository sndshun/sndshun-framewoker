package com.sndshun.blog.es;


import com.sndshun.blog.pojo.document.BlogPostDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;



public interface BlogPostRepository extends ElasticsearchRepository<BlogPostDocument, String> {


}
