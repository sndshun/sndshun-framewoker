package com.sndshun.blog.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sndshun.blog.mapper.BlogArchiveMapper;
import com.sndshun.blog.entity.BlogArchiveEntity;
import com.sndshun.blog.service.BlogArchiveService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 归档表(BlogArchive)表服务实现类
 *
 * @author sndshun
 * @since 2023-12-03 18:37:41
 */
@Service("blogArchiveService")
public class BlogArchiveServiceImpl extends ServiceImpl<BlogArchiveMapper, BlogArchiveEntity> implements BlogArchiveService {



    @Override
    public List<BlogArchiveEntity> getAll() {

        return null;
    }

    @Override
    public List<Map<String, List<BlogArchiveEntity>>> getAllByYear() {
        return null;
    }
}
