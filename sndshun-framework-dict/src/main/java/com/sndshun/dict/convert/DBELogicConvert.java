package com.sndshun.dict.convert;

import com.sndshun.api.pojo.dto.DictBizDto;
import com.sndshun.dict.entity.DictBizEntity;
import lombok.NonNull;

/**
 * 业务字典表(DictBiz)表实体类转换
 */
public class DBELogicConvert {


    /**
     * DictBizDto转换为DictBizEntity（14个属性）
     *
     * @param item 属性
     * @return DictBizEntity
     */
    public static DictBizEntity dBDConvertToDBE14(@NonNull DictBizDto item) {
        DictBizEntity result = new DictBizEntity();
        result.setId(item.getId())
                .setParentId(item.getParentId())
                .setCode(item.getCode())
                .setDictKey(item.getDictKey())
                .setDictValue(item.getDictValue())
                .setSort(item.getSort())
                .setRemark(item.getRemark())
                .setIsSealed(item.getIsSealed())
                .setVersion(item.getVersion())
                .setTenantId(item.getTenantId())
                .setLogicDelete(item.getLogicDelete())
                .setCreatedBy(item.getCreatedBy())
                .setCreatedTime(item.getCreatedTime())
                .setUpdatedBy(item.getUpdatedBy())
                .setUpdatedTime(item.getUpdatedTime());
        return result;
    }
}
