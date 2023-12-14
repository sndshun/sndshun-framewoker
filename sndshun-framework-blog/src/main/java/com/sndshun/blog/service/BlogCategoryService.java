package com.sndshun.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sndshun.blog.entity.BlogCategoryEntity;
import com.sndshun.blog.vo.BlogCategoryTreeVo;

import java.util.List;

/**
 * 分类表(BlogCategory)表服务接口
 *
 * @author sndshun
 * @since 2023-12-03 18:37:42
 */
public interface BlogCategoryService extends IService<BlogCategoryEntity> {

    /**
     * 获取简略分类树
     * @return {@link List }<{@link BlogCategoryTreeVo }>
     * @author sndshun
     * @date 2023/12/03 06:54:43
     */
    List<BlogCategoryTreeVo> getCategoryTreeCache();
    /**
     * 获取所有分类树
     * @return {@link List }<{@link BlogCategoryTreeVo }>
     * @author sndshun
     * @date 2023/12/03 06:54:43
     */
    List<BlogCategoryTreeVo> getCategoryAllTree();

}
