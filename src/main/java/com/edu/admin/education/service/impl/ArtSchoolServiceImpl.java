package com.edu.admin.education.service.impl;

import com.edu.admin.education.model.ArtSchool;
import com.edu.admin.education.service.IArtSchoolService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

//
//import com.edu.admin.education.dao.ArtSchoolDao;
//import com.edu.admin.education.enums.PublicState;
//import com.edu.admin.education.model.ArtActivity;
//import com.edu.admin.education.model.ArtSchool;
//import com.edu.admin.education.model.LiveCourseClassification;
//import com.edu.admin.education.service.IArtSchoolService;
//import com.github.pagehelper.PageHelper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.util.CollectionUtils;
//import tk.mybatis.mapper.entity.Example;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//;
//
///**
// * 模块业务接口具体实现
// * @author mengqa
// * @date 2018-04-05
// **/
@Service
public class ArtSchoolServiceImpl implements IArtSchoolService {
    @Override
    public ArtSchool getById(Long id) {
        return null;
    }

    @Override
    public int save(ArtSchool liveCourseClassification) {
        return 0;
    }

    @Override
    public int update(ArtSchool liveCourseClassification) {
        return 0;
    }

    @Override
    public List<ArtSchool> list(Map<String, Object> params, Integer offset, Integer limit) {
        return null;
    }

    @Override
    public int count(Map<String, Object> params) {
        return 0;
    }

    @Override
    public int delete(Long id) {
        return 0;
    }

    @Override
    public List<ArtSchool> findAll() {
        return null;
    }

    @Override
    public ArtSchool getByName(String name) {
        return null;
    }
//
//    @Autowired
//    private ArtSchoolDao artSchoolDao;
//
//    @Override
//    public ArtSchool getById(Long id) {
//        return artSchoolDao.selectByPrimaryKey(id);
//    }
//
//    @Override
//    public int save(ArtSchool artSchool) {
////        ArtSchool oldData = getByName(artSchool.getName());
////        if (oldData != null) {
////            return 0;
////        }
//        return artSchoolDao.insertSelective(artSchool);
//    }
//
//    @Override
//    public int update(ArtSchool artSchool) {
//        return artSchoolDao.updateByPrimaryKeySelective(artSchool);
//    }
//
//    @Override
//    public List<ArtSchool> list(Map<String, Object> params, Integer offset, Integer limit) {
//        PageHelper.offsetPage(offset, limit);
//       /* // 直等查询
//        LiveCourseClassification queryObject = BeanUtil.getQueryObject(params, LiveCourseClassification.class);
//        if (params.get("orderBy") != null) {
//            queryObject.setOrderBy((String)params.get("orderBy"));
//        }
//        List<LiveCourseClassification> list = liveCourseClassificationDao.selectByCustom(queryObject);*/
//        // QBC查询
//        Example example = getQueryExample(params);
//        List<ArtSchool> list = artSchoolDao.selectByExample(example);
//        return list;
//    }
//
//    @Override
//    public int count(Map<String, Object> params) {
//        // 直等查询
//        /*LiveCourseClassification queryObject = BeanUtil.getQueryObject(params, LiveCourseClassification.class);
//        queryObject.setOrderBy(null);
//        int count = liveCourseClassificationDao.selectCount(queryObject);*/
//        // QBC查询
//        Example example = getQueryExample(params);
//        int count = artSchoolDao.selectCountByExample(example);
//
//        return count;
//    }
//
//    @Override
//    public int delete(Long id) {
//        return artSchoolDao.deleteByPrimaryKey(id);
//    }
//
//    @Override
//    public List<ArtSchool> findAll() {
//        Example example = getQueryExample(new HashMap<>(1));
//        return artSchoolDao.selectByExample(example);
//    }
//
//    @Override
//    public ArtSchool getByName(String name) {
//        Example example = new Example(ArtActivity.class);
//        Example.Criteria criteria = example.createCriteria();
//        criteria.andEqualTo("name", name);
//        List<ArtSchool> list = artSchoolDao.selectByExample(example);
//        if (CollectionUtils.isEmpty(list)) {
//            return null;
//        }
//        return list.get(0);
//    }
//
//    /**
//     * 单表QBC查询
//     * @param params 查询参数
//     */
//    private Example getQueryExample(Map<String, Object> params) {
//        Example example = new Example(LiveCourseClassification.class);
//        Example.Criteria criteria = example.createCriteria();
//        criteria.andNotEqualTo("state", PublicState.DELETE.getCode());
//        criteria.andEqualTo("id", params.get("id"));
//        criteria.andEqualTo("name", params.get("name"));
//        if (params.get("orderBy") != null) {
//            String orderBy = (String) params.get("orderBy");
//            example.setOrderByClause(orderBy.replace("order by", ""));
//        }
//        return example;
//    }
}
