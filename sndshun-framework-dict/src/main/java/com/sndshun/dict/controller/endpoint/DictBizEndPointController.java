package com.sndshun.dict.controller.endpoint;

import com.sndshun.api.DictBizEndPointApi;
import com.sndshun.api.pojo.vo.DictVo;
import com.sndshun.commons.tools.Result;
import com.sndshun.dict.entity.DictBizEntity;
import com.sndshun.dict.service.DictBizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class DictBizEndPointController implements DictBizEndPointApi {

    private final DictBizService dictBizService;

    @Autowired
    public DictBizEndPointController(DictBizService dictBizService) {
        this.dictBizService = dictBizService;
    }

    @Override
    public Result<List<DictVo>> getDictBizByParentId(Long parentId) {
        List<DictBizEntity> dictByParentIdCache = dictBizService.getDictByParentIdCache(parentId);
        List<DictVo> collect = dictByParentIdCache.stream().map(item ->
                new DictVo(item.getId(), item.getParentId(), item.getCode(),
                        item.getDictKey(), item.getDictValue(), item.getSort(),
                        item.getRemark())).collect(Collectors.toList());
        return Result.ok(collect);
    }
}
