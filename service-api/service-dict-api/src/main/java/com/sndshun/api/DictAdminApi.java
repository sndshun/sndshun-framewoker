package com.sndshun.api;

import com.sndshun.commons.tools.Result;
import io.swagger.v3.oas.annotations.Operation;
import org.apiguardian.api.API;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RequestMapping("admin/dict")
@Validated
public interface DictAdminApi {

    @Operation(summary = "字典查询",description = "分页查询字典",tags = {"dict"})
    @GetMapping("page")
    Result<?> selectPage();

    @GetMapping("query/{id}")
    Result<?> selectById(@PathVariable Long id);

    @PostMapping
    Result<?> insert();

    @PutMapping
    Result<?> update();

    @DeleteMapping("/{id}")
    Result<?> delete(@PathVariable Long id);
}
