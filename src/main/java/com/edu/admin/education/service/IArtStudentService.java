package com.edu.admin.education.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.edu.admin.education.model.ArtStudent;
import com.edu.admin.education.model.ArtStudentImportInfoDto;
import com.edu.admin.server.page.table.PageTableRequest;
import com.edu.admin.server.page.table.PageTableResponse;

import java.util.List;
import java.util.Map;

public interface IArtStudentService extends IService<ArtStudent> {

//    List<ArtStudent> list(Map<String, Object> params, Integer offset, Integer limit);

//    int count(Map<String, Object> params);

    int deleteLogic(ArtStudent artStudent);

    void deleteLogicBatch(List<String> ids);

    boolean save(ArtStudent artStudent);

    ArtStudent getById(Long id);

    void update(ArtStudent artStudent);

    void saveDatas(List<ArtStudentImportInfoDto> dtoList);

    ArtStudent getByCondition(Map<String, Object> params);

    PageTableResponse queryList(PageTableRequest request);

    List<ArtStudent> list(PageTableRequest request);

}
