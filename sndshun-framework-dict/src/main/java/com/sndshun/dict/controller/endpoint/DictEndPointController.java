package com.sndshun.dict.controller.endpoint;

import com.sndshun.api.DictEndPointApi;
import com.sndshun.api.pojo.vo.DictVo;
import com.sndshun.commons.tools.Result;
import com.sndshun.dict.entity.DictEntity;
import com.sndshun.dict.service.DictService;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class DictEndPointController implements DictEndPointApi {
    @Resource
    private DictService dictService;

    @Override
    public Result<List<DictVo>> getDictByParentId(Long parentId) {
        List<DictEntity> dictByParentIdCache = dictService.getDictByParentIdCache(parentId);
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
