package com.edu.admin.education.service;

import com.edu.admin.education.command.ArtTextbookSaveCommand;
import com.edu.admin.education.command.ArtTextbookUpdateCommand;
import com.edu.admin.education.dto.ArtTextbookDto;

import java.util.List;
import java.util.Map;

/**
 * @author mengqa
 * @date 2019-11-6
 **/
public interface IArtTextbookService {

    ArtTextbookDto getById(Long id);

    ArtTextbookDto save(ArtTextbookSaveCommand command);

    ArtTextbookDto update(ArtTextbookUpdateCommand command);

    List<ArtTextbookDto> list(Map<String, Object> params, Integer offset, Integer limit);

    int count(Map<String, Object> params);

    int delete(Long id);

    List<ArtTextbookDto> findAll();

}

