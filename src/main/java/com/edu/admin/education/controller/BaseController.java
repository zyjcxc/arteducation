package com.edu.admin.education.controller;


import com.edu.admin.education.enums.ResultEnum;
import com.edu.admin.education.exception.HumanResourceException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 * 公共控制器
 *
 * @author mengqa
 **/
@Data
@Slf4j
public abstract class BaseController<T> {

    protected boolean getexcludeError(BindingResult bindingResult, String... props) {
        List<FieldError> list = bindingResult.getFieldErrors();
        List arrayList = new ArrayList(list);
        Iterator<FieldError> iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            String field = iterator.next().getField();
            for (String prop : props) {
                if (field.equalsIgnoreCase(prop)) {
                    iterator.remove();
                }
            }

        }

        return arrayList.size() > 0;
    }

    /**
     * 验证表单参数
     * @param bindingResult 验证
     */
    protected void validFormInfo(BindingResult bindingResult) {

        System.out.println("bindingResult = " + bindingResult);
        Optional.of(getexcludeError(bindingResult)).filter(r -> r).map(s -> {
            throw new HumanResourceException(ResultEnum.PARAM_ERROR.getCode(),
                    "表单参数" + bindingResult.getFieldError().getDefaultMessage());
        });

//        boolean flag = getexcludeError(bindingResult);
//        if (flag) {
//            throw new HumanResourceException(ResultEnum.PARAM_ERROR.getCode(),
//                    "表单参数" + bindingResult.getFieldError().getDefaultMessage());
//        }
    }
}
