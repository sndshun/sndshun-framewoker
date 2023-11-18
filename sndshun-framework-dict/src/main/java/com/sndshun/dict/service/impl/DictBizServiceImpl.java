package com.sndshun.dict.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sndshun.dict.mapper.DictBizMapper;
import com.sndshun.dict.entity.DictBizEntity;
import com.sndshun.dict.service.DictBizService;
import org.springframework.stereotype.Service;

/**
 * 业务字典表(DictBiz)表服务实现类
 *
 * @author sndshun
 * @since 2023-11-17 09:34:25
 */
@Service("dictBizService")
public class DictBizServiceImpl extends ServiceImpl<DictBizMapper, DictBizEntity> implements DictBizService {

}
