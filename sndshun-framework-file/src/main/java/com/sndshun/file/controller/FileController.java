package com.sndshun.file.controller;

import com.sndshun.commons.tools.Result;
import com.sndshun.commons.tools.StringUtils;
import com.sndshun.file.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 文件对象控制层
 *
 * @author mapleie
 */
@RestController
@RequestMapping("/file")
public class FileController extends BaseController {
    private final OssService ossService;

    @Autowired
    public FileController(OssService ossService) {
        this.ossService = ossService;
    }

    @GetMapping("/bucket/list")
    public Result<?> bucketList() {
        return ossService.listBuckets();
    }

    /**
     * 桶是否存在
     *
     * @param bucketName 桶名
     * @return 是否存在
     */
    @GetMapping("/exist/{bucketName}")
    public Result<String> bucketExists(@PathVariable String bucketName) {
        log.info("FileController bucketExists start");
        boolean empty = StringUtils.isEmpty(bucketName);
        StringUtils.isBooleanTrue(empty);
        Result<String> result = ossService.bucketExists(bucketName);
        log.info("FileController bucketExists end");
        return result;
    }

}
