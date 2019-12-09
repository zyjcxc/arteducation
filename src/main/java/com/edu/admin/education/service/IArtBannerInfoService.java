package com.edu.admin.education.service;

import com.edu.admin.education.command.ArtBannerInfoSaveCommand;
import com.edu.admin.education.command.ArtBannerInfoUpdateCommand;
import com.edu.admin.education.dto.ArtBannerInfoDto;

import java.util.List;
import java.util.Map;

/**
 * @author mengqa
 * @date 2019-11-6
 **/
public interface IArtBannerInfoService {

    ArtBannerInfoDto getById(Long id);

    ArtBannerInfoDto save(ArtBannerInfoSaveCommand command);

    ArtBannerInfoDto update(ArtBannerInfoUpdateCommand command);

    List<ArtBannerInfoDto> list(Map<String, Object> params, Integer offset, Integer limit);

    int count(Map<String, Object> params);

    int delete(Long id);

    List<ArtBannerInfoDto> findAll();

    List<ArtBannerInfoDto> findAllBySite(Map<String, Object> params);

}

