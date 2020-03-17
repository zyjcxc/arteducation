package com.edu.admin.education.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edu.admin.education.dao.ArtSchoolMapper;
import com.edu.admin.education.model.ArtSchool;
import com.edu.admin.education.service.IArtSchoolService;
import com.edu.admin.server.page.table.OrderByObject;
import com.edu.admin.server.page.table.PageTableRequest;
import com.edu.admin.server.page.table.PageTableResponse;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ArtSchoolServiceImpl extends ServiceImpl<ArtSchoolMapper, ArtSchool> implements IArtSchoolService {

    @Autowired
    private ArtSchoolMapper artSchoolMapper;

    @Override
    public ArtSchool getById(Long id) {
        return artSchoolMapper.selectById(id);
    }

    @Override
    public boolean save(ArtSchool artSchool) {
        ArtSchool oldData = getByName(artSchool.getName());
        if (oldData != null) {
            return false;
        }
        artSchoolMapper.insert(artSchool);
        return true;
    }

    @Override
    public int update(ArtSchool artSchool) {
        artSchoolMapper.updateById(artSchool);
        return 0;
    }

    @Override
    public int delete(Long id) {
        return artSchoolMapper.deleteById(id);
    }

    @Override
    public List<ArtSchool> findAll() {
        return artSchoolMapper.selectList(null);
    }

    @Override
    public ArtSchool getByName(String name) {
        return artSchoolMapper.selectOne(Wrappers.<ArtSchool>lambdaQuery().eq(ArtSchool::getName, name));
    }


    @Override
    public PageTableResponse queryList(PageTableRequest request) {
        Page<ArtSchool> page = new Page<>(request.getCurrentPage(),request.getLimit());
        Page<ArtSchool> result = artSchoolMapper.selectPage(page, makeQueryConditionWrapper(request));
//        List<ArtSchoolDto> artSchoolDtos = ArtSchoolConverter.convertToListArtSchoolDto(result.getRecords());
        return new PageTableResponse((int)result.getTotal(), (int)result.getTotal(), result.getRecords());
    }

    private QueryWrapper<ArtSchool> makeQueryConditionWrapper(PageTableRequest request) {
        OrderByObject orderByObject = request.getOrderByObject();
        QueryWrapper<ArtSchool> query = Wrappers.query();
        Map<String, Object> params = request.getParams();
        query.eq(params.containsKey(ArtSchool.Column.NAME.key()),
                ArtSchool.Column.NAME.key(),
                params.get(ArtSchool.Column.NAME.key()));
        query.eq(params.containsKey(ArtSchool.Column.ID.key()),
                ArtSchool.Column.ID.key(),
                params.get(ArtSchool.Column.ID.key()));
        if (orderByObject != null) {
            query.orderBy(orderByObject.isOrderBy(), orderByObject.isAsc(), orderByObject.getColumn(true));
        } else {
            query.orderBy(true, true, ArtSchool.Column.ID.key());
        }
        return query;
    }

}
