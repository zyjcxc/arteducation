package com.edu.admin.education.service.impl;
import com.edu.admin.education.command.ArtHomeSchoolSaveCommand;
import com.edu.admin.education.command.ArtHomeSchoolUpdateCommand;
import com.edu.admin.education.dto.ArtHomeSchoolDto;
import com.edu.admin.education.service.IArtHomeSchoolService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

//
//import com.edu.admin.education.command.ArtHomeSchoolSaveCommand;
//import com.edu.admin.education.command.ArtHomeSchoolUpdateCommand;
//import com.edu.admin.education.convert.ArtHomeSchoolConverter;
//import com.edu.admin.education.dao.ArtHomeSchoolDao;
//import com.edu.admin.education.dto.ArtHomeSchoolDto;
//import com.edu.admin.education.enums.ResultEnum;
//import com.edu.admin.education.exception.HumanResourceException;
//import com.edu.admin.education.model.ArtHomeSchool;
//import com.edu.admin.education.service.IArtHomeSchoolService;
//import com.edu.admin.server.utils.BeanUtil;
//import com.github.pagehelper.PageHelper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import tk.mybatis.mapper.entity.Example;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
@Service
@Transactional
public class ArtHomeSchoolServiceImpl implements IArtHomeSchoolService {
    @Override
    public ArtHomeSchoolDto getById(Long id) {
        return null;
    }

    @Override
    public ArtHomeSchoolDto save(ArtHomeSchoolSaveCommand command) {
        return null;
    }

    @Override
    public ArtHomeSchoolDto update(ArtHomeSchoolUpdateCommand command) {
        return null;
    }

    @Override
    public List<ArtHomeSchoolDto> list(Map<String, Object> params, Integer offset, Integer limit) {
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
    public List<ArtHomeSchoolDto> findAll() {
        return null;
    }

    @Override
    public List<ArtHomeSchoolDto> findRecommendList() {
        return null;
    }
//
//    @Autowired
//    private ArtHomeSchoolDao artHomeSchoolDao;
//
//    @Override
//    public ArtHomeSchoolDto getById(Long id) {
//        return ArtHomeSchoolConverter.convertToArtHomeSchoolDto(artHomeSchoolDao.selectByPrimaryKey(id));
//    }
//
//    @Override
//    public List<ArtHomeSchoolDto> findRecommendList() {
//        Map<String, Object> params = new HashMap<>();
//
//        params.put("recommend", 1);
//        params.put("orderBy", "order by sort asc");
//
////        Example example = getQueryExample(params);
//
//        List<ArtHomeSchool> list = artHomeSchoolDao.selectByCustomSql(params);
//
//        return ArtHomeSchoolConverter.convertToListArtHomeSchoolDto(list);
//    }
//
//    @Override
//    public ArtHomeSchoolDto save(ArtHomeSchoolSaveCommand command) {
//        ArtHomeSchool artHomeSchool = ArtHomeSchoolConverter.convertToArtHomeSchool(command);
//        artHomeSchoolDao.insertSelective(artHomeSchool);
//        return ArtHomeSchoolConverter.convertToArtHomeSchoolDto(artHomeSchool);
//    }
//
//    @Override
//    public ArtHomeSchoolDto update(ArtHomeSchoolUpdateCommand command) {
//        ArtHomeSchool oldData = artHomeSchoolDao.selectByPrimaryKey(command.getId());
//        if (oldData == null) {
//            throw new HumanResourceException(ResultEnum.NO_FIND_DATA);
//        }
//        oldData = ArtHomeSchoolConverter.convertToArtHomeSchool(command);
//        oldData.setId(command.getId());
//        artHomeSchoolDao.updateByPrimaryKeySelective(oldData);
//        return ArtHomeSchoolConverter.convertToArtHomeSchoolDto(oldData);
//    }
//
//    @Override
//    public List<ArtHomeSchoolDto> list(Map<String, Object> params, Integer offset, Integer limit) {
//        PageHelper.offsetPage(offset, limit);
//
////        Example example = getQueryExample(params);
//
//        List<ArtHomeSchool> list = artHomeSchoolDao.selectByCustomSql(params);
//
//        return ArtHomeSchoolConverter.convertToListArtHomeSchoolDto(list);
//    }
//
//    @Override
//    public int count(Map<String, Object> params) {
//        // 直等查询
////        ArtHomeSchool queryObject = BeanUtil.getQueryObject(params, ArtHomeSchool.class);
////        queryObject.setOrderBy(null);
//        return artHomeSchoolDao.countByCustomSql(params);
//    }
//
//    @Override
//    public int delete(Long id) {
//        return artHomeSchoolDao.deleteByPrimaryKey(id);
//    }
//
//    @Override
//    public List<ArtHomeSchoolDto> findAll() {
//        List<ArtHomeSchool> ArtHomeSchool = artHomeSchoolDao.selectAll();
//        return ArtHomeSchoolConverter.convertToListArtHomeSchoolDto(ArtHomeSchool);
//    }
//
//    /**
//     * 单表QBC查询
//     * @param params 查询参数
//     */
//    private Example getQueryExample(Map<String, Object> params) {
//        // criteria.andEqualTo("title", params.get("title"));
//        // criteria.andEqualTo("status", params.get("status"));
//        // 直等查询
//        ArtHomeSchool queryObject = BeanUtil.getQueryObject(params, ArtHomeSchool.class);
//        if (params.get("orderBy") != null) {
//            queryObject.setOrderBy((String)params.get("orderBy"));
//        }
//        Example example = new Example(ArtHomeSchool.class);
//        Example.Criteria criteria = example.createCriteria();
//        if (params.get("orderBy") != null) {
//            String orderBy = (String) params.get("orderBy");
//            example.setOrderByClause(orderBy.replace("order by", ""));
//        }
//
//        if (params.containsKey("recommend")) {
//            criteria.andEqualTo("recommend", params.get("recommend"));
//        }
//
//        if (params.get("orderBy") != null) {
//            String orderBy = (String) params.get("orderBy");
//            example.setOrderByClause(orderBy.replace("order by", ""));
//        }
//
//        return example;
//    }
}
