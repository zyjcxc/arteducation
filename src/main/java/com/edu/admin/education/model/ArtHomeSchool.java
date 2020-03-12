package com.edu.admin.education.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@TableName("art_home_school")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArtHomeSchool extends BaseModel  {

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

    @TableField(fill = FieldFill.INSERT)
    private Date createtime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatetime;

    public enum Column {

        RECOMMEND("recommend"),
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