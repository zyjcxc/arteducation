package com.edu.admin.education.service.impl;

import com.edu.admin.education.dao.LiveCourseClassificationDao;
import com.edu.admin.education.enums.PublicState;
import com.edu.admin.education.model.LiveCourseClassification;
import com.edu.admin.education.service.ILiveCourseClassificationService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

;

/**
 * 课程分类 模块业务接口具体实现
 * @author mengqa
 * @date 2018-04-05
 **/
@Service
public class LiveCourseClassificationServiceImpl implements ILiveCourseClassificationService {

    @Autowired
    private LiveCourseClassificationDao liveCourseClassificationDao;

    @Override
    public LiveCourseClassification getById(Long id) {
        return liveCourseClassificationDao.selectByPrimaryKey(id);
    }

    @Override
    public int save(LiveCourseClassification liveCourseClassification) {
        return liveCourseClassificationDao.insertSelective(liveCourseClassification);
    }

    @Override
    public int update(LiveCourseClassification liveCourseClassification) {
        return liveCourseClassificationDao.updateByPrimaryKeySelective(liveCourseClassification);
    }

    @Override
    public List<LiveCourseClassification> list(Map<String, Object> params, Integer offset, Integer limit) {
        PageHelper.offsetPage(offset, limit);
       /* // 直等查询
        LiveCourseClassification queryObject = BeanUtil.getQueryObject(params, LiveCourseClassification.class);
        if (params.get("orderBy") != null) {
            queryObject.setOrderBy((String)params.get("orderBy"));
        }
        List<LiveCourseClassification> list = liveCourseClassificationDao.selectByCustom(queryObject);*/
        // QBC查询
        Example example = getQueryExample(params);
        List<LiveCourseClassification> list = liveCourseClassificationDao.selectByExample(example);
        return list;
    }

    @Override
    public int count(Map<String, Object> params) {
        // 直等查询
        /*LiveCourseClassification queryObject = BeanUtil.getQueryObject(params, LiveCourseClassification.class);
        queryObject.setOrderBy(null);
        int count = liveCourseClassificationDao.selectCount(queryObject);*/
        // QBC查询
        Example example = getQueryExample(params);
        int count = liveCourseClassificationDao.selectCountByExample(example);

        return count;
    }

    @Override
    public int delete(Long id) {
        return liveCourseClassificationDao.deleteByPrimaryKey(id);
    }

    @Override
    public List<LiveCourseClassification> findAll() {
        Example example = getQueryExample(new HashMap<>(1));
        return liveCourseClassificationDao.selectByExample(example);
    }

    /**
     * 单表QBC查询
     * @param params 查询参数
     */
    private Example getQueryExample(Map<String, Object> params) {
        Example example = new Example(LiveCourseClassification.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andNotEqualTo("state", PublicState.DELETE.getCode());
        criteria.andEqualTo("id", params.get("id"));
        criteria.andEqualTo("name", params.get("name"));
        if (params.get("orderBy") != null) {
            String orderBy = (String) params.get("orderBy");
            example.setOrderByClause(orderBy.replace("order by", ""));
        }
        return example;
    }
}
