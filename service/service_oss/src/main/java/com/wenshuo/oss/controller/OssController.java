package com.wenshuo.oss.controller;

import com.wenshuo.commonutils.R;
import com.wenshuo.oss.service.OssService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
@CrossOrigin
@RequestMapping("/eduOss")
public class OssController {

    @Resource
    private OssService ossService;

    @PostMapping("/upload")
    public  R uploadToOss(MultipartFile multipartFile){
        String URL = ossService.uploadAvatar(multipartFile);
        return R.ok().data("URL",URL);
    }
}
