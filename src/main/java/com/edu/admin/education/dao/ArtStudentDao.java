package com.edu.admin.education.dao;

import com.edu.admin.education.model.ArtStudent;
import com.edu.admin.mymapper.MyMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 特长生 模块数据层接口
 * @author mengqa
 * @date 2018-04-03
 */
@Mapper
public interface ArtStudentDao extends MyMapper<ArtStudent> {

    List<ArtStudent> selectByCustomSql(Map<String, Object> params);

    int countByCustomSql(Map<String, Object> params);

}
