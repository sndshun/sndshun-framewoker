package com.sndshun.dict.convert;

import com.sndshun.api.pojo.dto.DictDto;
import com.sndshun.dict.entity.DictEntity;
import lombok.NonNull;

/**
 * DictEntity的逻辑转换
 */
public class DELogicConvert {


    /**
     * DictDto转换为DictEntity(13参数)
     *
     * @param item 属性
     * @return DictEntity
     */
    public static DictEntity dDConvertToDE13(@NonNull DictDto item) {
        DictEntity result = new DictEntity();
        result.setId(item.getId())
                .setParentId(item.getParentId()).setCode(item.getCode())
                .setDictKey(item.getDictKey()).setDictValue(item.getDictValue())
                .setSort(item.getSort()).setRemark(item.getRemark())
                .setIsSealed(item.getIsSealed()).setVersion(item.getVersion())
                .setCreatedBy(item.getCreatedBy()).setLogicDelete(item.getLogicDelete())
                .setUpdatedBy(item.getUpdatedBy()).setCreatedTime(item.getCreatedTime())
                .setUpdatedTime(item.getUpdatedTime());
        return result;
    }
}
