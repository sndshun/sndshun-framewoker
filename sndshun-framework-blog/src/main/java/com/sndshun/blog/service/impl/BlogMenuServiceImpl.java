package com.sndshun.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sndshun.blog.constant.MenuType;
import com.sndshun.blog.mapper.BlogMenuMapper;
import com.sndshun.blog.entity.BlogMenuEntity;
import com.sndshun.blog.service.BlogMenuService;
import com.sndshun.blog.vo.BlogMenuTreeVo;
import com.sndshun.commons.constant.Status;
import com.sndshun.commons.tools.TreeUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 博客菜单(BlogMenu)表服务实现类
 *
 * @author sndshun
 * @since 2023-11-27 17:01:56
 */
@Service("blogMenuService")
public class BlogMenuServiceImpl extends ServiceImpl<BlogMenuMapper, BlogMenuEntity> implements BlogMenuService {
    @Resource
    private BlogMenuMapper blogMenuMapper;

    @Cacheable(cacheNames = "blog:menu",key = "#root.methodName")
    @Override
    public List<BlogMenuTreeVo> blogMenuTree() {
        LambdaQueryWrapper<BlogMenuEntity> select = Wrappers. <BlogMenuEntity>lambdaQuery().select(BlogMenuEntity::getId,
                        BlogMenuEntity::getName,
                        BlogMenuEntity::getParentId,
                        BlogMenuEntity::getIcon,
                        BlogMenuEntity::getPath,
                        BlogMenuEntity::getComponent)
                .eq(BlogMenuEntity::getIsHidden, Status.NO)
                .eq(BlogMenuEntity::getType, MenuType.BLOGADMINMENU.getCode());

        List<BlogMenuEntity> list = super.list(select);

        List<BlogMenuTreeVo> blogMenuTreeVoList = BlogMenuTreeVo.convertToBlogMenuTreeListVo(list);

        //定义排序规则 sort 为空给设置为0 负数可以作为置顶功能
        Comparator<BlogMenuTreeVo> comparator = Comparator.comparing(vo->null==vo.getSort()?0:vo.getSort());

        return TreeUtils.treeParent(blogMenuTreeVoList,BlogMenuTreeVo::getId,
                BlogMenuTreeVo::getParentId,
                BlogMenuTreeVo::getChildren,
                comparator);
    }

    @Cacheable(cacheNames = "blog:menu",key = "#root.methodName")
    @Override
    public List<BlogMenuTreeVo> blogMenuTreeAll() {
        List<BlogMenuEntity> blogMenuEntities = super.list();

        List<BlogMenuTreeVo> blogMenuTreeVoList = BlogMenuTreeVo.convertToBlogMenuTreeListVo(blogMenuEntities);

        //定义排序规则 sort 为空给设置为0 负数可以作为置顶功能
        Comparator<BlogMenuTreeVo> comparator = Comparator.comparing(vo->null==vo.getSort()?0:vo.getSort());

        return TreeUtils.treeParent(blogMenuTreeVoList,BlogMenuTreeVo::getId,
                BlogMenuTreeVo::getParentId,
                BlogMenuTreeVo::getChildren,
                comparator);
    }

    @Cacheable(cacheNames = "blog:menu",key = "#root.methodName")
    @Override
    public List<BlogMenuEntity> blogMenuByParentId(Long id) {
        LambdaQueryWrapper<BlogMenuEntity> select=Wrappers.<BlogMenuEntity>lambdaQuery()
                .select(BlogMenuEntity::getId,
                        BlogMenuEntity::getName,
                        BlogMenuEntity::getPath,
                        BlogMenuEntity::getComponent,
                        BlogMenuEntity::getIcon,
                        BlogMenuEntity::getSort)
                .eq(BlogMenuEntity::getParentId,id)
                .orderBy(true,true,BlogMenuEntity::getSort);
        return super.list(select);
    }

}
