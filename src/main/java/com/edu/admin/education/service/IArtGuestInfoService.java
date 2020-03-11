package com.edu.admin.education.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.edu.admin.education.command.ArtGuestInfoSaveCommand;
import com.edu.admin.education.dto.ArtGuestInfoDto;
import com.edu.admin.education.model.ArtGuestInfo;
import com.edu.admin.server.page.table.PageTableRequest;
import com.edu.admin.server.page.table.PageTableResponse;

public interface IArtGuestInfoService extends IService<ArtGuestInfo>  {

    ArtGuestInfoDto save(ArtGuestInfoSaveCommand command);

    PageTableResponse queryList(PageTableRequest request);
}
