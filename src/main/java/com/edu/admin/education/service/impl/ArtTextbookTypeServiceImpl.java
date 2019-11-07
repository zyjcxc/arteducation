package com.edu.admin.education.service.impl;

import com.edu.admin.education.command.ArtTextbookTypeSaveCommand;
import com.edu.admin.education.command.ArtTextbookTypeUpdateCommand;
import com.edu.admin.education.convert.ArtTextbookTypeConverter;
import com.edu.admin.education.dao.ArtTextbookTypeDao;
import com.edu.admin.education.dto.ArtTextbookTypeDto;
import com.edu.admin.education.enums.ResultEnum;
import com.edu.admin.education.exception.HumanResourceException;
import com.edu.admin.education.model.ArtTextbookType;
import com.edu.admin.education.service.IArtTextbookTypeService;
import com.edu.admin.server.utils.BeanUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ArtTextbookTypeServiceImpl implements IArtTextbookTypeService {

    @Autowired
    private ArtTextbookTypeDao artTextbookTypeDao;

    @Override
    public ArtTextbookTypeDto getById(Long id) {
        return ArtTextbookTypeConverter.convertToArtTextbookTypeDto(artTextbookTypeDao.selectByPrimaryKey(id));
    }

    @Override
    public ArtTextbookTypeDto save(ArtTextbookTypeSaveCommand command) {
        ArtTextbookType artTextbookType = ArtTextbookTypeConverter.convertToArtTextbookType(command);
        artTextbookType.setState("1");
        artTextbookTypeDao.insertSelective(artTextbookType);
        return ArtTextbookTypeConverter.convertToArtTextbookTypeDto(artTextbookType);
    }

    @Override
    public ArtTextbookTypeDto update(ArtTextbookTypeUpdateCommand command) {
        ArtTextbookType oldData = artTextbookTypeDao.selectByPrimaryKey(command.getId());
        if (oldData == null) {
            throw new HumanResourceException(ResultEnum.NO_FIND_DATA);
        }
        oldData = ArtTextbookTypeConverter.convertToArtTextbookType(command);
        oldData.setId(command.getId());
        artTextbookTypeDao.updateByPrimaryKeySelective(oldData);
        return ArtTextbookTypeConverter.convertToArtTextbookTypeDto(oldData);
    }

    @Override
    public List<ArtTextbookTypeDto> list(Map<String, Object> params, Integer offset, Integer limit) {
        PageHelper.offsetPage(offset, limit);

        Example example = getQueryExample(params);

        List<ArtTextbookType> list = artTextbookTypeDao.selectByExample(example);

        return ArtTextbookTypeConverter.convertToListArtTextbookTypeDto(list);
    }

    @Override
    public int count(Map<String, Object> params) {
        // 直等查询
        ArtTextbookType queryObject = BeanUtil.getQueryObject(params, ArtTextbookType.class);
        queryObject.setOrderBy(null);
        return artTextbookTypeDao.selectCount(queryObject);
    }

    @Override
    public int delete(Long id) {
        return artTextbookTypeDao.deleteByPrimaryKey(id);
    }

    @Override
    public List<ArtTextbookTypeDto> findAll() {
        List<ArtTextbookType> ArtTextbookType = artTextbookTypeDao.selectAll();
        return ArtTextbookTypeConverter.convertToListArtTextbookTypeDto(ArtTextbookType);
    }

    /**
     * 单表QBC查询
     * @param params 查询参数
     */
    private Example getQueryExample(Map<String, Object> params) {
        // criteria.andEqualTo("title", params.get("title"));
        // criteria.andEqualTo("status", params.get("status"));
        // 直等查询
        ArtTextbookType queryObject = BeanUtil.getQueryObject(params, ArtTextbookType.class);
        if (params.get("orderBy") != null) {
            queryObject.setOrderBy((String)params.get("orderBy"));
        }
        Example example = new Example(ArtTextbookType.class);
        Example.Criteria criteria = example.createCriteria();
        if (params.get("orderBy") != null) {
            String orderBy = (String) params.get("orderBy");
            example.setOrderByClause(orderBy.replace("order by", ""));
        }

        criteria.andEqualTo("state", 1);
        /*if (params.containsKey("name")) {
            criteria.andEqualTo("name", params.get("name"));
        }*/

        return example;
    }

}
