package com.edu.admin.education.service;

import com.edu.admin.education.model.ArtActivity;
import com.edu.admin.server.page.table.PageTableRequest;
import com.edu.admin.server.page.table.PageTableResponse;

import java.util.List;

/**
 * 活动 模块业务接口
 * @author mengqa
 * @date 2018-11-21
 **/
public interface IArtActivityService {

    ArtActivity getById(Long id);

    boolean save(ArtActivity artAcitivty);

    int update(ArtActivity artAcitivty);

    int delete(Long id);

    List<ArtActivity> findAll();

    ArtActivity getByActivityName(String activityName);

    PageTableResponse queryList(PageTableRequest request);
}

