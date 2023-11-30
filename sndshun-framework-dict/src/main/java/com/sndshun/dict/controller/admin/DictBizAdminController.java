package com.sndshun.dict.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sndshun.api.DictBizAdminApi;
import com.sndshun.api.pojo.dto.DictBizDto;
import com.sndshun.commons.tools.Result;
import com.sndshun.dict.entity.DictBizEntity;
import com.sndshun.dict.service.DictBizService;
import com.sndshun.web.pojo.QueryPage;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

@RestController
public class DictBizAdminController implements DictBizAdminApi {
    @Resource
    private DictBizService dictBizService;

    public static DictBizEntity convertToDictBizEntity(DictBizDto item) {
        if (item == null) {
            return null;
        }
        DictBizEntity result = new DictBizEntity();
        result.setId(item.getId());
        result.setParentId(item.getParentId());
        result.setCode(item.getCode());
        result.setDictKey(item.getDictKey());
        result.setDictValue(item.getDictValue());
        result.setSort(item.getSort());
        result.setRemark(item.getRemark());
        result.setIsSealed(item.getIsSealed());
        result.setVersion(item.getVersion());
        result.setTenantId(item.getTenantId());
        result.setLogicDelete(item.getLogicDelete());
        result.setCreatedBy(item.getCreatedBy());
        result.setCreatedTime(item.getCreatedTime());
        result.setUpdatedBy(item.getUpdatedBy());
        result.setUpdatedTime(item.getUpdatedTime());
        return result;
    }

    @Override
    public Result<?> selectAll(QueryPage page, DictBizDto dictBiz) {
        return Result.ok(dictBizService.page(new Page<DictBizEntity>()
                        .setSize(page.getSize()).setCurrent(page.getCurrent()),
                new QueryWrapper<>(convertToDictBizEntity(dictBiz))));
    }

    @Override
    public Result<?> selectOne(Serializable id) {
        return Result.ok(dictBizService.getOptById(id));
    }

    @Override
    public Result<?> insert(DictBizDto dictBiz) {
        return Result.ok(dictBizService.save(convertToDictBizEntity(dictBiz)));
    }

    @Override
    public Result<?> update(DictBizDto dictBiz) {
        return Result.ok(dictBizService.updateById(convertToDictBizEntity(dictBiz)));
    }

    @Override
    public Result<?> delete(Serializable id) {
        return Result.ok(dictBizService.removeById(id));
    }

    @Override
    public Result<?> deleteBatch(List<Long> idList) {
        return Result.ok(dictBizService.removeBatchByIds(idList));
    }
}
