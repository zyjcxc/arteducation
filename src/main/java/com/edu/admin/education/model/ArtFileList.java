package com.edu.admin.education.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@TableName("art_file_list")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArtFileList extends BaseModel {

    private String title;

    private String url;

    /**
	* 联系人 必填
	*/
    private String content;

    private Date createtime;

    private Date updatetime;

}