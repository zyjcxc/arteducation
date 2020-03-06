package com.edu.admin.education.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@TableName("art_authbook")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArtAuthbook extends BaseModel {

    /**
     *
     */
    private String picurl;

    /**
	* 授权书名称
	*/
    private String title;

    /**
	* 作者
	*/
    private String author;

    /**
	* 简介
	*/
    private String content;

    /**
	* 状态 0.草稿 1 正常 2删除
	*/
    private String state;

    private Long createUserId;

    private Date createtime;

    private Date updatetime;
}