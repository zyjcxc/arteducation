package com.edu.admin.education.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@TableName("art_teacher")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArtTeacher extends BaseModel {

    /**
	* 名称
	*/
    private String name;

    private String namePy;

    /**
	* position
	*/
    private String position;

    /**
	* 简介
	*/
    private String content;

    /**
	* 照片url
	*/
    private String photoUrl;

    /**
	* 主讲老师名称
	*/
    private String sex;

    /**
	* 课程状态 1 正常 2删除
	*/
    @TableLogic(delval = "2", value = "1")
    private String state;

    @TableField(fill = FieldFill.INSERT)
    private Date createtime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatetime;

    private String school;

    private String phone;

    private String title;

    public enum Column {

        NAME("name"),
        ID("id"),
        SEX("sex"),

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