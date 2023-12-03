package com.sndshun.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sndshun.blog.entity.BlogArchiveEntity;

import java.util.List;
import java.util.Map;

/**
 * 归档表(BlogArchive)表服务接口
 * todo 归档可以用文章表实现，暂不更改
 * @author sndshun
 * @since 2023-12-03 18:37:41
 */
public interface BlogArchiveService extends IService<BlogArchiveEntity> {

    /**
     * 获取所有归档
     * @return {@link List }<{@link BlogArchiveEntity }>
     * @author sndshun
     * @date 2023/12/03 07:33:32
     */
    List<BlogArchiveEntity> getAll();

    /**
     * 获取所有归档按年分组
     * @return {@link List }<{@link Map }<{@link String }, {@link List }<{@link BlogArchiveEntity }>>>
     * @author sndshun
     * @date 2023/12/03 07:34:51
     */
    List<Map<String, List<BlogArchiveEntity>>> getAllByYear();

}
