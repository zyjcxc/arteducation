package com.edu.admin.education.vo;

import lombok.Data;

/**
 * http请求返回的最外层对象
 * Created by 许强
 * 2017-11-27 14:13
 */
@Data
public class ResultVo<T> {

    /** 错误码. */
    private Integer code;

    /** 提示信息. */
    private String msg;

    /** 具体内容. */
    private T data;

    /**
     * 总条数
     */
    private Long total;
}
