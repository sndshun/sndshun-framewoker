package com.sndshun.blog.controller;

import com.sndshun.blog.annotation.VisitLog;
import com.sndshun.blog.enums.VisitEnum;
import com.sndshun.commons.tools.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 博客系统控制器
 * @author sndshun
 * @date 2023/12/08 10:05:33
 */
@Slf4j
@RestController
@RequestMapping("blog")
public class BlogController {


    /**
     * 清除所有菜单缓存
     * @return {@link Result }<{@link ? }>
     * @author sndshun
     * @date 2023/12/08 10:05:48
     */
    @VisitLog(VisitEnum.CACHE_CLEAR)
    @GetMapping("cache/clear")
    @CacheEvict(cacheNames = {"blog:menu","blog:post","blog:tag","blog:link","blog:category"},allEntries = true)
    public Result<Boolean> clearBlogMenuCache() {
        log.info("博客缓存=====================>全部清楚成功");
        return Result.ok(true);
    }
}
