package com.wenshuo.eduService.service.impl;

import com.wenshuo.eduService.entity.EduCourse;
import com.wenshuo.eduService.entity.EduCourseDescription;
import com.wenshuo.eduService.entity.vo.courseInfoVO;
import com.wenshuo.eduService.mapper.EduCourseDescriptionMapper;
import com.wenshuo.eduService.mapper.EduCourseMapper;
import com.wenshuo.eduService.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author Wenshuo
 * @since 2021-11-14
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Resource
    private EduCourseDescriptionMapper eduCourseDescriptionMapper;

    @Override
    @Transactional
    public void updateCourse(courseInfoVO courseInfoVO) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVO,eduCourse);
        eduCourse.setId(new Long(courseInfoVO.getId()));
        int i = baseMapper.updateById(eduCourse);
        if (i ==0){
            throw new RuntimeException("eduCourse Update failed");
        }
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        BeanUtils.copyProperties(courseInfoVO,eduCourseDescription);
        eduCourseDescription.setId(new Long(courseInfoVO.getId()));
        int i1 = eduCourseDescriptionMapper.updateById(eduCourseDescription);
        if (i1==0){
            throw new RuntimeException("eduCourseDescription Update failed");
        }
    }

    @Override
    public courseInfoVO getCourseInfoByID(Long id) {
        courseInfoVO courseInfoVO = new courseInfoVO();
        EduCourse eduCourse = baseMapper.selectById(id);
        BeanUtils.copyProperties(eduCourse,courseInfoVO);
        EduCourseDescription eduCourseDescription = eduCourseDescriptionMapper.selectById(id);
        BeanUtils.copyProperties(eduCourseDescription,courseInfoVO);
        courseInfoVO.setId(eduCourse.getId().toString());
        return courseInfoVO;
    }

    @Override
    public EduCourse addCourseInfo(courseInfoVO courseInfoVO) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVO,eduCourse);
        baseMapper.insert(eduCourse);

        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setId(eduCourse.getId());
        eduCourseDescription.setDescription(courseInfoVO.getDescription());
        eduCourseDescriptionMapper.insert(eduCourseDescription);
        return eduCourse;
    }
}
