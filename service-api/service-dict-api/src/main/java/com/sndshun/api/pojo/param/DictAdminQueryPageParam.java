package com.sndshun.api.pojo.param;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(name = "User", description = "Represents a user in the system")
    private Integer size;

}
