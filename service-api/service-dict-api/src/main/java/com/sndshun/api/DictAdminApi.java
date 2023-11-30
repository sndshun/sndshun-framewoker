package com.sndshun.api;

import com.sndshun.api.pojo.dto.DictDto;
import com.sndshun.commons.tools.Result;
import com.sndshun.web.pojo.QueryPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@Tag(name = "系统字典管理控制器")
@RequestMapping("dict/admin")
@Validated
public interface DictAdminApi {
    @Operation(summary = "分页查询", description = "分页查询所有数据")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功"),
    })
    @GetMapping
    Result<?> selectAll(QueryPage page, DictDto dict);

    @Operation(summary = "查询单个", description = "通过主键查询单条数据")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功"),
    })
    @Parameter(name = "id", required = true, description = "id", in = ParameterIn.PATH)
    @GetMapping("{id}")
    Result<?> selectOne(@PathVariable Serializable id);

    @Operation(summary = "新增数据", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功"),
    })
    @PostMapping
    Result<?> insert(@RequestBody(description = "实体", required = true) DictDto dict);

    @Operation(summary = "修改数据", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功"),
    })
    @PutMapping
    Result<?> update(@RequestBody(description = "实体", required = true) DictDto dict);

    @Operation(summary = "删除", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功"),
    })
    @Parameter(name = "id", required = true, description = "id", in = ParameterIn.PATH)
    @DeleteMapping
    Result<?> delete(@PathVariable Serializable id);

    @Operation(summary = "批量删除", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功"),
    })
    @DeleteMapping("batch")
    Result<?> deleteBatch(@RequestBody(description = "实体", required = true) List<Long> idList);
}
