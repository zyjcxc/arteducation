package com.edu.admin.education.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.edu.admin.education.command.ArtNewsSaveCommand;
import com.edu.admin.education.command.ArtNewsUpdateCommand;
import com.edu.admin.education.dto.ArtNewsDto;
import com.edu.admin.education.model.ArtNews;
import com.edu.admin.server.page.table.PageTableRequest;
import com.edu.admin.server.page.table.PageTableResponse;

import java.util.List;

/**
 * @author mengqa
 * @date 2019-11-5
 **/
public interface IArtNewsService extends IService<ArtNews> {

    ArtNewsDto getById(Long id);

    ArtNewsDto save(ArtNewsSaveCommand command);

    ArtNewsDto update(ArtNewsUpdateCommand command);

    int delete(Long id);

    List<ArtNewsDto> findAll();

    PageTableResponse queryList(PageTableRequest request);

}

