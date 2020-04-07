package com.edu.admin.education.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.edu.admin.education.model.ArtStudent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 特长生 模块数据层接口
 * @author mengqa
 * @date 2018-04-03
 */
@Mapper
public interface ArtStudentMapper extends BaseMapper<ArtStudent> {

    /*SELECT
    artStudentObj.*,
        <include refid="joinColumns"/>
    FROM
    art_student artStudentObj
        <include refid="joins"/>
        <include refid="joinWhere"/>
    ${orderBy}*/
    @Select("select artStudentObj.*, classificationObj.NAME classificationName," +
            "        activityObj.name activityName," +
            "        schoolObj.name schoolName " +
            "FROM art_student artStudentObj " +
            "LEFT JOIN live_course_classification classificationObj On artStudentObj.classification_id = classificationObj.id " +
            "LEFT JOIN art_activity activityObj ON activityObj.id = artStudentObj.activity_id " +
            "LEFT JOIN art_school schoolObj ON schoolObj.id = artStudentObj.school_id " +
            " ${ew.customSqlSegment} ")
    IPage<ArtStudent> selectByCustomSql(IPage<ArtStudent> page, @Param(Constants.WRAPPER)Wrapper<ArtStudent> wrapper);

    @Select("select artStudentObj.*, classificationObj.NAME classificationName," +
            "        activityObj.name activityName," +
            "        schoolObj.name schoolName " +
            "FROM art_student artStudentObj " +
            "LEFT JOIN live_course_classification classificationObj On artStudentObj.classification_id = classificationObj.id " +
            "LEFT JOIN art_activity activityObj ON activityObj.id = artStudentObj.activity_id " +
            "LEFT JOIN art_school schoolObj ON schoolObj.id = artStudentObj.school_id " +
            " ${ew.customSqlSegment} ")
    List<ArtStudent> selectByCustomSql(@Param(Constants.WRAPPER)Wrapper<ArtStudent> wrapper);

//    List<ArtStudent> selectByCustomSql(Map<String, Object> params);
//
//    int countByCustomSql(Map<String, Object> params);

}
