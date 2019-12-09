package com.edu.admin.education.service;

import com.edu.admin.education.command.ArtHomeSchoolSaveCommand;
import com.edu.admin.education.command.ArtHomeSchoolUpdateCommand;
import com.edu.admin.education.dto.ArtHomeSchoolDto;

import java.util.List;
import java.util.Map;

/**
 * @author mengqa
 * @date 2019-11-6
 **/
public interface IArtHomeSchoolService {

    ArtHomeSchoolDto getById(Long id);

    ArtHomeSchoolDto save(ArtHomeSchoolSaveCommand command);

    ArtHomeSchoolDto update(ArtHomeSchoolUpdateCommand command);

    List<ArtHomeSchoolDto> list(Map<String, Object> params, Integer offset, Integer limit);

    int count(Map<String, Object> params);

    int delete(Long id);

    List<ArtHomeSchoolDto> findAll();

    List<ArtHomeSchoolDto> findRecommendList();

}

