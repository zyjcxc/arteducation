package com.edu.admin.education.service;

import com.edu.admin.education.model.ArtSchool;

import java.util.List;
import java.util.Map;

/**
 * 模块业务接口
 * @author mengqa
 * @date 2018-04-05
 **/
public interface IArtSchoolService {

    ArtSchool getById(Long id);

    int save(ArtSchool liveCourseClassification);

    int update(ArtSchool liveCourseClassification);

    List<ArtSchool> list(Map<String, Object> params, Integer offset, Integer limit);

    int count(Map<String, Object> params);

    int delete(Long id);

    List<ArtSchool> findAll();

    ArtSchool getByName(String name);

}

