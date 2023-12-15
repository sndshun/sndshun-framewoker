package com.sndshun.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sndshun.blog.entity.BlogVisitUserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 访客用户(BlogVisitUser)表数据库访问层
 *
 * @author sndshun
 * @since 2023-12-11 13:33:07
 */
@Mapper
public interface BlogVisitUserMapper extends BaseMapper<BlogVisitUserEntity> {


    /**
     * 通过ip获取访客识别码
     *
     * @param ip ip
     * @return 识别码uuid
     */
    String getUuidByiP(String ip);
}
