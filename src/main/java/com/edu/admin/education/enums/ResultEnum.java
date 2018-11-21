package com.edu.admin.education.enums;

import lombok.Getter;


/**
 * 结果返回枚举类
 *
 * @author xuqiang
 * @create 2017-11-23 下午2:00
 **/
@Getter
public enum ResultEnum {

    SUCCESS(0, "成功"),

    PARAM_ERROR(1, "参数不正确"),

    MAX_ERROR(2, "课时超出最大边界限制"),

    MIN_ERROR(3, "课时超出最小边界限制"),

    CLASS_MODIFY_ERROR(4, "课程总课时数不能小于课节中的最大课时"),

    NO_RECORD(5, "无记录")

    ;


    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
