package com.edu.admin.education.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@TableName("art_home_student")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArtHomeStudent extends BaseModel {

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