package com.edu.admin.education.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.edu.admin.education.command.ArtHomeSchoolSaveCommand;
import com.edu.admin.education.command.ArtHomeSchoolUpdateCommand;
import com.edu.admin.education.dto.ArtHomeSchoolDto;
import com.edu.admin.education.model.ArtHomeSchool;
import com.edu.admin.server.page.table.PageTableRequest;
import com.edu.admin.server.page.table.PageTableResponse;

import java.util.List;

/**
 * @author mengqa
 * @date 2019-11-6
 **/
public interface IArtHomeSchoolService extends IService<ArtHomeSchool> {

    ArtHomeSchoolDto getById(Long id);

    ArtHomeSchoolDto save(ArtHomeSchoolSaveCommand command);

    ArtHomeSchoolDto update(ArtHomeSchoolUpdateCommand command);

    int delete(Long id);

    List<ArtHomeSchoolDto> findAll();

    List<ArtHomeSchoolDto> findRecommendList();

    PageTableResponse queryList(PageTableRequest request);

}

