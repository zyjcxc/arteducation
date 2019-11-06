package com.edu.admin.education.service;

import com.edu.admin.education.command.ArtNewsSaveCommand;
import com.edu.admin.education.command.ArtNewsUpdateCommand;
import com.edu.admin.education.dto.ArtNewsDto;

import java.util.List;
import java.util.Map;

/**
 * @author mengqa
 * @date 2019-11-5
 **/
public interface IArtNewsService {

    ArtNewsDto getById(Long id);

    ArtNewsDto save(ArtNewsSaveCommand command);

    ArtNewsDto update(ArtNewsUpdateCommand command);

    List<ArtNewsDto> list(Map<String, Object> params, Integer offset, Integer limit);

    int count(Map<String, Object> params);

    int delete(Long id);

    List<ArtNewsDto> findAll();

}

