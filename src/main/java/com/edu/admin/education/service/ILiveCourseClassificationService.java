package com.edu.admin.education.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.edu.admin.education.model.LiveCourseClassification;
import com.edu.admin.server.page.table.PageTableRequest;
import com.edu.admin.server.page.table.PageTableResponse;

import java.util.List;

/**
 * 课程分类 模块业务接口
 * @author mengqa
 * @date 2018-04-05
 **/
public interface ILiveCourseClassificationService extends IService<LiveCourseClassification> {

    LiveCourseClassification getById(Long id);

    boolean save(LiveCourseClassification liveCourseClassification);

    int update(LiveCourseClassification liveCourseClassification);

//    List<LiveCourseClassification> list(Map<String, Object> params, Integer offset, Integer limit);
//
//    int count(Map<String, Object> params);

    int delete(Long id);

    List<LiveCourseClassification> findAll();

    LiveCourseClassification getByName(String name);

    PageTableResponse queryList(PageTableRequest request);


}

