package com.edu.admin.education.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
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
    @TableLogic(delval = "2", value = "1")
    private String state;

    @TableField(fill = FieldFill.INSERT)
    private Date createtime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatetime;


    private Long createUserId;

//    @Transient
    @TableField(exist = false)
    private String textBookName;

    /**
     *
     */
    private String picurl;

    public enum Column {

        NAME("name"),
        ID("id"),
        TITLE("title"),
        AUTHOR("author"),
        VERSION("version"),
        TEXTBOOKTYPEID("textbookTypeId"),


        ;
        private String key;
        Column(String name) {
            this.key = name;
        }
        public String key() {
            return key;
        }
    }

    //        if (params.containsKey("title")) {
//            criteria.andEqualTo("title", params.get("title"));
//        }
//        if (params.containsKey("author")) {
//            criteria.andEqualTo("author", params.get("author"));
//        }
//        if (params.containsKey("version")) {
//            criteria.andEqualTo("version", params.get("version"));
//        }
//        if (params.containsKey("textbookTypeId")) {
//            criteria.andEqualTo("textbookTypeId", params.get("textbookTypeId"));
//        }

}