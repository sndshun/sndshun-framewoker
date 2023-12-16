package com.sndshun.blog.controller.admin;

import com.sndshun.commons.tools.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

@RestController
@RequestMapping("/blog/admin/file")
public class FileAdminController {

    /**
     * @param file     文件
     * @param fileName 文件名称
     * @Description 上传文件
     */
    @PostMapping("/upload")
    public Result<Boolean> uploadFile(@RequestParam("file") MultipartFile file, String fileName) {
        String originalFilename = file.getOriginalFilename();
        //minioUtils.upload(file, originalFilename);
        HashMap map = new HashMap();
        //map.put("fileUrl", minioUtils.getFileUrl(originalFilename));
        //map.put("fileName", originalFilename);
        //return JsonObjectResult.success(map);
        return Result.ok(false);
    }
}
