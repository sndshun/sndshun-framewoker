package com.sndshun.dict.controller.admin;


import com.sndshun.api.DictAdminApi;
import com.sndshun.commons.tools.Result;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DictAdminController implements DictAdminApi {
    @Override
    public Result<?> selectPage() {
        return null;
    }

    @Override
    public Result<?> selectById(Long id) {
        return null;
    }

    @Override
    public Result<?> insert() {
        return null;
    }

    @Override
    public Result<?> update() {
        return null;
    }

    @Override
    public Result<?> delete(Long id) {
        return null;
    }
}
