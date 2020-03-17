package com.edu.admin.education.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edu.admin.education.dao.LiveCourseClassificationMapper;
import com.edu.admin.education.enums.ResultEnum;
import com.edu.admin.education.exception.HumanResourceException;
import com.edu.admin.education.model.LiveCourseClassification;
import com.edu.admin.education.service.ILiveCourseClassificationService;
import com.edu.admin.server.page.table.OrderByObject;
import com.edu.admin.server.page.table.PageTableRequest;
import com.edu.admin.server.page.table.PageTableResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

//
//import com.edu.admin.education.dao.LiveCourseClassificationDao;
//import com.edu.admin.education.enums.PublicState;
//import com.edu.admin.education.enums.ResultEnum;
//import com.edu.admin.education.exception.HumanResourceException;
//import com.edu.admin.education.model.ArtActivity;
//import com.edu.admin.education.model.LiveCourseClassification;
//import com.edu.admin.education.service.ILiveCourseClassificationService;
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
// * 课程分类 模块业务接口具体实现
// * @author mengqa
// * @date 2018-04-05
// **/
@Service
public class LiveCourseClassificationServiceImpl extends ServiceImpl<LiveCourseClassificationMapper,  LiveCourseClassification> implements ILiveCourseClassificationService {
    
    @Autowired
    private LiveCourseClassificationMapper liveCourseClassificationMapper;
    
    @Override
    public LiveCourseClassification getById(Long id) {
        return liveCourseClassificationMapper.selectById(id);
    }

    @Override
    public boolean save(LiveCourseClassification liveCourseClassification) {
        LiveCourseClassification oldData = getByName(liveCourseClassification.getName());
        if (oldData != null) {
            throw new HumanResourceException(ResultEnum.REPEAT_RECORD);
        }
        liveCourseClassificationMapper.insert(liveCourseClassification);
        return true;
    }

    @Override
    public int update(LiveCourseClassification liveCourseClassification) {
        liveCourseClassificationMapper.updateById(liveCourseClassification);
        return 0;
    }
    
    @Override
    public int delete(Long id) {
        liveCourseClassificationMapper.deleteById(id);
        return 0;
    }

    @Override
    public List<LiveCourseClassification> findAll() {
        return liveCourseClassificationMapper.selectList(null);
    }

    @Override
    public LiveCourseClassification getByName(String name) {
        return liveCourseClassificationMapper.selectOne(Wrappers.<LiveCourseClassification>lambdaQuery().eq(LiveCourseClassification::getName, name));
    }

    @Override
    public PageTableResponse queryList(PageTableRequest request) {
        Page<LiveCourseClassification> page = new Page<>(request.getCurrentPage(),request.getLimit());
        Page<LiveCourseClassification> result = liveCourseClassificationMapper.selectPage(page, makeQueryConditionWrapper(request));
        return new PageTableResponse((int)result.getTotal(), (int)result.getTotal(), result.getRecords());
    }

    private QueryWrapper<LiveCourseClassification> makeQueryConditionWrapper(PageTableRequest request) {
        OrderByObject orderByObject = request.getOrderByObject();
        QueryWrapper<LiveCourseClassification> query = Wrappers.query();
        Map<String, Object> params = request.getParams();
        query.eq(params.containsKey(LiveCourseClassification.Column.NAME.key()),
                LiveCourseClassification.Column.NAME.key(),
                params.get(LiveCourseClassification.Column.NAME.key()));
        query.eq(params.containsKey(LiveCourseClassification.Column.ID.key()),
                LiveCourseClassification.Column.ID.key(),
                params.get(LiveCourseClassification.Column.ID.key()));
        if (orderByObject != null) {
            query.orderBy(orderByObject.isOrderBy(), orderByObject.isAsc(), orderByObject.getColumn(true));
        } else {
            query.orderBy(true, true, LiveCourseClassification.Column.ID.key());
        }
        return query;
    }
    
//
//    @Override
//    public LiveCourseClassification getById(Long id) {
//        return liveCourseClassificationDao.selectByPrimaryKey(id);
//    }
//
//    @Override
//    public int save(LiveCourseClassification liveCourseClassification) {
//        LiveCourseClassification oldData = getByName(liveCourseClassification.getName());
//        if (oldData != null) {
//            throw new HumanResourceException(ResultEnum.REPEAT_RECORD);
//        }
//        return liveCourseClassificationDao.insertSelective(liveCourseClassification);
//    }
//
//    @Override
//    public int update(LiveCourseClassification liveCourseClassification) {
//        return liveCourseClassificationDao.updateByPrimaryKeySelective(liveCourseClassification);
//    }
//
//    @Override
//    public List<LiveCourseClassification> list(Map<String, Object> params, Integer offset, Integer limit) {
//        PageHelper.offsetPage(offset, limit);
//       /* // 直等查询
//        LiveCourseClassification queryObject = BeanUtil.getQueryObject(params, LiveCourseClassification.class);
//        if (params.get("orderBy") != null) {
//            queryObject.setOrderBy((String)params.get("orderBy"));
//        }
//        List<LiveCourseClassification> list = liveCourseClassificationDao.selectByCustom(queryObject);*/
//        // QBC查询
//        Example example = getQueryExample(params);
//        List<LiveCourseClassification> list = liveCourseClassificationDao.selectByExample(example);
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
//        int count = liveCourseClassificationDao.selectCountByExample(example);
//
//        return count;
//    }
//
//    @Override
//    public int delete(Long id) {
//        return liveCourseClassificationDao.deleteByPrimaryKey(id);
//    }
//
//    @Override
//    public List<LiveCourseClassification> findAll() {
//        Example example = getQueryExample(new HashMap<>(1));
//        return liveCourseClassificationDao.selectByExample(example);
//    }
//
//    @Override
//    public LiveCourseClassification getByName(String name) {
//        Example example = new Example(ArtActivity.class);
//        Example.Criteria criteria = example.createCriteria();
//        criteria.andEqualTo("name", name);
//        List<LiveCourseClassification> list = liveCourseClassificationDao.selectByExample(example);
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
