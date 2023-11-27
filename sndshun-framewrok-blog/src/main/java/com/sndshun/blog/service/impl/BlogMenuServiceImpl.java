package com.sndshun.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sndshun.blog.mapper.BlogMenuMapper;
import com.sndshun.blog.entity.BlogMenuEntity;
import com.sndshun.blog.service.BlogMenuService;
import org.springframework.stereotype.Service;

/**
 * 博客菜单(BlogMenu)表服务实现类
 *
 * @author sndshun
 * @since 2023-11-27 17:01:56
 */
@Service("blogMenuService")
public class BlogMenuServiceImpl extends ServiceImpl<BlogMenuMapper, BlogMenuEntity> implements BlogMenuService {

}
