package com.sndshun.blog.es;

import com.sndshun.blog.pojo.document.BlogPostDocument;

import java.io.Serializable;
import java.util.List;

public interface PostElasticService {

    /**
     * 文章高亮搜索
     *
     * @param keyword 关键词
     * @return {@link List }<{@link BlogPostDocument }>
     * @author sndshun
     * @date 2024/01/22 06:55:58
     */
    List<BlogPostDocument> search(String keyword);


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

    /**
     * 根据ID删除数据
     *
     * @param id
     */
    void deleteById(Serializable id);

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

    /**
     * 组合搜索
     *
     * @return
     */
    List<BlogPostDocument> selectCombinedSearch(String value1, String value2, String index);

    /**
     * 验证是否存在
     *
     * @return
     */
    boolean isIndexExists(String id);

    /**
     * 过滤查询
     *
     * @param term1 值1
     * @param term2 值2
     * @return 集合
     */
    List<BlogPostDocument> FilterQuery(int term1, int term2);

    /**
     * 根据BlogPostDocument添加数据
     * @param document 对象
     * @return
     */
    boolean saveIndexByBlogPostDocument(BlogPostDocument document);
}
