package com.sndshun.dict.controller.admin;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sndshun.api.DictAdminApi;
import com.sndshun.api.pojo.dto.DictDto;
import com.sndshun.commons.tools.Result;
import com.sndshun.dict.convert.DELogicConvert;
import com.sndshun.dict.entity.DictEntity;
import com.sndshun.dict.service.DictService;
import com.sndshun.web.pojo.QueryPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.List;

@RestController
public class DictAdminController implements DictAdminApi {

    private final DictService dictService;

    @Autowired
    public DictAdminController(DictService dictService) {
        this.dictService = dictService;
    }

    @Override
    public Result<?> selectAll(QueryPage page, DictDto dict) {
        return Result.ok(dictService.page(new Page<DictEntity>().setSize(page.getSize()).setCurrent(page.getSize()), new QueryWrapper<>(null)));
    }

    @Override
    public Result<?> selectOne(Serializable id) {
        return Result.ok(dictService.getById(id));
    }

    @Override
    public Result<?> insert(DictDto dict) {
        DictEntity dictEntity = DELogicConvert.dDConvertToDE13(dict);
        return Result.ok(dictService.save(dictEntity));
    }

    @Override
    public Result<?> update(DictDto dict) {
        DictEntity dictEntity = DELogicConvert.dDConvertToDE13(dict);
        return Result.ok(dictService.updateById(dictEntity));
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
