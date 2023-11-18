package com.sndshun.mybatis.plus;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author sndshun
 * @Date 2023/11/16 19:34
 * @Version 1.0
 * @Description: mybatis-plus 配置类
 */
@Configuration
public class MybatisPlusConfig {

    /**
     * MyBatis Plus 拦截器
     *
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        //开启 乐观锁
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        //开启 防全表更新与删除插件
        interceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());
        //开启 分页插件
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));//如果配置多个插件,切记分页最后添加
        return interceptor;
    }


}
