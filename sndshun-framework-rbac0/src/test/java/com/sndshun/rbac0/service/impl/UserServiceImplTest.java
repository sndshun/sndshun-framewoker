package com.sndshun.rbac0.service.impl;

import com.sndshun.rbac0.entity.Rbac0PermissionEntity;
import com.sndshun.rbac0.entity.Rbac0RoleEntity;
import com.sndshun.rbac0.entity.Rbac0RolePermissionEntity;
import com.sndshun.rbac0.pojo.dto.UserRbac0InfoDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
@Slf4j
class UserServiceImplTest {

    @Resource
    private UserServiceImpl userService;


    @Test
    void testSelectRolesByUserId() {
        // Setup
        // Run the test
        final List<Rbac0RoleEntity> result = userService.selectRolesByUserId(2L);

        // Verify the results
        result.forEach(role ->log.info("{}",role.toString()));
    }

    @Test
    void testSelectPermissionsByUserId() {

        // Run the test
        final List<Rbac0PermissionEntity> result = userService.selectPermissionsByUserId(2L);
        result.forEach(role ->log.info("{}",role.toString()));
    }

    @Test
    void testSelectUserRbac0Info() {
        // Run the test
        final UserRbac0InfoDto result = userService.selectUserRbac0Info(2L);
        log.info("{}",result);
    }
}
