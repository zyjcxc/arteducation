package com.edu.admin.education.dao;

import com.edu.admin.education.model.ArtFileList;
import com.edu.admin.mymapper.MyMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ArtFileListDao extends MyMapper<ArtFileList> {

    List<ArtFileList> selectByCustomSql(Map<String, Object> params);

    int countByCustomSql(Map<String, Object> params);
}