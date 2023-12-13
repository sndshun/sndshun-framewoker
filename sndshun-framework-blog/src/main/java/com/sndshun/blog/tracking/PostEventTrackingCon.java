package com.sndshun.blog.tracking;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**前端埋点处理
 * @author sndshun
 * @date 2023/12/13 11:45:23
 */
@RequestMapping("/blog/track/post")
public class PostEventTrackingCon {

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    /**
     * 博客埋点 key
     */
    public static final String blog_track_key = "blog:track";

    /**
     * 访问
     */
    public static final String post_visits_hash = "postVisitsHash";
    /**
     * 文章停留时常
     */
    public static final String post_time_on_site_hash = "postTimeOnSiteHash";
    /**
     * 浏览量
     */
    public static final String post_views_hash = "postViewsHash";


    /** 页面浏览埋点处理
     * @param id 编号
     * @author sndshun
     * @date 2023/12/13 11:19:19
     */
    @Async
    @GetMapping("/view")
    public void view(Long id) {
        //todo 页面埋点请求处理，正常埋点数据是要发送到大数据平台，现在就这 redis
        //页面埋点可以是个img图片可以是随便 <img url="/blog/endpoint/post/view?id=2335235532">
        redisTemplate.opsForHash().increment(blog_track_key,post_views_hash,1);
    }

}
