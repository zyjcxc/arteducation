package com.edu.admin.education.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import java.util.Date;

@Table(name = "art_award")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArtAward extends BaseModel {

    /**
     *
     */
    private String picurl;

    /**
	* 奖项、证书名
	*/
    private String title;

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