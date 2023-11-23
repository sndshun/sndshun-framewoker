package com.sndshun.dict.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sndshun.dict.entity.DictEntity;

/**
 * 字典表(Dict)表服务接口
 *
 * @author sndshun
 * @since 2023-11-17 09:34:24
 */
public interface DictService extends IService<DictEntity> {

    /**
     * @param id 编号
     * @return {@link DictEntity }
     * @author sndshun
     * @date 2023/11/24 12:26:56
     */
    DictEntity getByIdCache(Integer id);

}
