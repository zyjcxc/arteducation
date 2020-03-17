package com.edu.admin.education.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edu.admin.education.command.ArtTextbookSaveCommand;
import com.edu.admin.education.command.ArtTextbookUpdateCommand;
import com.edu.admin.education.convert.ArtTextbookConverter;
import com.edu.admin.education.dao.ArtTextbookMapper;
import com.edu.admin.education.dto.ArtTextbookDto;
import com.edu.admin.education.dto.ArtTextbookTypeDto;
import com.edu.admin.education.enums.PublicState;
import com.edu.admin.education.enums.ResultEnum;
import com.edu.admin.education.exception.HumanResourceException;
import com.edu.admin.education.model.ArtTextbook;
import com.edu.admin.education.model.ArtTextbookType;
import com.edu.admin.education.service.IArtTextbookService;
import com.edu.admin.education.service.IArtTextbookTypeService;
import com.edu.admin.server.page.table.OrderByObject;
import com.edu.admin.server.page.table.PageTableRequest;
import com.edu.admin.server.page.table.PageTableResponse;
import com.edu.admin.server.utils.StrUtil;
import com.edu.admin.server.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

//
//import com.edu.admin.education.command.ArtTextbookSaveCommand;
//import com.edu.admin.education.command.ArtTextbookUpdateCommand;
//import com.edu.admin.education.convert.ArtTextbookConverter;
//import com.edu.admin.education.dao.ArtTextbookDao;
//import com.edu.admin.education.dao.ArtTextbookTypeDao;
//import com.edu.admin.education.dto.ArtTextbookDto;
//import com.edu.admin.education.enums.ResultEnum;
//import com.edu.admin.education.exception.HumanResourceException;
//import com.edu.admin.education.model.ArtTextbook;
//import com.edu.admin.education.model.ArtTextbookType;
//import com.edu.admin.education.service.IArtTextbookService;
//import com.edu.admin.server.utils.BeanUtil;
//import com.edu.admin.server.utils.UserUtil;
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
public class ArtTextbookServiceImpl extends ServiceImpl<ArtTextbookMapper, ArtTextbook> implements IArtTextbookService {

    @Autowired
    private ArtTextbookMapper artTextbookMapper;

    @Autowired
    private IArtTextbookTypeService artTextbookTypeService;

    @Override
    public ArtTextbookDto getById(Long id) {
        ArtTextbook artTextbook = artTextbookMapper.selectById(id);
        if (artTextbook != null) {
            ArtTextbookTypeDto byId = artTextbookTypeService.getById(artTextbook.getTextbookTypeId());
            if (byId != null) {
                artTextbook.setTextBookName(byId.getName());
            }
            return ArtTextbookConverter.convertToArtTextbookDto(artTextbook);
        }
        return null;
    }

    @Override
    public ArtTextbookDto save(ArtTextbookSaveCommand command) {
        ArtTextbook artTextbook = ArtTextbookConverter.convertToArtTextbook(command);
        artTextbook.setState(PublicState.NORMAL.getDataBase());
        artTextbook.setCreateUserId(UserUtil.getCurrentUser().getId());
        artTextbookMapper.insert(artTextbook);
        return ArtTextbookConverter.convertToArtTextbookDto(artTextbook);
    }

    @Override
    public ArtTextbookDto update(ArtTextbookUpdateCommand command) {
        ArtTextbook oldData = artTextbookMapper.selectById(command.getId());
        if (oldData == null) {
            throw new HumanResourceException(ResultEnum.NO_FIND_DATA);
        }
        oldData = ArtTextbookConverter.convertToArtTextbook(command);
        oldData.setId(command.getId());
        artTextbookMapper.updateById(oldData);
        return ArtTextbookConverter.convertToArtTextbookDto(oldData);
    }

    @Override
    public int delete(Long id) {
        return artTextbookMapper.deleteById(id);
    }

    @Override
    public List<ArtTextbookDto> findAll() {
        return ArtTextbookConverter.convertToListArtTextbookDto(artTextbookMapper.selectList(null));
    }

    @Override
    public PageTableResponse queryList(PageTableRequest request) {
        Page<ArtTextbook> page = new Page<>(request.getCurrentPage(),request.getLimit());
        Page<ArtTextbook> result = artTextbookMapper.selectPage(page, makeQueryConditionWrapper(request));
        List<ArtTextbookDto> dtos = ArtTextbookConverter.convertToListArtTextbookDto(result.getRecords());
        dtos.forEach(d -> {
            ArtTextbookTypeDto byId = artTextbookTypeService.getById(d.getTextbookTypeId());
            if (byId != null) {
                d.setTextBookName(byId.getName());
            }
        });
        return new PageTableResponse((int)result.getTotal(), (int)result.getTotal(), dtos);
    }

