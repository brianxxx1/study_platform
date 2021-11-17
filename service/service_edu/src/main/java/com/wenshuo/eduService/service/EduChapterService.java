package com.wenshuo.eduService.service;

import com.wenshuo.eduService.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wenshuo.eduService.entity.chapter.ChapterVO;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author Wenshuo
 * @since 2021-11-15
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVO> getChapterByCourseID(Long courseID);

    void addChapter(EduChapter eduChapter);

    void deleteChapter(Long id);
}
