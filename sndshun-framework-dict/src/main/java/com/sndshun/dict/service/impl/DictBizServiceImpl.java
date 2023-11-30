package com.sndshun.dict.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sndshun.dict.mapper.DictBizMapper;
import com.sndshun.dict.entity.DictBizEntity;
import com.sndshun.dict.service.DictBizService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 业务字典表(DictBiz)表服务实现类
 *
 * @author sndshun
 * @since 2023-11-17 09:34:25
 */
@Service("dictBizService")
public class DictBizServiceImpl extends ServiceImpl<DictBizMapper, DictBizEntity> implements DictBizService {

    @Cacheable(cacheNames = "dict:biz",key = "#parentId")
    @Override
    public List<DictBizEntity> getDictByParentIdCache(Long parentId) {
        LambdaQueryWrapper<DictBizEntity> select = Wrappers.<DictBizEntity>lambdaQuery().select(DictBizEntity::getId,
                DictBizEntity::getParentId,
                DictBizEntity::getCode,
                DictBizEntity::getDictKey,
                DictBizEntity::getDictValue,
                DictBizEntity::getRemark,
                DictBizEntity::getSort).eq(DictBizEntity::getParentId,parentId);
        return super.list(select);
    }
}
