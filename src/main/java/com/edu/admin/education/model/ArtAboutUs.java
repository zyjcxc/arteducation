package com.edu.admin.education.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import java.util.Date;

@Table(name = "art_about_us")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArtAboutUs extends BaseModel {

    /**
	* 联系人 必填
	*/
    private String content;

    private Date createtime;

    private Date updatetime;

}