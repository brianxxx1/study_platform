package com.wenshuo.eduService.service;

import com.wenshuo.eduService.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wenshuo.eduService.entity.vo.courseInfoVO;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author Wenshuo
 * @since 2021-11-14
 */
public interface EduCourseService extends IService<EduCourse> {

    EduCourse addCourseInfo(courseInfoVO courseInfoVO);

    courseInfoVO getCourseInfoByID(Long id);

    void updateCourse(courseInfoVO courseInfoVO);
}
