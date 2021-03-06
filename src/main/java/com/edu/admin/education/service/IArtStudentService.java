package com.edu.admin.education.service;

import com.edu.admin.education.model.ArtStudent;
import com.edu.admin.education.model.ArtStudentImportInfoDto;

import java.util.List;
import java.util.Map;

public interface IArtStudentService {

    List<ArtStudent> list(Map<String, Object> params, Integer offset, Integer limit);

    int count(Map<String, Object> params);

    int deleteLogic(ArtStudent artStudent);

    void deleteLogicBatch(List<String> ids);

    void save(ArtStudent artStudent);

    ArtStudent getById(Long id);

    void update(ArtStudent artStudent);

    void saveDatas(List<ArtStudentImportInfoDto> dtoList);

    ArtStudent getByCondition(Map<String, Object> params);

}
