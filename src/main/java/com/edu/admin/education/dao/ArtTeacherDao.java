package com.edu.admin.education.dao;

import com.edu.admin.education.model.ArtTeacher;
import com.edu.admin.mymapper.MyMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ArtTeacherDao extends MyMapper<ArtTeacher> {

    List<ArtTeacher> selectByCustomSql(Map<String, Object> params);

    int countByCustomSql(Map<String, Object> params);
}