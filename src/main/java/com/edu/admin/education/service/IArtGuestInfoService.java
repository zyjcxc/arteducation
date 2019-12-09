package com.edu.admin.education.service;

import com.edu.admin.education.command.ArtGuestInfoSaveCommand;
import com.edu.admin.education.dto.ArtGuestInfoDto;

import java.util.List;
import java.util.Map;

public interface IArtGuestInfoService {

    ArtGuestInfoDto save(ArtGuestInfoSaveCommand command);

    int count(Map<String, Object> params);

    List<ArtGuestInfoDto> list(Map<String, Object> params, Integer offset, Integer limit);
}
