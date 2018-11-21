package com.edu.admin.education.exception;


import com.edu.admin.education.enums.ResultEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * 异常处理全局类
 *
 * @author xuqiang
 * @create 2017-11-23 下午2:41
 **/
@Data
public class HumanResourceException extends RuntimeException implements Serializable{


    private static final long serialVersionUID = -1343225163074053111L;

    private Integer code;

    public HumanResourceException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());

        this.code = resultEnum.getCode();
    }

    public HumanResourceException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
