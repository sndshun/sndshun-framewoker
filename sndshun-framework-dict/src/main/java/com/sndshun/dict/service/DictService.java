package com.sndshun.dict.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sndshun.dict.entity.DictBizEntity;
import com.sndshun.dict.entity.DictEntity;

import java.util.List;

/**
 * 字典表(Dict)表服务接口
 *
 * @author sndshun
 * @since 2023-11-17 09:34:24
 */
public interface DictService extends IService<DictEntity> {

    /**
     * 根据父级id查询
     * @param parentId 编号
     * @return {@link List }<{@link DictBizEntity }>
     * @author sndshun
     * @date 2023/12/01 03:32:55
     */
    List<DictEntity> getDictByParentIdCache(Long parentId);
    /**
     * 根据父级code查询
     * @param code code
     * @return {@link List }<{@link DictBizEntity }>
     * @author sndshun
     * @date 2023/12/01 03:32:55
     */
    List<DictEntity> getDictByCodeCache(String code);

}
