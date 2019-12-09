package com.edu.admin.education.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import java.util.Date;

@Table(name = "art_home_school")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArtHomeSchool extends BaseModel  {

    private String name;

    private String picurl;

    /**
	* 联系人 必填
	*/
    private String content;

    private Integer sort;

    /**
	* 1.首页展示 0.首页不展示
	*/
    private Integer recommend;

    private Date createtime;

    private Date updatetime;

}