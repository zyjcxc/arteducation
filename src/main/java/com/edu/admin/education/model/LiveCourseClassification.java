package com.edu.admin.education.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
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
    @TableLogic(delval = "2", value = "1")
    private String state;

    private String icon;

    @TableField(fill = FieldFill.INSERT)
    private Date createtime;

    public enum Column {

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