package com.edu.admin.education.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@TableName("art_activity")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArtActivity extends BaseModel {

    /**
     * 名称
     */
    private String name;

    /**
     * 图标地址
     */
    private String icon;

    /**
     * 状态 1 正常  2 删除
     */
    @TableLogic(delval = "2", value = "1")
    private String state;
    @TableField(fill = FieldFill.INSERT)
    private Date createtime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatetime;

    public enum Column {

        STATE("state"),
        ID("id"),
        STATUS("status"),
        TITLE("title"),

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