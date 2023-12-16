package com.sndshun.file.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sndshun.file.entity.FileEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 文件表(File)表数据库访问层
 *
 * @author sndshun
 * @since 2023-12-16 00:11:22
 */
@Mapper
public interface FileMapper extends BaseMapper<FileEntity> {

}
