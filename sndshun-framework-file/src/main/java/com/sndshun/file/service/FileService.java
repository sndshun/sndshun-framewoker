package com.sndshun.file.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sndshun.file.entity.FileEntity;

/**
 * 文件表(File)表服务接口
 *
 * @author sndshun
 * @since 2023-12-16 00:11:22
 */
public interface FileService extends IService<FileEntity> {

    /** 根据文件hash值查询
     * @param hash 散 列
     * @return {@link FileEntity }
     * @author sndshun
     * @date 2023/12/16 01:13:18
     */
    FileEntity getFileByHash(String hash);



}
