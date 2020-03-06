package com.edu.admin.education.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@TableName("art_school")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArtSchool extends BaseModel {

    private String name;

    private String picurl;

    private String content;

    private Date createtime;

    private Date updatetime;

}