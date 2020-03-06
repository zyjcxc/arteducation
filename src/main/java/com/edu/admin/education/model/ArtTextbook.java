package com.edu.admin.education.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

//import javax.persistence.Transient;

@TableName("art_textbook")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArtTextbook extends BaseModel {

    /**
	* 教材分类id
	*/
    private Long textbookTypeId;

    /**
	* 教材名
	*/
    private String title;

    /**
	* 作者
	*/
    private String author;

    /**
	* 教材版本
	*/
    private String version;

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

//    @Transient
    @TableField(exist = false)
    private String textBookName;

    /**
     *
     */
    private String picurl;

}