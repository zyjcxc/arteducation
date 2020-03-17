package com.edu.admin.education.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.edu.admin.education.command.ArtTeacherSaveCommand;
import com.edu.admin.education.command.ArtTeacherUpdateCommand;
import com.edu.admin.education.dto.ArtTeacherDto;
import com.edu.admin.education.model.ArtTeacher;
import com.edu.admin.server.page.table.PageTableRequest;
import com.edu.admin.server.page.table.PageTableResponse;

import java.util.List;

/**
 * @author mengqa
 * @date 2019-11-6
 **/
public interface IArtTeacherService extends IService<ArtTeacher>  {

    ArtTeacherDto getById(Long id);

    ArtTeacherDto save(ArtTeacherSaveCommand command);

    ArtTeacherDto update(ArtTeacherUpdateCommand command);

//    List<ArtTeacherDto> list(Map<String, Object> params, Integer offset, Integer limit);
//
//    int count(Map<String, Object> params);

    int delete(Long id);

    List<ArtTeacherDto> findAll();

    PageTableResponse queryList(PageTableRequest request);


}

