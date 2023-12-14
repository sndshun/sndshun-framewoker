package com.sndshun.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sndshun.blog.entity.BlogCategoryEntity;
import com.sndshun.blog.mapper.BlogCategoryMapper;
import com.sndshun.blog.service.BlogCategoryService;
import com.sndshun.blog.vo.BlogCategoryTreeVo;
import com.sndshun.commons.constant.Status;
import com.sndshun.commons.tools.TreeUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

/**
 * 分类表(BlogCategory)表服务实现类
 *
 * @author sndshun
 * @since 2023-12-03 18:37:42
 */
@Service("blogCategoryService")
public class BlogCategoryServiceImpl extends ServiceImpl<BlogCategoryMapper, BlogCategoryEntity> implements BlogCategoryService {

    @Cacheable(cacheNames = "blog:category",key = "#root.methodName")
    @Override
    public List<BlogCategoryTreeVo> getCategoryTreeCache() {
        LambdaQueryWrapper<BlogCategoryEntity> select = Wrappers.<BlogCategoryEntity>lambdaQuery().select(BlogCategoryEntity::getId,
                BlogCategoryEntity::getParentId,
                BlogCategoryEntity::getName,
                BlogCategoryEntity::getSlug,
                BlogCategoryEntity::getPostCount,
                BlogCategoryEntity::getSort,
                BlogCategoryEntity::getDescription
        ).eq(BlogCategoryEntity::getIsActive, Status.YES.getValue());


        List<BlogCategoryEntity> list = super.list(select);
        List<BlogCategoryTreeVo> blogCategoryTreeVos = BlogCategoryTreeVo.convertToBlogCategoryTreeVo(list);

        //定义排序规则 sort 为空给设置为0 负数可以作为置顶功能
        Comparator<BlogCategoryTreeVo> comparator = Comparator.comparing(vo->null==vo.getSort()?0:vo.getSort());
        List<BlogCategoryTreeVo> treeParents = TreeUtils.treeParent(blogCategoryTreeVos, BlogCategoryTreeVo::getId, BlogCategoryTreeVo::getParentId, BlogCategoryTreeVo::getChildren, comparator);

        return treeParents;
    }

    @Override
    public List<BlogCategoryTreeVo> getCategoryAllTree() {
        List<BlogCategoryEntity> list = super.list();
        List<BlogCategoryTreeVo> blogCategoryTreeVos = BlogCategoryTreeVo.convertToBlogCategoryTreeVo(list);
        //定义排序规则 sort 为空给设置为0 负数可以作为置顶功能
        Comparator<BlogCategoryTreeVo> comparator = Comparator.comparing(vo->null==vo.getSort()?0:vo.getSort());
        List<BlogCategoryTreeVo> treeParents = TreeUtils.treeParent(blogCategoryTreeVos, BlogCategoryTreeVo::getId, BlogCategoryTreeVo::getParentId, BlogCategoryTreeVo::getChildren, comparator);

        return treeParents;
    }
}
