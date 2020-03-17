package com.edu.admin.education.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.edu.admin.education.command.ArtHomeStudentSaveCommand;
import com.edu.admin.education.command.ArtHomeStudentUpdateCommand;
import com.edu.admin.education.dto.ArtHomeStudentDto;
import com.edu.admin.education.model.ArtHomeStudent;
import com.edu.admin.server.page.table.PageTableRequest;
import com.edu.admin.server.page.table.PageTableResponse;

import java.util.List;

/**
 * @author mengqa
 * @date 2019-11-6
 **/
public interface IArtHomeStudentService extends IService<ArtHomeStudent> {

    ArtHomeStudentDto getById(Long id);

    ArtHomeStudentDto save(ArtHomeStudentSaveCommand command);

    ArtHomeStudentDto update(ArtHomeStudentUpdateCommand command);

    int delete(Long id);

    List<ArtHomeStudentDto> findAll();

    List<ArtHomeStudentDto> findRecommendList();

    PageTableResponse queryList(PageTableRequest request);
}

