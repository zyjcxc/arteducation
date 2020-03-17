package com.edu.admin.education.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.edu.admin.education.command.ArtTextbookTypeSaveCommand;
import com.edu.admin.education.command.ArtTextbookTypeUpdateCommand;
import com.edu.admin.education.dto.ArtTextbookTypeDto;
import com.edu.admin.education.model.ArtTextbookType;
import com.edu.admin.server.page.table.PageTableRequest;
import com.edu.admin.server.page.table.PageTableResponse;

import java.util.List;

/**
 * @author mengqa
 * @date 2019-11-6
 **/
public interface IArtTextbookTypeService extends IService<ArtTextbookType> {

    ArtTextbookTypeDto getById(Long id);

    ArtTextbookTypeDto save(ArtTextbookTypeSaveCommand command);

    ArtTextbookTypeDto update(ArtTextbookTypeUpdateCommand command);

    int delete(Long id);

    List<ArtTextbookTypeDto> findAll();

    PageTableResponse queryList(PageTableRequest request);

}

