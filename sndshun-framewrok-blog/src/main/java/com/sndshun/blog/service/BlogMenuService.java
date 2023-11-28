package com.sndshun.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sndshun.blog.entity.BlogMenuEntity;
import com.sndshun.blog.vo.BlogMenuTreeVo;

import java.util.List;

/**
 * 博客菜单(BlogMenu)表服务接口
 *
 * @author sndshun
 * @since 2023-11-27 17:01:56
 */
public interface BlogMenuService extends IService<BlogMenuEntity> {

    /**
     * 获取博客菜单
     * @return {@link List }<{@link BlogMenuTreeVo }>
     * @author sndshun
     * @date 2023/11/28 05:38:15
     */
    List<BlogMenuTreeVo> blogMenuTree();

    /**
     * 获取博客菜单所有数据
     * @return {@link List }<{@link BlogMenuTreeVo }>
     * @author sndshun
     * @date 2023/11/28 05:38:17
     */
    List<BlogMenuTreeVo> blogMenuTreeAll();

}
