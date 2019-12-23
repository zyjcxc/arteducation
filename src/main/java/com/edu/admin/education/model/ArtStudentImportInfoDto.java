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

    @ExcelProperty(index = 0,value = "证书类型")
    private String bookType ;

    @ExcelProperty(index = 1,value = "证书编号")
    private String bookNo;

    @ExcelProperty(index = 2, value = "姓名")
    private String name;

    @ExcelProperty(index = 3, value = "拼音")
    private String namePy;

    @ExcelProperty(index = 4,value = "出生日期")
    private String born;

    @ExcelProperty(index = 5,value = "身份证")
    private String cardNo;

    @ExcelProperty(index = 6,value = "民族")
    private String nation;

    @ExcelProperty(index = 7,value = "国籍")
    private String country;

    @ExcelProperty(index = 8,value = "性别")
    private String sex;

    @ExcelProperty(index = 9,value = "申报专业")
    private String projectName;

    @ExcelProperty(index = 10,value = "级别")
    private String level;

    @ExcelProperty(index = 11,value = "活动名称")
    private String activityName;

    @ExcelProperty(index = 12,value = "成绩")
    private String score ;

    @ExcelProperty(index = 13,value = "学校")
    private String school ;

}
