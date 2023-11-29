package com.sndshun.api.pojo.param;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 分页查询条件参数校验类
 * @author sndshun
 * @date 2023/11/29 03:51:29
 */

@Data
public class DictAdminQueryPageParam {

    @Valid
    @NotNull
    private Integer current;

    @Valid
    @NotNull
    private Integer size;

}
