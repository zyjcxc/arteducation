package com.edu.admin.education.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.edu.admin.education.command.ArtAwardSaveCommand;
import com.edu.admin.education.command.ArtAwardUpdateCommand;
import com.edu.admin.education.dto.ArtAwardDto;
import com.edu.admin.education.model.ArtAward;
import com.edu.admin.server.page.table.PageTableRequest;
import com.edu.admin.server.page.table.PageTableResponse;

import java.util.List;

/**
 * @author mengqa
 * @date 2019-11-6
 **/
public interface IArtAwardService extends IService<ArtAward> {

    ArtAwardDto getById(Long id);

    ArtAwardDto save(ArtAwardSaveCommand command);

    ArtAwardDto update(ArtAwardUpdateCommand command);

    int delete(Long id);

    List<ArtAwardDto> findAll();

    PageTableResponse queryList(PageTableRequest request);

}

