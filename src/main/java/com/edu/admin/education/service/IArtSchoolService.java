package com.edu.admin.education.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.edu.admin.education.model.ArtSchool;
import com.edu.admin.server.page.table.PageTableRequest;
import com.edu.admin.server.page.table.PageTableResponse;

import java.util.List;

/**
 * 模块业务接口
 * @author mengqa
 * @date 2018-04-05
 **/
public interface IArtSchoolService extends IService<ArtSchool>  {

    ArtSchool getById(Long id);

    boolean save(ArtSchool artSchool);

    int update(ArtSchool artSchool);

    int delete(Long id);

    List<ArtSchool> findAll();

    ArtSchool getByName(String name);

    PageTableResponse queryList(PageTableRequest request);
}

