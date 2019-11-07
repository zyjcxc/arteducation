package com.edu.admin.education.service;

import com.edu.admin.education.command.ArtTeacherSaveCommand;
import com.edu.admin.education.command.ArtTeacherUpdateCommand;
import com.edu.admin.education.dto.ArtTeacherDto;

import java.util.List;
import java.util.Map;

/**
 * @author mengqa
 * @date 2019-11-6
 **/
public interface IArtTeacherService {

    ArtTeacherDto getById(Long id);

    ArtTeacherDto save(ArtTeacherSaveCommand command);

    ArtTeacherDto update(ArtTeacherUpdateCommand command);

    List<ArtTeacherDto> list(Map<String, Object> params, Integer offset, Integer limit);

    int count(Map<String, Object> params);

    int delete(Long id);

    List<ArtTeacherDto> findAll();

}

