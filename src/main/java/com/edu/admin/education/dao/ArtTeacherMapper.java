package com.edu.admin.education.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.edu.admin.education.model.ArtTeacher;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArtTeacherMapper extends BaseMapper<ArtTeacher> {

//    List<ArtTeacher> selectByCustomSql(Map<String, Object> params);
//
//    int countByCustomSql(Map<String, Object> params);
}