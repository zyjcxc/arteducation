package com.edu.admin.education.dao;

import com.edu.admin.education.model.ArtNews;
import com.edu.admin.mymapper.MyMapper;

import java.util.List;
import java.util.Map;

public interface ArtNewsDao extends MyMapper<ArtNews> {

    List<ArtNews> selectByCustomSql(Map<String, Object> params);

    int countByCustomSql(Map<String, Object> params);

}