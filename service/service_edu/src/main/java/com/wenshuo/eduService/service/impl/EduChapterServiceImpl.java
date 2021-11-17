package com.wenshuo.eduService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wenshuo.eduService.entity.EduChapter;
import com.wenshuo.eduService.entity.EduVideo;
import com.wenshuo.eduService.entity.chapter.ChapterVO;
import com.wenshuo.eduService.entity.chapter.VideoVO;
import com.wenshuo.eduService.mapper.EduChapterMapper;
import com.wenshuo.eduService.mapper.EduVideoMapper;
import com.wenshuo.eduService.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author Wenshuo
 * @since 2021-11-15
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {
    @Resource
    private EduVideoMapper eduVideoMapper;

    @Override
    public void deleteChapter(Long id) {
        QueryWrapper<EduVideo> eduVideoQueryWrapper = new QueryWrapper<>();
        eduVideoQueryWrapper.eq("chapter_id",id);
        Integer integer = eduVideoMapper.selectCount(eduVideoQueryWrapper);
        if (integer > 0){
            throw new RuntimeException("You Still Have Videos In This Chapter");
        }else{
            baseMapper.deleteById(id);
        }
    }

    @Override
    public void addChapter(EduChapter eduChapter) {
        baseMapper.insert(eduChapter);
    }

    @Override
    public List<ChapterVO> getChapterByCourseID(Long courseID) {
        QueryWrapper<EduChapter> eduChapterQueryWrapper = new QueryWrapper<>();
        eduChapterQueryWrapper.eq("course_id", courseID);
        List<EduChapter> eduChapters = baseMapper.selectList(eduChapterQueryWrapper);
        ArrayList<ChapterVO> chapterVOS = new ArrayList<>();
        for (EduChapter eduChapter : eduChapters) {
            ChapterVO chapterVO = new ChapterVO();
            chapterVO.setId(eduChapter.getId().toString());
            chapterVO.setTitle(eduChapter.getTitle());
            ArrayList<VideoVO> videoVOS = new ArrayList<>();
            QueryWrapper<EduVideo> eduVideoQueryWrapper = new QueryWrapper<>();
            eduVideoQueryWrapper.eq("course_id", courseID).eq("chapter_id", eduChapter.getId());
            List<EduVideo> eduVideos = eduVideoMapper.selectList(eduVideoQueryWrapper);
            for (EduVideo eduVideo : eduVideos) {
                VideoVO videoVO = new VideoVO();
                videoVO.setId(eduVideo.getId().toString());
                videoVO.setTitle(eduVideo.getTitle());
                videoVOS.add(videoVO);
            }
            chapterVO.setVideos(videoVOS);
            chapterVOS.add(chapterVO);
        }

        return chapterVOS;
    }
}
