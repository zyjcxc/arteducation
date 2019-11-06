package com.edu.admin.education.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import java.util.Date;

@Table(name = "art_news")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArtNews extends BaseModel{

    /**
	* 标题
	*/
    private String title;

    /**
	* 作者
	*/
    private String author;

    /**
	* 来源
	*/
    private String source;

    private String content;

    /**
	* 状态 0.草稿 1 正常 2删除
	*/
    private String state;

    /**
	* 1.新闻 2.公告
	*/
    private Integer type;

    private Long createUserId;

    private Date createtime;

    private Date updatetime;


}