package com.sndshun.rbac0.service.impl;

import com.sndshun.rbac0.pojo.dto.RoleJoinPermissionDto;
import com.sndshun.rbac0.service.Rbac0RoleService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class Rbac0RoleServiceImplTest {

    @Resource
    private Rbac0RoleService rbac0RoleService;

    @Test
    void selectRolePermission() {
        RoleJoinPermissionDto roleJoinPermissionDto = rbac0RoleService.selectRolePermission(1);
        System.out.println(roleJoinPermissionDto);
    }
}