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
@ExcelTitles({"序号", "ID" ,"姓名", "性别", "国籍", "民族", "出生日期", "专业项目", "级别", "身份证号", "证书编号", "证书类型"})
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

    private String bookNo;

    private Integer bookType;

    private Date createtime;

    @Transient
    private String classificationName;
    @Transient
    private String activityName;
    @Transient
    private List<String> ids;
}