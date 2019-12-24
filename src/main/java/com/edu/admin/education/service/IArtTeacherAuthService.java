package com.edu.admin.education.service;

import com.edu.admin.education.model.ArtTeacherAuth;

import java.util.List;
import java.util.Map;

public interface IArtTeacherAuthService {

    List<ArtTeacherAuth> list(Map<String, Object> params, Integer offset, Integer limit);

    int count(Map<String, Object> params);

    int deleteLogic(ArtTeacherAuth artStudent);

    void deleteLogicBatch(List<String> ids);

    void save(ArtTeacherAuth artStudent);

    ArtTeacherAuth getById(Long id);

    void update(ArtTeacherAuth artStudent);

//    void saveDatas(List<ArtStudentImportInfoDto> dtoList);

    ArtTeacherAuth getByCondition(Map<String, Object> params);

}
