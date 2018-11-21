package com.edu.admin.education.service;

import com.edu.admin.education.model.LiveCourseClassification;

import java.util.List;
import java.util.Map;

/**
 * 课程分类 模块业务接口
 * @author mengqa
 * @date 2018-04-05
 **/
public interface ILiveCourseClassificationService {

    LiveCourseClassification getById(Long id);

    int save(LiveCourseClassification liveCourseClassification);

    int update(LiveCourseClassification liveCourseClassification);

    List<LiveCourseClassification> list(Map<String, Object> params, Integer offset, Integer limit);

    int count(Map<String, Object> params);

    int delete(Long id);

    List<LiveCourseClassification> findAll();
}

