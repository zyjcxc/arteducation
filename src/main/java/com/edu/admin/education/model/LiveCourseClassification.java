package com.edu.admin.education.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import java.util.Date;

@Table(name = "live_course_classification")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LiveCourseClassification extends BaseModel {

    /**
     * 名称
     */
    private String name;

    /**
     * 状态 1 正常  0 删除
     */
    private String state;

    private String icon;

    private Date createtime;

}