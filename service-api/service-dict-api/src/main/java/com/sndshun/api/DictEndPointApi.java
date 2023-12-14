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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Tag(name = "dict",description = "系统字典端点控制器")
@RequestMapping("dict")
public interface DictEndPointApi {
    @Operation(summary = "清除系统缓存", description = "清空所有系统缓存")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功"),
    })
    @GetMapping("/clear")
    Result<Boolean> clear();

    @Operation(summary = "根据父级id查询", description = "查询系统字典父级id下的分类")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功"),
    })
    @Parameter(name = "parentId",required = true,description = "父级ID",in = ParameterIn.PATH)
    @GetMapping("/parentId")
    Result<List<DictVo>> getDictByParentId(@RequestParam Long parentId);

    @Operation(summary = "根据父级code查询", description = "查询系统字典父级code下的分类,返回map")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功"),
    })
    @Parameter(name = "code",required = true,description = "父级Code",in = ParameterIn.PATH)
    @GetMapping("/code")
    Result<Map<String,String>> getDictByCodeToMap(@RequestParam String code);

}

