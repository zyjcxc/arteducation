package com.edu.admin.education.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import java.util.Date;


@Table(name = "art_teacher")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArtTeacher extends BaseModel {

    /**
	* 名称
	*/
    private String name;

    private String namePy;

    /**
	* position
	*/
    private String position;

    /**
	* 简介
	*/
    private String content;

    /**
	* 照片url
	*/
    private String photoUrl;

    /**
	* 主讲老师名称
	*/
    private String sex;

    /**
	* 课程状态 1 正常 2删除
	*/
    private String state;

    private Date createtime;

    private Date updatetime;


}