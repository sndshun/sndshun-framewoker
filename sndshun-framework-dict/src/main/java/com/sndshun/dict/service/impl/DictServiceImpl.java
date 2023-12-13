package com.sndshun.dict.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sndshun.dict.entity.DictBizEntity;
import com.sndshun.dict.mapper.DictMapper;
import com.sndshun.dict.entity.DictEntity;
import com.sndshun.dict.service.DictService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 字典表(Dict)表服务实现类
 *
 * @author sndshun
 * @since 2023-11-17 09:34:24
 */
@Service("dictService")
@Slf4j
public class DictServiceImpl extends ServiceImpl<DictMapper, DictEntity> implements DictService {

    @Cacheable(cacheNames = "dict", key = "#parentId")
    @Override
    public List<DictEntity> getDictByParentIdCache(Long parentId) {
        LambdaQueryWrapper<DictEntity> select = Wrappers.<DictEntity>lambdaQuery().select(DictEntity::getId,
                DictEntity::getParentId,
                DictEntity::getCode,
                DictEntity::getDictKey,
                DictEntity::getDictValue,
                DictEntity::getRemark,
                DictEntity::getSort).eq(DictEntity::getParentId, parentId);
        return super.list(select);
    }

    @Cacheable(cacheNames = "dict", key = "#code")
    @Override
    public List<DictEntity> getDictByCodeCache(String code) {
        LambdaQueryWrapper<DictEntity> select = Wrappers.<DictEntity>lambdaQuery().select(DictEntity::getId,
                        DictEntity::getParentId,
                        DictEntity::getCode,
                        DictEntity::getDictKey,
                        DictEntity::getDictValue,
                        DictEntity::getRemark,
                        DictEntity::getSort)
                .ne(DictEntity::getDictKey, -1)
                .eq(DictEntity::getCode, code);
        return super.list(select);
    }
}
