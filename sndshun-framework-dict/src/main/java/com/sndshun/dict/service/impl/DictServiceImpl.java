package com.sndshun.dict.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sndshun.dict.mapper.DictMapper;
import com.sndshun.dict.entity.DictEntity;
import com.sndshun.dict.service.DictService;
import org.springframework.stereotype.Service;

/**
 * 字典表(Dict)表服务实现类
 *
 * @author sndshun
 * @since 2023-11-17 09:34:24
 */
@Service("dictService")
public class DictServiceImpl extends ServiceImpl<DictMapper, DictEntity> implements DictService {

}
