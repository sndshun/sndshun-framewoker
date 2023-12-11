package com.sndshun.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sndshun.blog.mapper.BlogVisitUserMapper;
import com.sndshun.blog.entity.BlogVisitUserEntity;
import com.sndshun.blog.service.BlogVisitUserService;
import org.springframework.stereotype.Service;

/**
 * 访客用户(BlogVisitUser)表服务实现类
 *
 * @author sndshun
 * @since 2023-12-11 13:33:07
 */
@Service("blogVisitUserService")
public class BlogVisitUserServiceImpl extends ServiceImpl<BlogVisitUserMapper, BlogVisitUserEntity> implements BlogVisitUserService {

}
