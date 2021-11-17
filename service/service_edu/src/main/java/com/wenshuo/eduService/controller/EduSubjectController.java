package com.wenshuo.eduService.controller;


import com.wenshuo.commonutils.R;
import com.wenshuo.eduService.entity.subject.tierOne;
import com.wenshuo.eduService.service.EduSubjectService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author Wenshuo
 * @since 2021-11-14
 */
@RestController
@CrossOrigin
@RequestMapping("/eduService/subject")
public class EduSubjectController {

    @Resource
    private EduSubjectService eduSubjectService;

    @PostMapping("/parseExcel")
    public R parseExcel(MultipartFile file) throws IOException {
        eduSubjectService.parse(file,eduSubjectService);
        return R.ok();
    }

    @GetMapping("/listAll")
    public  R listAll(){
        List<tierOne> list = eduSubjectService.listAll();
        return R.ok().data("list",list);
    }
}

