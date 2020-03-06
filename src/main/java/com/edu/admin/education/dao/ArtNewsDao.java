package com.edu.admin.education.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.edu.admin.education.model.ArtNews;

import java.util.List;
import java.util.Map;

public interface ArtNewsDao extends BaseMapper<ArtNews> {

    List<ArtNews> selectByCustomSql(Map<String, Object> params);

    int countByCustomSql(Map<String, Object> params);

}