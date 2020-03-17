package com.edu.admin.education.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@TableName("art_news")
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
    @TableLogic(delval = "2", value = "1")
    private String state;

    /**
	* 1.新闻 2.公告
	*/
    private Integer type;

    private Long createUserId;

    @TableField(fill = FieldFill.INSERT)
    private Date createtime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatetime;

    public enum Column {

        TYPE("type"),
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