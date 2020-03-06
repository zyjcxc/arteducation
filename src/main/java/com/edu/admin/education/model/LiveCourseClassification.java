package com.edu.admin.education.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@TableName("live_course_classification")
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