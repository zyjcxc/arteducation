package com.edu.admin.education.enums;

import lombok.Getter;

/**
 * 各处个数常量
 * @author mengqa
 * @date 2018-04-18 13:58
 **/
@Getter
public enum  MaxSizeLimit {

    /**
     * 首页推荐个数
     */
    HOME_CONTENT_RECOMMEND_SIZE(1, 5),
    /**
     * 礼物个数
     */
    GIFT_NUM_SIZE(1, 6)

    ;


    private Integer code;

    private Integer size;

    MaxSizeLimit(Integer code, Integer size) {
        this.code = code;
        this.size = size;
    }
}
