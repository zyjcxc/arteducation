package com.edu.admin.education.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import java.util.Date;

@Table(name = "art_guest_info")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArtGuestInfo extends BaseModel  {

    /**
	* 联系人 必填
	*/
    private String name;

    /**
	* 所在地
	*/
    private String address;

    /**
	* 电话 必填
	*/
    private String phone;

    /**
	* 电子邮件
	*/
    private String email;

    /**
	* 留言
	*/
    private String message;

    private Date createtime;

    private Date updatetime;

}