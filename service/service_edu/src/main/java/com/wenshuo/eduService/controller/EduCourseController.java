package com.wenshuo.eduService.controller;


import com.wenshuo.commonutils.R;
import com.wenshuo.eduService.entity.EduCourse;
import com.wenshuo.eduService.entity.subject.tierTwo;
import com.wenshuo.eduService.entity.vo.courseInfoVO;
import com.wenshuo.eduService.service.EduCourseService;
import com.wenshuo.eduService.service.EduSubjectService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author Wenshuo
 * @since 2021-11-14
 */
@RestController
@RequestMapping("/eduService/course")
@CrossOrigin
public class EduCourseController {

    @Resource
    private EduCourseService eduCourseService;
    @Resource
    private EduSubjectService eduSubjectService;

    @PostMapping("/addCourseInfo")
    public R addCourseInfo(@RequestBody courseInfoVO courseInfoVO){
        EduCourse eduCourse = eduCourseService.addCourseInfo(courseInfoVO);
        return R.ok().data("course",eduCourse);
    }

    @GetMapping("/getCourseInfoByID/{id}")
    public R getCourseInfoByID(@PathVariable Long id){
        courseInfoVO courseInfoVO = eduCourseService.getCourseInfoByID(id);
        return R.ok().data("courseInfo",courseInfoVO);
    }

    @PostMapping("/updateCourse")
    public R updateCourse(@RequestBody courseInfoVO courseInfoVO){
        eduCourseService.updateCourse(courseInfoVO);
        return R.ok();
    }

    @GetMapping("/getTierTwoList/{id}")
    public R getTierTwoList(@PathVariable Long id){
        List<tierTwo> list = eduSubjectService.getTierTwoList(id);
        return R.ok().data("list",list);
    }
}

