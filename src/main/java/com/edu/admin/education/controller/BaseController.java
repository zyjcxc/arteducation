package com.edu.admin.education.controller;


import com.edu.admin.education.enums.ResultEnum;
import com.edu.admin.education.exception.HumanResourceException;
import com.edu.admin.server.page.table.PageTableRequest;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.*;

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

    protected Map<String,Integer> getPageOffsetAndLimit(PageTableRequest request) {
        Map<String, Integer> page = new HashMap<>();
        Integer offset = 0;
        Integer limit = 20;
        if (request.getOffset() == null && request.getParams().containsKey("offset")) {
            String offsetStr = (String) request.getParams().get("offset");
            offset = Integer.parseInt(offsetStr);
        } else if (request.getOffset() != null) {
            offset = request.getOffset();
        }
        if (request.getLimit() == null && request.getParams().containsKey("limit")) {
            String limitStr = (String) request.getParams().get("limit");
            limit = Integer.parseInt(limitStr);
        } else if (request.getLimit() != null) {
            limit = request.getLimit();
        }
        page.put("offset", offset);
        page.put("limit", limit);
        return page;
    }
}
