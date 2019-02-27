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

    NO_RECORD(5, "无记录"),

    REPEAT_RECORD(6, "重复记录"),

    NO_ACTIVITY_RECORD(7, "导入失败, 记录中存在活动名称为空的记录"),

    NO_TYPE_RECORD(8, "导入失败, 记录中存在专业项目为空的记录"),

    IMPORT_ERROR(9, "导入失败"),

    PARAMS_ERROR_CARD_NO(10, "身份证信息不能为空"),

    PARAMS_ERROR_BORN(11, "出生日期不能为空"),
    PARAMS_ERROR_PROJECT(12, "比赛专业不能为空"),
    PARAMS_ERROR_ACTIVITY(13, "活动不能为空"),
    PARAMS_ERROR_NAME(14, "姓名不能为空"),
    PARAMS_ERROR_P(15, "民族不能为空"),
    PARAMS_ERROR_N(16, "国籍不能为空"),

    REPEAT_STUDENT_RECORD(17, "该学生已经存在该活动中，不能重复录入"),


    ;


    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
