package com.sndshun.api.pojo.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * 业务字典表(DictBiz)表实体类
 *
 * @author sndshun
 * @since 2023-12-01 02:52:21
 */

@Data
@Schema(name = "dict_biz", description = "业务字典表")
public class DictBizDto {
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
    @Schema(name = "isSealed", description = "是否已封存")
    private Integer isSealed;
    @Schema(name = "version", description = "乐观锁")
    private Integer version;
    @Schema(name = "tenantId", description = "租户号")
    private Long tenantId;
    @Schema(name = "logicDelete", description = "逻辑删除;0：正常 1：删除")
    private Integer logicDelete;
    @Schema(name = "createdBy", description = "创建人")
    private Long createdBy;
    @Schema(name = "createdTime", description = "创建时间")
    private Date createdTime;
    @Schema(name = "updatedBy", description = "最后更新人")
    private Long updatedBy;
    @Schema(name = "updatedTime", description = "最后更新时间")
    private Date updatedTime;


}
