package com.edu.admin.education.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.edu.admin.education.command.ArtTextbookSaveCommand;
import com.edu.admin.education.command.ArtTextbookUpdateCommand;
import com.edu.admin.education.dto.ArtTextbookDto;
import com.edu.admin.education.model.ArtTextbook;
import com.edu.admin.server.page.table.PageTableRequest;
import com.edu.admin.server.page.table.PageTableResponse;

import java.util.List;

/**
 * @author mengqa
 * @date 2019-11-6
 **/
public interface IArtTextbookService extends IService<ArtTextbook> {

    ArtTextbookDto getById(Long id);

    ArtTextbookDto save(ArtTextbookSaveCommand command);

    ArtTextbookDto update(ArtTextbookUpdateCommand command);

//    List<ArtTextbookDto> list(Map<String, Object> params, Integer offset, Integer limit);
//
//    int count(Map<String, Object> params);

    int delete(Long id);

    List<ArtTextbookDto> findAll();

    PageTableResponse queryList(PageTableRequest request);

}

