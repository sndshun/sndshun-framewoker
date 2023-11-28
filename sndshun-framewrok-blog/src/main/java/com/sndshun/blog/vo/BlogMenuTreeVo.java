package com.sndshun.blog.vo;


import java.util.ArrayList;
import java.util.Date;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import com.sndshun.blog.entity.BlogMenuEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 博客菜单数据
 *
 * @author sndshun
 * @since 2023-11-27 16:30:02
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogMenuTreeVo {
    /**
     * 主键
     */

    private Long id;
    /**
     * 父id
     */

    private Integer parentId;
    /**
     * 菜单名
     */

    private String name;
    /**
     * 菜单路径
     */

    private String path;
    /**
     * 组件
     */

    private String component;
    /**
     * 菜单icon
     */

    private String icon;
    /**
     * 排序
     */

    private Integer sort;
    /**
     * 是否隐藏  0否1是
     */

    private Integer isHidden;
    /**
     * 租户号
     */

    private Long tenantId;
    /**
     * 最后更新时间
     */

    private Date updatedTime;
    /**
     * 最后更新人
     */

    private Long updatedBy;
    /**
     * 创建时间
     */

    private Date createdTime;
    /**
     * 创建人
     */

    private Long createdBy;
    /**
     * 逻辑删除;0：正常 1：删除
     */

    private Integer logicDelete;
    /**
     * 乐观锁
     */

    private Integer version;


    private List<BlogMenuTreeVo> children = new ArrayList<>();

    public static BlogMenuTreeVo convertToBlogMenuTreeVo(BlogMenuEntity item) {
        if (item == null) {
            return null;
        }
        BlogMenuTreeVo result = new BlogMenuTreeVo();
        result.setId(item.getId());
        result.setParentId(item.getParentId());
        result.setName(item.getName());
        result.setPath(item.getPath());
        result.setComponent(item.getComponent());
        result.setIcon(item.getIcon());
        result.setSort(item.getSort());
        result.setIsHidden(item.getIsHidden());
        result.setTenantId(item.getTenantId());
        result.setUpdatedTime(item.getUpdatedTime());
        result.setUpdatedBy(item.getUpdatedBy());
        result.setCreatedTime(item.getCreatedTime());
        result.setCreatedBy(item.getCreatedBy());
        result.setLogicDelete(item.getLogicDelete());
        result.setVersion(item.getVersion());
        return result;
    }

    public static List<BlogMenuTreeVo> convertToBlogMenuTreeListVo(List<BlogMenuEntity> list) {
        if (list.isEmpty()) {
            return new ArrayList<>();
        }

        return list.stream().map(BlogMenuTreeVo::convertToBlogMenuTreeVo).collect(Collectors.toList());
    }
}
