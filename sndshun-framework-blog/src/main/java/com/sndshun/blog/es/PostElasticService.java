package com.sndshun.blog.es;

import com.sndshun.blog.pojo.document.BlogPostDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import javax.annotation.Resource;
import java.util.List;

public interface PostElasticService<T, ID> {

    /**
     * 创建索引并推送映射
     */
    boolean createIndex();

    /**
     * 删除索引
     *
     * @param index
     * @return
     */
    boolean deleteIndex(String index);

    /**
     * 根据id查询
     *
     * @param id    id
     * @param index 索引
     * @return
     */
    BlogPostDocument selectAllById(String id, String index);

    void deleteById(ID id);

    /**
     * 高亮搜索
     *
     * @param keyword 关键词
     * @param index   索引
     * @return 集合
     */
    List<BlogPostDocument> searchHighlight(String keyword, String index);

    /**
     * 分页查询
     *
     * @param page  页数
     * @param size  大小
     * @param index 索引
     * @return
     */
    List<BlogPostDocument> selectPage(int page, int size, String index);

    /**
     * 排序查询
     *
     * @param index 索引
     * @return
     */
    List<BlogPostDocument> selectByViewCountDesc(String index);

    @Resource
    ElasticsearchRepository<BlogPostDocument, String> getRepository();
}
