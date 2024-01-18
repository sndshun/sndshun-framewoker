package com.sndshun.blog.pojo.vo;


import com.sndshun.blog.entity.BlogCategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 分类表(BlogCategory)表实体类
 *
 * @author sndshun
 * @since 2023-12-03 18:37:42
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogCategoryTreeVo {
    /**
     * 分类ID
     */

    private Long id;
    /**
     * 分类名称
     */

    private String name;
    /**
     * 分类描述
     */

    private String description;
    /**
     * 是分类的别名，可用于生成友好的 URL
     */

    private String slug;
    /**
     * 分类下有几篇文章
     */
    private Integer postCount;
    /**
     * 父分类ID，表示层级关系
     */

    private Long parentId;
    /**
     * 显示顺序
     */

    private Integer sort;
    /**
     * 是否启用; 0: 禁用, 1: 启用
     */

    private Integer isActive;
    /**
     * 租户号
     */

    private Long tenantId;
    /**
     * 乐观锁
     */

    private Integer version;
    /**
     * 逻辑删除;0：正常 1：删除
     */

    private Integer logicDelete;
    /**
     * 创建人
     */

    private Long createdBy;
    /**
     * 创建时间
     */

    private Date createdTime;
    /**
     * 最后更新人
     */

    private Long updatedBy;
    /**
     * 最后更新时间
     */

    private Date updatedTime;

    private List<BlogCategoryTreeVo> children = new ArrayList<>();

    public static BlogCategoryTreeVo convertToBlogCategoryTreeVo(BlogCategoryEntity item) {
        if (item == null) {
            return null;
        }
        BlogCategoryTreeVo result = new BlogCategoryTreeVo();
        result.setId(item.getId());
        result.setName(item.getName());
        result.setDescription(item.getDescription());
        result.setSlug(item.getSlug());
        result.setPostCount(item.getPostCount());
        result.setParentId(item.getParentId());
        result.setSort(item.getSort());
        result.setIsActive(item.getIsActive());
        result.setTenantId(item.getTenantId());
        result.setVersion(item.getVersion());
        result.setLogicDelete(item.getLogicDelete());
        result.setCreatedBy(item.getCreatedBy());
        result.setCreatedTime(item.getCreatedTime());
        result.setUpdatedBy(item.getUpdatedBy());
        result.setUpdatedTime(item.getUpdatedTime());
        return result;
    }

    public static List<BlogCategoryTreeVo> convertToBlogCategoryTreeVo(List<BlogCategoryEntity> item) {
        return item.stream().map(BlogCategoryTreeVo::convertToBlogCategoryTreeVo).collect(Collectors.toList());
    }


}
