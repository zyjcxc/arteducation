package com.edu.admin.education.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.edu.admin.education.command.ArtBannerInfoSaveCommand;
import com.edu.admin.education.command.ArtBannerInfoUpdateCommand;
import com.edu.admin.education.dto.ArtBannerInfoDto;
import com.edu.admin.education.model.ArtBannerInfo;
import com.edu.admin.server.page.table.PageTableRequest;
import com.edu.admin.server.page.table.PageTableResponse;

import java.util.List;
import java.util.Map;

/**
 * @author mengqa
 * @date 2019-11-6
 **/
public interface IArtBannerInfoService extends IService<ArtBannerInfo> {

    ArtBannerInfoDto getById(Long id);

    ArtBannerInfoDto save(ArtBannerInfoSaveCommand command);

    ArtBannerInfoDto update(ArtBannerInfoUpdateCommand command);

//    List<ArtBannerInfoDto> list(Map<String, Object> params, Integer offset, Integer limit);

//    int count(Map<String, Object> params);

    int delete(Long id);

    List<ArtBannerInfoDto> findAll();

    List<ArtBannerInfoDto> findAllBySite(Map<String, Object> params);

    PageTableResponse queryList(PageTableRequest request);

}

