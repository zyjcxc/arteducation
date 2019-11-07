package com.edu.admin.education.service;

import com.edu.admin.education.command.ArtTextbookTypeSaveCommand;
import com.edu.admin.education.command.ArtTextbookTypeUpdateCommand;
import com.edu.admin.education.dto.ArtTextbookTypeDto;

import java.util.List;
import java.util.Map;

/**
 * @author mengqa
 * @date 2019-11-6
 **/
public interface IArtTextbookTypeService {

    ArtTextbookTypeDto getById(Long id);

    ArtTextbookTypeDto save(ArtTextbookTypeSaveCommand command);

    ArtTextbookTypeDto update(ArtTextbookTypeUpdateCommand command);

    List<ArtTextbookTypeDto> list(Map<String, Object> params, Integer offset, Integer limit);

    int count(Map<String, Object> params);

    int delete(Long id);

    List<ArtTextbookTypeDto> findAll();

}

