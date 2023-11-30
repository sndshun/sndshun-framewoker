package com.sndshun.dict.controller.endpoint;

import com.sndshun.api.DictBizEndPointApi;
import com.sndshun.api.DictEndPointApi;
import com.sndshun.api.pojo.vo.DictVo;
import com.sndshun.commons.tools.Result;
import com.sndshun.dict.entity.DictBizEntity;
import com.sndshun.dict.entity.DictEntity;
import com.sndshun.dict.service.DictBizService;
import com.sndshun.dict.service.DictService;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class DictBizEndPointController implements DictBizEndPointApi {
    @Resource
    private DictBizService dictBizService;

    @Override
    public Result<List<DictVo>> getDictBizByParentId(Long parentId) {
        List<DictBizEntity> dictByParentIdCache = dictBizService.getDictByParentIdCache(parentId);
        List<DictVo> collect = dictByParentIdCache.stream().map(item -> {
            return new DictVo(item.getId(),
                    item.getParentId(),
                    item.getCode(),
                    item.getDictKey(),
                    item.getDictValue(),
                    item.getSort(),
                    item.getRemark());
        }).collect(Collectors.toList());
        return Result.ok(collect);
    }
}
