package com.edu.admin.education.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edu.admin.education.command.ArtTextbookTypeSaveCommand;
import com.edu.admin.education.command.ArtTextbookTypeUpdateCommand;
import com.edu.admin.education.convert.ArtTextbookTypeConverter;
import com.edu.admin.education.dao.ArtTextbookTypeMapper;
import com.edu.admin.education.dto.ArtTextbookTypeDto;
import com.edu.admin.education.enums.PublicState;
import com.edu.admin.education.enums.ResultEnum;
import com.edu.admin.education.exception.HumanResourceException;
import com.edu.admin.education.model.ArtTextbookType;
import com.edu.admin.education.service.IArtTextbookTypeService;
import com.edu.admin.server.page.table.OrderByObject;
import com.edu.admin.server.page.table.PageTableRequest;
import com.edu.admin.server.page.table.PageTableResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

//
//import com.edu.admin.education.command.ArtTextbookTypeSaveCommand;
//import com.edu.admin.education.command.ArtTextbookTypeUpdateCommand;
//import com.edu.admin.education.convert.ArtTextbookTypeConverter;
//import com.edu.admin.education.dao.ArtTextbookTypeDao;
//import com.edu.admin.education.dto.ArtTextbookTypeDto;
//import com.edu.admin.education.enums.ResultEnum;
//import com.edu.admin.education.exception.HumanResourceException;
//import com.edu.admin.education.model.ArtTextbookType;
//import com.edu.admin.education.service.IArtTextbookTypeService;
//import com.edu.admin.server.utils.BeanUtil;
//import com.github.pagehelper.PageHelper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import tk.mybatis.mapper.entity.Example;
//
//import java.util.List;
//import java.util.Map;
//
@Service
@Transactional
public class ArtTextbookTypeServiceImpl extends ServiceImpl<ArtTextbookTypeMapper, ArtTextbookType> implements IArtTextbookTypeService {

    @Autowired
    private ArtTextbookTypeMapper artTextbookTypeMapper;
    
    @Override
    public ArtTextbookTypeDto getById(Long id) {
        return ArtTextbookTypeConverter.convertToArtTextbookTypeDto(artTextbookTypeMapper.selectById(id));
    }

    @Override
    public ArtTextbookTypeDto save(ArtTextbookTypeSaveCommand command) {
        ArtTextbookType artTextbookType = ArtTextbookTypeConverter.convertToArtTextbookType(command);
        artTextbookType.setState(PublicState.NORMAL.getDataBase());
//        artTextbookType.setCreateUserId(UserUtil.getCurrentUser().getId());
        artTextbookTypeMapper.insert(artTextbookType);
        return ArtTextbookTypeConverter.convertToArtTextbookTypeDto(artTextbookType);
    }

    @Override
    public ArtTextbookTypeDto update(ArtTextbookTypeUpdateCommand command) {
        ArtTextbookType oldData = artTextbookTypeMapper.selectById(command.getId());
        if (oldData == null) {
            throw new HumanResourceException(ResultEnum.NO_FIND_DATA);
        }
        oldData = ArtTextbookTypeConverter.convertToArtTextbookType(command);
        oldData.setId(command.getId());
        artTextbookTypeMapper.updateById(oldData);
        return ArtTextbookTypeConverter.convertToArtTextbookTypeDto(oldData);
    }

    @Override
    public int delete(Long id) {
        return artTextbookTypeMapper.deleteById(id);
    }

    @Override
    public List<ArtTextbookTypeDto> findAll() {
        return ArtTextbookTypeConverter.convertToListArtTextbookTypeDto(artTextbookTypeMapper.selectList(null));
    }

    @Override
    public PageTableResponse queryList(PageTableRequest request) {
        Page<ArtTextbookType> page = new Page<>(request.getCurrentPage(),request.getLimit());
        Page<ArtTextbookType> result = artTextbookTypeMapper.selectPage(page, makeQueryConditionWrapper(request));
        List<ArtTextbookTypeDto> dtos = ArtTextbookTypeConverter.convertToListArtTextbookTypeDto(result.getRecords());
        return new PageTableResponse((int)result.getTotal(), (int)result.getTotal(), dtos);
    }
    
