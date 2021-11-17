package com.wenshuo.eduService.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wenshuo.eduService.entity.EduSubject;
import com.wenshuo.eduService.entity.excelMetaData;
import com.wenshuo.eduService.service.EduSubjectService;

public class excelListener extends AnalysisEventListener<excelMetaData> {


    private EduSubjectService eduSubjectService;


    public excelListener(EduSubjectService eduSubjectService) {
        this.eduSubjectService = eduSubjectService;
    }

    @Override
    public void invoke(excelMetaData excelMetaData, AnalysisContext analysisContext) {
        if (excelMetaData == null) {
            throw new NullPointerException("excel data is null");
        } else {
            String tierOne = excelMetaData.getTierOne();
            String tierTwo = excelMetaData.getTierTwo();
            EduSubject tierOneSubject = tierOneExists(tierOne);
            if (tierOneSubject==null){
                tierOneSubject = new EduSubject();
                tierOneSubject.setTitle(tierOne).setParentId(0L);
                eduSubjectService.save(tierOneSubject);
            }
            Long parentId = tierOneSubject.getId();
            if (!tierTwoExists(tierTwo,parentId)){
                EduSubject eduSubject = new EduSubject();
                eduSubject.setTitle(tierTwo).setParentId(parentId);
                eduSubjectService.save(eduSubject);
            }

        }
    }

    public EduSubject tierOneExists(String tierOneName) {
        QueryWrapper<EduSubject> eduSubjectQueryWrapper = new QueryWrapper<>();
        eduSubjectQueryWrapper.eq("title", tierOneName).eq("parent_id", "0");
        return eduSubjectService.getOne(eduSubjectQueryWrapper);
    }

    public boolean tierTwoExists(String terTwoName, Long parentID) {
        QueryWrapper<EduSubject> eduSubjectQueryWrapper = new QueryWrapper<>();
        eduSubjectQueryWrapper.eq("title", terTwoName).eq("parent_id",parentID );
        EduSubject one = eduSubjectService.getOne(eduSubjectQueryWrapper);
        return (one != null);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
