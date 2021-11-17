package com.wenshuo.eduService.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class excelMetaData {
    @ExcelProperty(index=0)
    public String tierOne;
    @ExcelProperty(index = 1)
    public String tierTwo;
}
