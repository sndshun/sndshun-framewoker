package com.sndshun.dict.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sndshun.api.DictBizAdminApi;
import com.sndshun.api.pojo.dto.DictBizDto;
import com.sndshun.commons.tools.Result;
import com.sndshun.dict.convert.DBELogicConvert;
import com.sndshun.dict.entity.DictBizEntity;
import com.sndshun.dict.service.DictBizService;
import com.sndshun.web.pojo.QueryPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.List;

@RestController
public class DictBizAdminController implements DictBizAdminApi {

    private final DictBizService dictBizService;

    @Autowired
    public DictBizAdminController(DictBizService dictBizService) {
        this.dictBizService = dictBizService;
    }

    @Override
    public Result<?> selectAll(QueryPage page, DictBizDto dictBiz) {
        return Result.ok(dictBizService.page(new Page<DictBizEntity>().setSize(page.getSize()).setCurrent(page.getCurrent()), new QueryWrapper<>(DBELogicConvert.dBDConvertToDBE14(dictBiz))));
    }

    @Override
    public Result<?> selectOne(Serializable id) {
        return Result.ok(dictBizService.getOptById(id));
    }

    @Override
    public Result<?> insert(DictBizDto dictBiz) {
        return Result.ok(dictBizService.save(DBELogicConvert.dBDConvertToDBE14(dictBiz)));
    }

    @Override
    public Result<?> update(DictBizDto dictBiz) {
        return Result.ok(dictBizService.updateById(DBELogicConvert.dBDConvertToDBE14(dictBiz)));
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
