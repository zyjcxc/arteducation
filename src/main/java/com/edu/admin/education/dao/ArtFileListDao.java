package com.edu.admin.education.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.edu.admin.education.model.ArtFileList;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ArtFileListDao extends BaseMapper<ArtFileList> {

    List<ArtFileList> selectByCustomSql(Map<String, Object> params);

    int countByCustomSql(Map<String, Object> params);
}