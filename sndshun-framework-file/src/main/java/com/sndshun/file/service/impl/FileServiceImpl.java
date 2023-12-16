package com.sndshun.file.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sndshun.file.entity.FileEntity;
import com.sndshun.file.mapper.FileMapper;
import com.sndshun.file.service.FileService;
import org.springframework.stereotype.Service;

/**
 * 文件表(File)表服务实现类
 *
 * @author sndshun
 * @since 2023-12-16 00:11:22
 */
@Service("fileService")
public class FileServiceImpl extends ServiceImpl<FileMapper, FileEntity> implements FileService {

    @Override
    public FileEntity getFileByHash(String hash) {
        LambdaQueryWrapper<FileEntity> select = Wrappers.<FileEntity>lambdaQuery()
                .select(FileEntity::getFilePath,
                        FileEntity::getConnectPath,
                        FileEntity::getOriginalFileName,
                        FileEntity::getName,
                        FileEntity::getFileSize,
                        FileEntity::getFileType,
                        FileEntity::getMimeType,
                        FileEntity::getBucket,
                        FileEntity::getFileHash)
                .eq(FileEntity::getFileHash, hash).last("limit 1");
        return super.getOne(select);
    }
}
