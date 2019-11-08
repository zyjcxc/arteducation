package com.edu.admin.education.service;

import com.edu.admin.education.command.ArtAwardSaveCommand;
import com.edu.admin.education.command.ArtAwardUpdateCommand;
import com.edu.admin.education.dto.ArtAwardDto;

import java.util.List;
import java.util.Map;

/**
 * @author mengqa
 * @date 2019-11-6
 **/
public interface IArtAwardService {

    ArtAwardDto getById(Long id);

    ArtAwardDto save(ArtAwardSaveCommand command);

    ArtAwardDto update(ArtAwardUpdateCommand command);

    List<ArtAwardDto> list(Map<String, Object> params, Integer offset, Integer limit);

    int count(Map<String, Object> params);

    int delete(Long id);

    List<ArtAwardDto> findAll();

}

