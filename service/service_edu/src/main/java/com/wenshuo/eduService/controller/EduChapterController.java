package com.wenshuo.eduService.controller;


import com.wenshuo.commonutils.R;
import com.wenshuo.eduService.entity.EduChapter;
import com.wenshuo.eduService.entity.chapter.ChapterVO;
import com.wenshuo.eduService.service.EduChapterService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author Wenshuo
 * @since 2021-11-15
 */
@RestController
@RequestMapping("/eduService/chapter")
@CrossOrigin
public class EduChapterController {

    @Resource
    private EduChapterService eduChapterService;
    @GetMapping("/getChapterByCourseID/{courseID}")
    public R getChapterByCourseID( @PathVariable Long courseID){
        List<ChapterVO> list =  eduChapterService.getChapterByCourseID(courseID);
        return R.ok().data("list",list);
    }


    @PostMapping("/addChapter")
    public R addChapter(@RequestBody EduChapter eduChapter){
        eduChapterService.addChapter(eduChapter);
        return R.ok();
    }

    @GetMapping("/getChapterByID/{id}")
    public R getChapterByID(@PathVariable Long id){
        EduChapter eduChapter = eduChapterService.getById(id);
        return R.ok().data("eduChapter",eduChapter);
    }

    @PostMapping("/editChapter")
    public R editChapter(@RequestBody EduChapter eduChapter){
        eduChapterService.updateById(eduChapter);
        return R.ok();
    }

    @DeleteMapping("/deleteChapter/{id}")
    public R deleteChapter(@PathVariable Long id){
        eduChapterService.deleteChapter(id);
        return R.ok();
    }

}

