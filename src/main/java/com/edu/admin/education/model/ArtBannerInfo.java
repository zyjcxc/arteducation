package com.edu.admin.education.model;

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

    /**
	* 状态 0.草稿 1 正常 2删除
	*/
    private String state;

    private String title;

    private Integer recommend;

    private Date createtime;

    private Date updatetime;

    private String name;


}