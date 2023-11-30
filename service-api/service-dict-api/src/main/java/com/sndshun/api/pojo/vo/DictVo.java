package com.sndshun.api.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(name = "字典视图", description = "toC返回使用")
public class DictVo {
    @Schema(name = "id", description = "主键")
    private Long id;
    @Schema(name = "parentId", description = "父主键")
    private Long parentId;
    @Schema(name = "code", description = "字典码")
    private String code;
    @Schema(name = "dictKey", description = "字典值")
    private String dictKey;
    @Schema(name = "dictValue", description = "字典名称")
    private String dictValue;
    @Schema(name = "sort", description = "排序")
    private Integer sort;
    @Schema(name = "remark", description = "字典备注")
    private String remark;
}
