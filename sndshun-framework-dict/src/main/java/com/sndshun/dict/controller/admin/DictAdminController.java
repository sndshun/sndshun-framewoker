package com.sndshun.dict.controller.admin;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sndshun.api.DictAdminApi;
import com.sndshun.api.pojo.dto.DictDto;
import com.sndshun.commons.tools.Result;
import com.sndshun.dict.entity.DictEntity;
import com.sndshun.dict.service.DictService;
import com.sndshun.web.pojo.QueryPage;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

@RestController
public class DictAdminController implements DictAdminApi {
    @Resource
    private DictService dictService;

    public static DictEntity convertToDictEntity(DictDto item) {
        if (item == null) {
            return null;
        }
        DictEntity result = new DictEntity();
        result.setId(item.getId());
        result.setParentId(item.getParentId());
        result.setCode(item.getCode());
        result.setDictKey(item.getDictKey());
        result.setDictValue(item.getDictValue());
        result.setSort(item.getSort());
        result.setRemark(item.getRemark());
        result.setIsSealed(item.getIsSealed());
        result.setVersion(item.getVersion());
        result.setCreatedBy(item.getCreatedBy());
        result.setLogicDelete(item.getLogicDelete());
        result.setUpdatedBy(item.getUpdatedBy());
        result.setCreatedTime(item.getCreatedTime());
        result.setUpdatedTime(item.getUpdatedTime());
        return result;
    }

    @Override
    public Result<?> selectAll(QueryPage page, DictDto dict) {
        return Result.ok(dictService.page(new Page<DictEntity>().setSize(page.getSize()).setCurrent(page.getSize())
                ,new QueryWrapper<>(null)));
    }

    @Override
    public Result<?> selectOne(Serializable id) {
        return Result.ok(dictService.getById(id));
    }

    @Override
    public Result<?> insert(DictDto dict) {
        return Result.ok(dictService.save(convertToDictEntity(dict)));
    }

    @Override
    public Result<?> update(DictDto dict) {
        return Result.ok(dictService.updateById(convertToDictEntity(dict)));
    }

    @Override
    public Result<?> delete(Serializable id) {
        return Result.ok(dictService.removeById(id));
    }

    @Override
    public Result<?> deleteBatch(List<Long> idList) {
        return Result.ok(idList);
    }
}
