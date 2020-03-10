package com.edu.admin.education.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.edu.admin.education.command.ArtAuthbookSaveCommand;
import com.edu.admin.education.command.ArtAuthbookUpdateCommand;
import com.edu.admin.education.dto.ArtAuthbookDto;
import com.edu.admin.education.model.ArtAuthbook;
import com.edu.admin.server.page.table.PageTableRequest;
import com.edu.admin.server.page.table.PageTableResponse;

import java.util.List;

/**
 * @author mengqa
 * @date 2019-11-8
 **/
public interface IArtAuthbookService extends IService<ArtAuthbook> {

    ArtAuthbookDto getById(Long id);

    ArtAuthbookDto save(ArtAuthbookSaveCommand command);

    ArtAuthbookDto update(ArtAuthbookUpdateCommand command);

    void delete(Long id);

    List<ArtAuthbookDto> findAll();

    PageTableResponse queryList(PageTableRequest request);

}

