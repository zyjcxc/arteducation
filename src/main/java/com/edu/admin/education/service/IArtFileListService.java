package com.edu.admin.education.service;

import com.edu.admin.education.command.ArtFileListSaveCommand;
import com.edu.admin.education.command.ArtFileListUpdateCommand;
import com.edu.admin.education.dto.ArtFileListDto;

import java.util.List;
import java.util.Map;

/**
 * @author mengqa
 * @date 2019-11-6
 **/
public interface IArtFileListService {

    ArtFileListDto getById(Long id);

    ArtFileListDto save(ArtFileListSaveCommand command);

    ArtFileListDto update(ArtFileListUpdateCommand command);

    List<ArtFileListDto> list(Map<String, Object> params, Integer offset, Integer limit);

    int count(Map<String, Object> params);

    int delete(Long id);

    List<ArtFileListDto> findAll();

}

