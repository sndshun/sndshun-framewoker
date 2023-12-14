package com.sndshun.blog.tracking;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**前端埋点处理
 * @author sndshun
 * @date 2023/12/13 11:45:23
 */
@Component
public class PostEventTrackingService {
    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @Async
    public void tracking(Long id) {
        //todo 后端埋点与vuex类似 暂时先这么写
        //这个方法可以插入controller service 方法中，避免直接写在业务逻辑中
        //这块也是需要存大数据平台，目前不redis的话 MongoDB就很合适
    }
}
