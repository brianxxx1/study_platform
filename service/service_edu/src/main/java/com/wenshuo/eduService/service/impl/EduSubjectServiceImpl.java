package com.wenshuo.eduService.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wenshuo.eduService.entity.EduCourse;
import com.wenshuo.eduService.entity.EduSubject;
import com.wenshuo.eduService.entity.excelMetaData;
import com.wenshuo.eduService.entity.subject.tierOne;
import com.wenshuo.eduService.entity.subject.tierTwo;
import com.wenshuo.eduService.listener.excelListener;
import com.wenshuo.eduService.mapper.EduCourseMapper;
import com.wenshuo.eduService.mapper.EduSubjectMapper;
import com.wenshuo.eduService.service.EduSubjectService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author Wenshuo
 * @since 2021-11-14
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Resource
    private EduCourseMapper eduCourseMapper;
    @Override
    public List<tierTwo> getTierTwoList(Long id) {
        EduCourse eduCourse = eduCourseMapper.selectById(id);
        String subjectParentId = eduCourse.getSubjectParentId();
        QueryWrapper<EduSubject> tierTwoQueryWrapper = new QueryWrapper<>();
        tierTwoQueryWrapper.eq("parent_id",subjectParentId);
        List<EduSubject> eduSubjects = baseMapper.selectList(tierTwoQueryWrapper);
        ArrayList<tierTwo> tierTwos = new ArrayList<>();
        for (EduSubject eduSubject : eduSubjects) {
            tierTwo tierTwo = new tierTwo();
            tierTwo.setTitle(eduSubject.getTitle());
            tierTwo.setId(eduSubject.getId());
            tierTwos.add(tierTwo);
        }
        return tierTwos;
    }

    @Override
    public List<tierOne> listAll() {
        ArrayList<tierOne> list = new ArrayList<>();
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id","0");
        List<EduSubject> eduSubjects = baseMapper.selectList(wrapper);
        for (EduSubject eduSubject : eduSubjects) {
            tierOne tierOne = new tierOne();
            tierOne.setId(eduSubject.getId());
            tierOne.setTitle(eduSubject.getTitle());
            QueryWrapper<EduSubject> wrapper1 = new QueryWrapper<>();
            wrapper1.eq("parent_id",tierOne.getId());
            List<EduSubject> children = baseMapper.selectList(wrapper1);
            ArrayList<tierTwo> tierTwos = new ArrayList<>();
            for (EduSubject child : children) {
                tierTwo tierTwo = new tierTwo();
                tierTwo.setId(child.getId());
                tierTwo.setTitle(child.getTitle());
                tierTwos.add((tierTwo));
            }
            tierOne.setChildren(tierTwos);
            list.add(tierOne);
        }
        return list;
    }

    @Override
    public void parse(MultipartFile multipartFile, EduSubjectService eduSubjectService) {
        try {
            EasyExcel.read(multipartFile.getInputStream(), excelMetaData.class,new excelListener(eduSubjectService)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
