package com.edu.admin.education.model;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.edu.admin.education.excel.ExcelTitles;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

//import javax.persistence.Transient;

@TableName("art_student")
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
    @TableLogic(delval = "2", value = "1")
    private String state;
    private Integer activityId;
    private String score;

    private Long schoolId;

    private String bookNo;

    private Integer bookType;

    @TableField(fill = FieldFill.INSERT)
    private Date createtime;

//    @Transient
    @TableField(exist = false)
    private String classificationName;
//    @Transient
    @TableField(exist = false)
    private String activityName;
//    @Transient
    @TableField(exist = false)
    private String schoolName;


//    @Transient
    @TableField(exist = false)
    private List<String> ids;

    public enum Column {

        CARD_NO("cardNo"),
        NAME("name"),
        ACTIVITY_ID("activityId"),
        CLASSIFICATION_ID("classificationId"),
        LEVEL("level"),
        STATE("state"),
        ID("id"),
        BOOK_TYPE("bookType"),
        BOOK_NO("bookNo"),
        SEX("sex"),
        SCHOOL("school"),

        ;
        private String key;
        Column(String name) {
            this.key = name;
        }
        public String key() {
            return key;
        }
    }

}