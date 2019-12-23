package com.edu.admin.education.dao;

import com.edu.admin.education.model.ArtHomeStudent;
import com.edu.admin.mymapper.MyMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ArtHomeStudentDao extends MyMapper<ArtHomeStudent> {

    List<ArtHomeStudent> selectByCustomSql(Map<String, Object> params);

    int countByCustomSql(Map<String, Object> params);
}