package com.sndshun.dict.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sndshun.dict.entity.DictBizEntity;

import java.util.List;

/**
 * 业务字典表(DictBiz)表服务接口
 *
 * @author sndshun
 * @since 2023-11-17 09:34:25
 */
public interface DictBizService extends IService<DictBizEntity> {

    /**
     * 根据父级id查询
     * @param parentId 编号
     * @return {@link List }<{@link DictBizEntity }>
     * @author sndshun
     * @date 2023/12/01 03:32:55
     */
    List<DictBizEntity> getDictByParentIdCache(Long parentId);

}
