package com.edu.admin.education.enums;

import lombok.Getter;

/**
 * @author mengqa
 * @date 2018-04-26 15:53
 **/
@Getter
public enum ConstantsEnum {
    TEACHER_DEFAULT_PASSWORD(1, "默认密码", "123456"),
    PAY_TYPE_ALIPAY(1, "支付宝", "1"),
    PAY_TYPE_WECHAT(2, "微信", "2"),
    SEX_MAN(1, "男", "m"),
    SEX_WOMAN(2, "女", "g"),
    NO_SEX(3, "未知性别", ""),
    


    ;



    private Integer code;

    private String message;

    private String dataBase;


    ConstantsEnum(Integer code, String message, String dataBase) {
        this.code = code;
        this.message = message;
        this.dataBase = dataBase;
    }
}

