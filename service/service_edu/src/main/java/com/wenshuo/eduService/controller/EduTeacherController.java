package com.wenshuo.eduService.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wenshuo.commonutils.R;
import com.wenshuo.eduService.entity.EduTeacher;
import com.wenshuo.eduService.entity.vo.TeacherQuery;
import com.wenshuo.eduService.service.EduTeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author Wenshuo
 * @since 2021-11-08
 */
@RestController
@RequestMapping("/eduService/teacher")
@CrossOrigin
@Slf4j
public class EduTeacherController {

    @Resource
    private EduTeacherService eduTeacherService;

    @GetMapping("/findAll")
    public R findALlTeachers(){
        List<EduTeacher> list = eduTeacherService.list(null);
        log.info(list.toString());
        return R.ok().data("list",list);
    }

    @DeleteMapping("/delete/{id}")
    public R removeById(@PathVariable Long id){
        boolean b = eduTeacherService.removeById(id);
        if (b){
            return R.ok();
        }else{
            return R.error();
        }

    }

    @GetMapping("/selectTeacherByPage/{current}")
    public R selectTeacherByPage(@PathVariable long current){
        Page<EduTeacher> eduTeacherPage = new Page<>(current,5);
        eduTeacherService.page(eduTeacherPage,null);
        return R.ok().data("total",eduTeacherPage.getTotal()).data("list",eduTeacherPage.getRecords());
    }

    @PostMapping("/pageSelectByCondition/{current}")
    public R pageSelectByCondition(@PathVariable Long current,@RequestBody(required = false) TeacherQuery tq){
        Page<EduTeacher> eduTeacherPage = new Page<>(current,5);
        QueryWrapper<EduTeacher> eduTeacherQueryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(tq.getName())){
            eduTeacherQueryWrapper.like("name",tq.getName());
        }
        if (!StringUtils.isEmpty(tq.getBegin())){
            eduTeacherQueryWrapper.ge("gmt_create",tq.getBegin());
        }
        if (!StringUtils.isEmpty(tq.getEnd())){
            eduTeacherQueryWrapper.le("gmt_create",tq.getEnd());
        }
        if (!StringUtils.isEmpty(tq.getLevel())){
            eduTeacherQueryWrapper.eq("level",tq.getLevel());
        }
        eduTeacherQueryWrapper.orderByDesc("gmt_create");
        eduTeacherService.page(eduTeacherPage,eduTeacherQueryWrapper);
        return R.ok().data("total",eduTeacherPage.getTotal()).data("list",eduTeacherPage.getRecords());


    }

    @PostMapping("/addTeacher")
    public R addTeacher(@RequestBody EduTeacher teacher){
        boolean save = eduTeacherService.save(teacher);
        if (save){
            return R.ok();
        }else{
            return R.error();
        }

    }

    @GetMapping("/getTeacherById/{id}")
    public R getTeacherById(@PathVariable String id){
        EduTeacher teacher = eduTeacherService.getById(id);
        return R.ok().data("teacher",teacher);

    }

    @PostMapping("/updateTeacher")
    public R updateTeacher(@RequestBody EduTeacher teacher){
        boolean b = eduTeacherService.updateById(teacher);
        if (b){
            return R.ok();
        }else{
            return R.error();
        }
    }



}

