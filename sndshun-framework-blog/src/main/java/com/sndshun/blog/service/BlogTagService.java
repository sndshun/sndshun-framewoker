package com.sndshun.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sndshun.blog.entity.BlogTagEntity;

import java.util.List;

/**
 * 标签表(BlogTag)表服务接口
 *
 * @author sndshun
 * @since 2023-12-03 18:37:42
 */
public interface BlogTagService extends IService<BlogTagEntity> {

    /**
     * 所有标签
     * @return {@link List }<{@link BlogTagEntity }>
     * @author sndshun
     * @date 2023/12/03 07:13:39
     */
    List<BlogTagEntity> getAll();

}