    private QueryWrapper<ArtTextbook> makeQueryConditionWrapper(PageTableRequest request) {
        OrderByObject orderByObject = request.getOrderByObject();
        QueryWrapper<ArtTextbook> query = Wrappers.query();
        Map<String, Object> params = request.getParams();
        query.eq(params.containsKey(ArtTextbook.Column.TITLE.key()),
                ArtTextbook.Column.TITLE.key(),
                params.get(ArtTextbook.Column.TITLE.key()));
        query.eq(params.containsKey(ArtTextbook.Column.TEXTBOOKTYPEID.key()),
                StrUtil.humpToUnderline(ArtTextbook.Column.TEXTBOOKTYPEID.key()),
                params.get(ArtTextbook.Column.TEXTBOOKTYPEID.key()));
        query.eq(params.containsKey(ArtTextbook.Column.VERSION.key()),
                ArtTextbook.Column.VERSION.key(),
                params.get(ArtTextbook.Column.VERSION.key()));
        query.eq(params.containsKey(ArtTextbook.Column.AUTHOR.key()),
                ArtTextbook.Column.AUTHOR.key(),
                params.get(ArtTextbook.Column.AUTHOR.key()));
        query.eq(params.containsKey(ArtTextbookType.Column.ID.key()),
                ArtTextbook.Column.ID.key(),
                params.get(ArtTextbook.Column.ID.key()));
        if (orderByObject != null) {
            query.orderBy(orderByObject.isOrderBy(), orderByObject.isAsc(), orderByObject.getColumn(true));
        } else {
            query.orderBy(true, true, ArtTextbook.Column.ID.key());
        }
        return query;
    }

//
//    @Autowired
//    private ArtTextbookDao artTextbookDao;
//
//    @Autowired
//    private ArtTextbookTypeDao artTextbookTypeDao;
//
//    @Override
//    public ArtTextbookDto getById(Long id) {
//        ArtTextbook artTextbook = artTextbookDao.selectByPrimaryKey(id);
//        if (artTextbook != null) {
//            ArtTextbookType artTextbookType = artTextbookTypeDao.selectByPrimaryKey(artTextbook.getTextbookTypeId());
//            if (artTextbookType != null) {
//                artTextbook.setTextBookName(artTextbookType.getName());
//            }
//            return ArtTextbookConverter.convertToArtTextbookDto(artTextbook);
//        }
//        return null;
//    }
//
//    @Override
//    public ArtTextbookDto save(ArtTextbookSaveCommand command) {
//        ArtTextbook artTextbook = ArtTextbookConverter.convertToArtTextbook(command);
//        artTextbook.setState("1");
//        artTextbook.setCreateUserId(UserUtil.getCurrentUser().getId());
//        artTextbookDao.insertSelective(artTextbook);
//        return ArtTextbookConverter.convertToArtTextbookDto(artTextbook);
//    }
//
//    @Override
//    public ArtTextbookDto update(ArtTextbookUpdateCommand command) {
//        ArtTextbook oldData = artTextbookDao.selectByPrimaryKey(command.getId());
//        if (oldData == null) {
//            throw new HumanResourceException(ResultEnum.NO_FIND_DATA);
//        }
//        oldData = ArtTextbookConverter.convertToArtTextbook(command);
//        oldData.setId(command.getId());
//        artTextbookDao.updateByPrimaryKeySelective(oldData);
//        return ArtTextbookConverter.convertToArtTextbookDto(oldData);
//    }
//
//    @Override
//    public List<ArtTextbookDto> list(Map<String, Object> params, Integer offset, Integer limit) {
//        PageHelper.offsetPage(offset, limit);
//
//        Example example = getQueryExample(params);
//
//        List<ArtTextbook> list = artTextbookDao.selectByExample(example);
//        list.forEach(d -> {
//            ArtTextbookType artTextbookType = artTextbookTypeDao.selectByPrimaryKey(d.getTextbookTypeId());
//            if (artTextbookType != null) {
//                d.setTextBookName(artTextbookType.getName());
//            }
//        });
//
//
//        return ArtTextbookConverter.convertToListArtTextbookDto(list);
//    }
//
//    @Override
//    public int count(Map<String, Object> params) {
//        // 直等查询
//        ArtTextbook queryObject = BeanUtil.getQueryObject(params, ArtTextbook.class);
//        queryObject.setOrderBy(null);
//        return artTextbookDao.selectCount(queryObject);
//    }
//
//    @Override
//    public int delete(Long id) {
//        return artTextbookDao.deleteByPrimaryKey(id);
//    }
//
//    @Override
//    public List<ArtTextbookDto> findAll() {
//        List<ArtTextbook> ArtTextbook = artTextbookDao.selectAll();
//        return ArtTextbookConverter.convertToListArtTextbookDto(ArtTextbook);
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
//        ArtTextbook queryObject = BeanUtil.getQueryObject(params, ArtTextbook.class);
//        if (params.get("orderBy") != null) {
//            queryObject.setOrderBy((String)params.get("orderBy"));
//        }
//        Example example = new Example(ArtTextbook.class);
//        Example.Criteria criteria = example.createCriteria();
//        if (params.get("orderBy") != null) {
//            String orderBy = (String) params.get("orderBy");
//            example.setOrderByClause(orderBy.replace("order by", ""));
//        }
//
//        criteria.andEqualTo("state", 1);
//        if (params.containsKey("title")) {
//            criteria.andEqualTo("title", params.get("title"));
//        }
//        if (params.containsKey("author")) {
//            criteria.andEqualTo("author", params.get("author"));
//        }
//        if (params.containsKey("version")) {
//            criteria.andEqualTo("version", params.get("version"));
//        }
//        if (params.containsKey("textbookTypeId")) {
//            criteria.andEqualTo("textbookTypeId", params.get("textbookTypeId"));
//        }
//
//        return example;
//    }
//
}
