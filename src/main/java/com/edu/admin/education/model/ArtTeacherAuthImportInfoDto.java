package com.edu.admin.education.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArtTeacherAuthImportInfoDto extends BaseRowModel {

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

    @ExcelProperty(index = 5,value = "性别")
    private String sex;

    @ExcelProperty(index = 6,value = "申报专业")
    private String projectName;

    @ExcelProperty(index = 7,value = "职位")
    private String position ;

    @ExcelProperty(index = 8,value = "有效期")
    private String vatime ;

    @ExcelProperty(index = 9,value = "学校")
    private String school ;

}
