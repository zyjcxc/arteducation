package com.edu.admin.education.service;

import com.edu.admin.education.command.ArtAuthbookSaveCommand;
import com.edu.admin.education.command.ArtAuthbookUpdateCommand;
import com.edu.admin.education.dto.ArtAuthbookDto;

import java.util.List;
import java.util.Map;

/**
 * @author mengqa
 * @date 2019-11-8
 **/
public interface IArtAuthbookService {

    ArtAuthbookDto getById(Long id);

    ArtAuthbookDto save(ArtAuthbookSaveCommand command);

    ArtAuthbookDto update(ArtAuthbookUpdateCommand command);

    List<ArtAuthbookDto> list(Map<String, Object> params, Integer offset, Integer limit);

    int count(Map<String, Object> params);

    int delete(Long id);

    List<ArtAuthbookDto> findAll();

}

