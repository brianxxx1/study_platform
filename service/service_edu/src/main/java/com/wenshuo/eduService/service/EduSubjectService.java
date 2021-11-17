package com.wenshuo.eduService.service;

import com.wenshuo.eduService.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wenshuo.eduService.entity.subject.tierOne;
import com.wenshuo.eduService.entity.subject.tierTwo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author Wenshuo
 * @since 2021-11-14
 */
public interface EduSubjectService extends IService<EduSubject> {

    void parse(MultipartFile multipartFile, EduSubjectService eduSubjectService);

    List<tierOne> listAll();

    List<tierTwo> getTierTwoList(Long id);
}