    private QueryWrapper<ArtTextbookType> makeQueryConditionWrapper(PageTableRequest request) {
        OrderByObject orderByObject = request.getOrderByObject();
        QueryWrapper<ArtTextbookType> query = Wrappers.query();
        Map<String, Object> params = request.getParams();
        query.eq(params.containsKey(ArtTextbookType.Column.NAME.key()),
                ArtTextbookType.Column.NAME.key(),
                params.get(ArtTextbookType.Column.NAME.key()));
        query.eq(params.containsKey(ArtTextbookType.Column.ID.key()),
                ArtTextbookType.Column.ID.key(),
                params.get(ArtTextbookType.Column.ID.key()));
        if (orderByObject != null) {
            query.orderBy(orderByObject.isOrderBy(), orderByObject.isAsc(), orderByObject.getColumn(true));
        } else {
            query.orderBy(true, true, ArtTextbookType.Column.ID.key());
        }
        return query;
    }


//    @Override
//    public ArtTextbookTypeDto save(ArtTextbookTypeSaveCommand command) {
//        ArtTextbookType artTextbookType = ArtTextbookTypeConverter.convertToArtTextbookType(command);
//        artTextbookType.setState("1");
//        artTextbookTypeDao.insertSelective(artTextbookType);
//        return ArtTextbookTypeConverter.convertToArtTextbookTypeDto(artTextbookType);
//    }
//
//    @Override
//    public ArtTextbookTypeDto update(ArtTextbookTypeUpdateCommand command) {
//        ArtTextbookType oldData = artTextbookTypeDao.selectByPrimaryKey(command.getId());
//        if (oldData == null) {
//            throw new HumanResourceException(ResultEnum.NO_FIND_DATA);
//        }
//        oldData = ArtTextbookTypeConverter.convertToArtTextbookType(command);
//        oldData.setId(command.getId());
//        artTextbookTypeDao.updateByPrimaryKeySelective(oldData);
//        return ArtTextbookTypeConverter.convertToArtTextbookTypeDto(oldData);
//    }
//
//    @Override
//    public List<ArtTextbookTypeDto> list(Map<String, Object> params, Integer offset, Integer limit) {
//        PageHelper.offsetPage(offset, limit);
//
//        Example example = getQueryExample(params);
//
//        List<ArtTextbookType> list = artTextbookTypeDao.selectByExample(example);
//
//        return ArtTextbookTypeConverter.convertToListArtTextbookTypeDto(list);
//    }
//
//    @Override
//    public int count(Map<String, Object> params) {
//        // 直等查询
//        ArtTextbookType queryObject = BeanUtil.getQueryObject(params, ArtTextbookType.class);
//        queryObject.setOrderBy(null);
//        return artTextbookTypeDao.selectCount(queryObject);
//    }
//
//    @Override
//    public int delete(Long id) {
//        return artTextbookTypeDao.deleteByPrimaryKey(id);
//    }
//
//    @Override
//    public List<ArtTextbookTypeDto> findAll() {
//        List<ArtTextbookType> ArtTextbookType = artTextbookTypeDao.selectAll();
//        return ArtTextbookTypeConverter.convertToListArtTextbookTypeDto(ArtTextbookType);
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
//        ArtTextbookType queryObject = BeanUtil.getQueryObject(params, ArtTextbookType.class);
//        if (params.get("orderBy") != null) {
//            queryObject.setOrderBy((String)params.get("orderBy"));
//        }
//        Example example = new Example(ArtTextbookType.class);
//        Example.Criteria criteria = example.createCriteria();
//        if (params.get("orderBy") != null) {
//            String orderBy = (String) params.get("orderBy");
//            example.setOrderByClause(orderBy.replace("order by", ""));
//        }
//
//        criteria.andEqualTo("state", 1);
//        /*if (params.containsKey("name")) {
//            criteria.andEqualTo("name", params.get("name"));
//        }*/
//
//        return example;
//    }
//
}
