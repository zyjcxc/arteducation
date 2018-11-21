package com.edu.admin.mymapper.ext;

import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * 自定义Mapper
 * @param <T>
 */
public interface SelectByCustomMapper<T> {

    @SelectProvider(
            type = SelectCustomProvider.class,
            method = "dynamicSQL"
    )
    List<T> selectByCustom(T var1);
}
