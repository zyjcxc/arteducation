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
    /**
     *   1 老师动态更新  2  购买课程更新  3  用户返现消息
     */
    MESSAGE_INFO_TYPE_TEACHER(1, "老师动态更新", "1"),
    MESSAGE_INFO_TYPE_CLASS(2, "购买课程更新", "2"),
    MESSAGE_INFO_TYPE_USER(3, "用户返现消息", "3"),
    /**
     *   推送消息类型 1 老师  2  课程  3  课节
     */
    MESSAGE_PUSH_TYPE_TEACHER(1, "老师", "1"),
    MESSAGE_PUSH_TYPE_CLASS(2, "课程", "2"),
    MESSAGE_PUSH_TYPE_LESSION(3, "课节", "3"),
    /**
     *   状态 1  已读取   0  新消息
     */
    MESSAGE_INFO_STATE_READ(1, "已读取", "1"),
    MESSAGE_INFO_STATE_UNREAD(2, "新消息", "2"),

    ORDER_STATUS_NORMAL(1, "新下单", "1"),
    ORDER_PAY_TYPE_CLASS(1, "课程支付", "1"),
    ORDER_PAY_TYPE_GIFT(2, "打赏支付", "2"),

    /**
     *   用户群体 0.全部 1.单个
     */
    MESSAGE_AUDIENCE_ALL(0, "全部", "0"),
    MESSAGE_AUDIENCE_SINGLE(1, "个人", "1"),

    HOME_CONTENT_TYPE_TEACHER(1, "公众号", "1"),
    HOME_CONTENT_TYPE_COURSE(2, "课程", "2"),
    HOME_CONTENT_TYPE_LESSION(3, "课节", "3"),

    ES_COURSE_INDEX_NAME(1, "课程索引", "education"),
    ES_COURSE_INDEX_TYPE(1, "课程索引类型", "course"),


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

