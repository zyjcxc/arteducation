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

@Table(name = "art_teacher_auth")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ExcelTitles({"证书类型","证书编号","姓名","拼音", "出生日期", "性别", "专业项目", "职位", "有效期","学校"})
public class ArtTeacherAuth extends BaseModel{

    /**
	* 名称
	*/
    private String name;

    /**
	* 姓名拼音
	*/
    private String namePy;

    /**
	* 职位
	*/
    private String position;

    /**
	* 性别 m 男 g女
	*/
    private String sex;

    /**
	* 有效期限
	*/
    private String vatime;

    private String bookNo;

    private Integer bookType;

    /**
	* 状态 1 正常 2删除
	*/
    private String state;

    private Date createtime;

    private Date updatetime;

    private Long schoolId;

    private Long classificationId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date born;

    @Transient
    private String classificationName;
    @Transient
    private String schoolName;

    @Transient
    private List<String> ids;


}