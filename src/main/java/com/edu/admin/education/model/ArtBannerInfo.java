package com.edu.admin.education.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@TableName("art_banner_info")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArtBannerInfo extends BaseModel {

    /**
	* 位置 1.顶部 2.中部
	*/
    private Integer site;

    /**
	* 图片连接
	*/
    private String picurl;

    /**
	* 跳转url
	*/
    private String url;

    /**
	* 顺序
	*/
    private Integer sort;

    private String title;

    private Integer recommend;

    /**
     * 状态 0.草稿 1 正常 2删除
     */
    @TableLogic(delval = "2", value = "1")
    private String state;

    @TableField(fill = FieldFill.INSERT)
    private Date createtime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatetime;

    private String name;

    public enum Column {

        SITE("site"),
        SORT("sort"),
        RECOMMEND("recommend"),
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