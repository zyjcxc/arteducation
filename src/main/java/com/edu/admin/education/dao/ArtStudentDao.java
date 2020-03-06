package com.edu.admin.education.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.edu.admin.education.model.ArtStudent;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 特长生 模块数据层接口
 * @author mengqa
 * @date 2018-04-03
 */
@Mapper
public interface ArtStudentDao extends BaseMapper<ArtStudent> {

    List<ArtStudent> selectByCustomSql(Map<String, Object> params);

    int countByCustomSql(Map<String, Object> params);

}
