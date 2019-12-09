package com.edu.admin.education.service;

import com.edu.admin.education.command.ArtHomeStudentSaveCommand;
import com.edu.admin.education.command.ArtHomeStudentUpdateCommand;
import com.edu.admin.education.dto.ArtHomeStudentDto;

import java.util.List;
import java.util.Map;

/**
 * @author mengqa
 * @date 2019-11-6
 **/
public interface IArtHomeStudentService {

    ArtHomeStudentDto getById(Long id);

    ArtHomeStudentDto save(ArtHomeStudentSaveCommand command);

    ArtHomeStudentDto update(ArtHomeStudentUpdateCommand command);

    List<ArtHomeStudentDto> list(Map<String, Object> params, Integer offset, Integer limit);

    int count(Map<String, Object> params);

    int delete(Long id);

    List<ArtHomeStudentDto> findAll();

    List<ArtHomeStudentDto> findRecommendList();

}

