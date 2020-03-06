package com.edu.admin.education.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.edu.admin.education.model.ArtTeacher;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ArtTeacherDao extends BaseMapper<ArtTeacher> {

    List<ArtTeacher> selectByCustomSql(Map<String, Object> params);

    int countByCustomSql(Map<String, Object> params);
}