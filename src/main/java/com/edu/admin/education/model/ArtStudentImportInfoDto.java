package com.edu.admin.education.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArtStudentImportInfoDto extends BaseRowModel {

    @ExcelProperty(index = 1, value = "姓名")
    private String name;

    @ExcelProperty(index = 2, value = "拼音")
    private String namePy;

    @ExcelProperty(index = 3,value = "出生日期")
    private String born;

    @ExcelProperty(index = 4,value = "身份证")
    private String cardNo;

    @ExcelProperty(index = 5,value = "民族")
    private String nation;

    @ExcelProperty(index = 6,value = "国籍")
    private String country;

    @ExcelProperty(index = 7,value = "性别")
    private String sex;

    @ExcelProperty(index = 8,value = "申报专业")
    private String projectName;

    @ExcelProperty(index = 9,value = "级别")
    private String level;

    @ExcelProperty(index = 11,value = "活动名称")
    private String activityName;


}
