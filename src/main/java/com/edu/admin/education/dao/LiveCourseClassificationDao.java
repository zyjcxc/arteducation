package com.edu.admin.education.dao;

import com.edu.admin.education.model.LiveCourseClassification;
import com.edu.admin.mymapper.MyMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 课程分类 模块数据层接口
 * @author mengqa
 * @date 2018-04-05
 */
@Mapper
public interface LiveCourseClassificationDao extends MyMapper<LiveCourseClassification> {


}
