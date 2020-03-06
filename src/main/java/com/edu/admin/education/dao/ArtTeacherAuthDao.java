package com.edu.admin.education.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.edu.admin.education.model.ArtTeacherAuth;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ArtTeacherAuthDao extends BaseMapper<ArtTeacherAuth> {

    List<ArtTeacherAuth> selectByCustomSql(Map<String, Object> params);

    int countByCustomSql(Map<String, Object> params);
}