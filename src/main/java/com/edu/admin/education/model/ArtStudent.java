package com.edu.admin.education.model;


import com.edu.admin.education.excel.ExcelTitles;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

@Table(name = "art_student")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ExcelTitles({"证书类型","证书编号","姓名","拼音", "出生日期","身份证号" ,"民族","国籍", "性别", "专业项目", "级别", "考试时间", "成绩", "学校"})
public class ArtStudent extends BaseModel {

    private Integer classificationId;

    private String name;

    private String namePy;
    private String sex;
    private String country;
    private String nation;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date born;
    private String level;
    private String cardNo;
    private String state;
    private Integer activityId;
    private String score;

    private Long schoolId;

    private String bookNo;

    private Integer bookType;

    private Date createtime;

    @Transient
    private String classificationName;
    @Transient
    private String activityName;
    @Transient
    private String schoolName;


    @Transient
    private List<String> ids;
}