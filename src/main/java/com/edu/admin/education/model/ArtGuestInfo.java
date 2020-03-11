package com.edu.admin.education.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@TableName("art_guest_info")
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

    @TableField(fill = FieldFill.INSERT)
    private Date createtime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatetime;

    public enum Column {

        NAME("name"),
        ID("id"),

        ;
        private String key;
        Column(String name) {
            this.key = name;
        }
        public String key() {
            return key;
        }
    }

}