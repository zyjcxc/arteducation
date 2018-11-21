package com.edu.admin.education.service;

import com.edu.admin.education.model.ArtActivity;

import java.util.List;
import java.util.Map;

/**
 * 活动 模块业务接口
 * @author mengqa
 * @date 2018-11-21
 **/
public interface IArtActivityService {

    ArtActivity getById(Long id);

    int save(ArtActivity artAcitivty);

    int update(ArtActivity artAcitivty);

    List<ArtActivity> list(Map<String, Object> params, Integer offset, Integer limit);

    int count(Map<String, Object> params);

    int delete(Long id);

    List<ArtActivity> findAll();

}

