package com.wenshuo.eduService.controller;


import com.wenshuo.commonutils.R;
import com.wenshuo.eduService.entity.EduVideo;
import com.wenshuo.eduService.service.EduVideoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author Wenshuo
 * @since 2021-11-14
 */
@RestController
@CrossOrigin
@RequestMapping("/eduService/video")
public class EduVideoController {

    @Resource
    private EduVideoService eduVideoService;

    @PostMapping("/addVideo")
    public R addVideo(@RequestBody EduVideo eduVideo){
        eduVideoService.save(eduVideo);
        return R.ok();
    }

    @DeleteMapping("/deleteVideo/{id}")
    public R deleteVideo(@PathVariable Long id){
        eduVideoService.removeById(id);
        return R.ok();
    }
}

