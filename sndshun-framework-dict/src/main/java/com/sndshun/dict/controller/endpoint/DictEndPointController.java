package com.sndshun.dict.controller.endpoint;

import com.sndshun.api.DictEndPointApi;
import com.sndshun.api.pojo.vo.DictVo;
import com.sndshun.commons.tools.Result;
import com.sndshun.dict.entity.DictEntity;
import com.sndshun.dict.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class DictEndPointController implements DictEndPointApi {

    private final DictService dictService;

    @Autowired
    public DictEndPointController(DictService dictService) {
        this.dictService = dictService;
    }

    @Override
    public Result<List<DictVo>> getDictByParentId(Long parentId) {
        List<DictEntity> dictByParentIdCache = dictService.getDictByParentIdCache(parentId);
        List<DictVo> collect = dictByParentIdCache.stream().map(item -> new DictVo(item.getId(), item.getParentId(), item.getCode(), item.getDictKey(), item.getDictValue(), item.getSort(), item.getRemark())).collect(Collectors.toList());
        return Result.ok(collect);
    }
}
