package com.sndshun.api;

import com.sndshun.api.pojo.vo.DictVo;
import com.sndshun.commons.tools.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@Tag(name = "系统字典端点控制器")
@RequestMapping("dict")
public interface DictEndPointApi {
    @Operation(summary = "根据父级id查询", description = "查询父级系id统字典下的分类")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功"),
    })
    @Parameter(name = "parentId",required = true,description = "父级ID",in = ParameterIn.PATH)
    @GetMapping("/{parentId}/children")
    Result<List<DictVo>> getDictByParentId(@PathVariable Long parentId);

}
