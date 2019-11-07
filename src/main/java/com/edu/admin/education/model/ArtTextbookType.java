package com.edu.admin.education.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import java.util.Date;

@Table(name = "art_textbook_type")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArtTextbookType extends BaseModel {


    /**
	* 分类名称
	*/
    private String name;

    /**
	* 状态 1.正常
	*/
    private String state;

    private Date createtime;

    private Date updatetime;


}