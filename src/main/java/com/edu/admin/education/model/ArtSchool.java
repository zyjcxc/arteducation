package com.edu.admin.education.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import java.util.Date;

@Table(name = "art_school")
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