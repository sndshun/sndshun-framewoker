package com.sndshun.blog.controller;

import com.sndshun.commons.tools.Result;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 博客系统控制器
 * @author sndshun
 * @date 2023/12/08 10:05:33
 */

@RestController
@RequestMapping("blog")
public class BlogController {

    /**
     * 清除所有缓存
     * @return {@link Result }<{@link ? }>
     * @author sndshun
     * @date 2023/12/08 10:05:48
     */
    @GetMapping("cache/clear")
    @CacheEvict(cacheNames = "blog")
    public Result<?> clearBlogCache() {
        return Result.ok(true);
    }
}
