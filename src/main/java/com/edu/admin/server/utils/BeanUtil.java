package com.edu.admin.server.utils;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.IntegerConverter;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Map;

/**
 * @author mengqa
 * @date 2018-03-31 22:57
 **/
public class BeanUtil {

    /**
     * 单表直等查询
     * @param params 查询参数
     * @param cls class
     * @return 查询Object
     */
    public static <T> T getQueryObject(Map<String, Object> params, Class<T> cls) {
        T t = null;
        try {
            t = cls.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
//        MyNotice myNotice = new MyNotice();
        //处理时间格式
        DateConverter dateConverter = new DateConverter();
        //设置日期格式
        dateConverter.setPatterns(new String[]{"yyyy-MM-dd","yyyy-MM-dd HH:mm:ss"});
        BeanUtilsBean.getInstance().getConvertUtils().register(dateConverter, Date.class);
        BeanUtilsBean.getInstance().getConvertUtils().register(new IntegerConverter(null), Integer.class);
        try {
            BeanUtilsBean.getInstance().populate(t, params);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return t;
    }
}
