package com.edu.admin.education.enums;

import lombok.Getter;

/**
 * 公众号状态
 */
@Getter
public enum PublicState {

    NORMAL(1, "正常", "1"),
    DELETE(2, "删除", "2"),
    FREZION(3, "冻结", "3"),
    // 课程分类的删除状态
    CLASS_DELETE(0, "删除", "0"),

    // 课程状态
    STUDYING(1, "学习中", "1"),
    OVERTIME(3, "失效", "3"),
    ;

    private Integer code;

    private String message;

    private String dataBase;

    PublicState(Integer code, String message, String dataBase) {
        this.code = code;
        this.message = message;
        this.dataBase = dataBase;
    }
}
