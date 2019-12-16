package com.edu.admin.education.service.impl;

import com.edu.admin.education.command.ArtAuthbookSaveCommand;
import com.edu.admin.education.command.ArtAuthbookUpdateCommand;
import com.edu.admin.education.convert.ArtAuthbookConverter;
import com.edu.admin.education.dao.ArtAuthbookDao;
import com.edu.admin.education.dto.ArtAuthbookDto;
import com.edu.admin.education.enums.ResultEnum;
import com.edu.admin.education.exception.HumanResourceException;
import com.edu.admin.education.model.ArtAuthbook;
import com.edu.admin.education.service.IArtAuthbookService;
import com.edu.admin.server.utils.BeanUtil;
import com.edu.admin.server.utils.UserUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ArtAuthbookServiceImpl implements IArtAuthbookService {

    @Autowired
    private ArtAuthbookDao artAuthbookDao;

    @Override
    public ArtAuthbookDto getById(Long id) {
        return ArtAuthbookConverter.convertToArtAuthbookDto(artAuthbookDao.selectByPrimaryKey(id));
    }

    @Override
    public ArtAuthbookDto save(ArtAuthbookSaveCommand command) {
        ArtAuthbook artAuthbook = ArtAuthbookConverter.convertToArtAuthbook(command);
        artAuthbook.setState("1");
        artAuthbook.setCreateUserId(UserUtil.getCurrentUser().getId());
        artAuthbookDao.insertSelective(artAuthbook);
        return ArtAuthbookConverter.convertToArtAuthbookDto(artAuthbook);
    }

    @Override
    public ArtAuthbookDto update(ArtAuthbookUpdateCommand command) {
        ArtAuthbook oldData = artAuthbookDao.selectByPrimaryKey(command.getId());
        if (oldData == null) {
            throw new HumanResourceException(ResultEnum.NO_FIND_DATA);
        }
        oldData = ArtAuthbookConverter.convertToArtAuthbook(command);
        oldData.setId(command.getId());
        artAuthbookDao.updateByPrimaryKeySelective(oldData);
        return ArtAuthbookConverter.convertToArtAuthbookDto(oldData);
    }

    @Override
    public List<ArtAuthbookDto> list(Map<String, Object> params, Integer offset, Integer limit) {
        PageHelper.offsetPage(offset, limit);

        Example example = getQueryExample(params);

        List<ArtAuthbook> list = artAuthbookDao.selectByExample(example);

        return ArtAuthbookConverter.convertToListArtAuthbookDto(list);
    }

    @Override
    public int count(Map<String, Object> params) {
        // 直等查询
        ArtAuthbook queryObject = BeanUtil.getQueryObject(params, ArtAuthbook.class);
        queryObject.setOrderBy(null);
        return artAuthbookDao.selectCount(queryObject);
    }

    @Override
    public int delete(Long id) {
        return artAuthbookDao.deleteByPrimaryKey(id);
    }

    @Override
    public List<ArtAuthbookDto> findAll() {
        List<ArtAuthbook> ArtAuthbook = artAuthbookDao.selectAll();
        return ArtAuthbookConverter.convertToListArtAuthbookDto(ArtAuthbook);
    }

    /**
     * 单表QBC查询
     * @param params 查询参数
     */
    private Example getQueryExample(Map<String, Object> params) {
        // criteria.andEqualTo("title", params.get("title"));
        // criteria.andEqualTo("status", params.get("status"));
        // 直等查询
        ArtAuthbook queryObject = BeanUtil.getQueryObject(params, ArtAuthbook.class);
        if (params.get("orderBy") != null) {
            queryObject.setOrderBy((String)params.get("orderBy"));
        }
        Example example = new Example(ArtAuthbook.class);
        Example.Criteria criteria = example.createCriteria();
        if (params.get("orderBy") != null) {
            String orderBy = (String) params.get("orderBy");
            example.setOrderByClause(orderBy.replace("order by", ""));
        }
        if (params.containsKey("id")) {
            criteria.andEqualTo("id", params.get("id"));
        }
        if (params.containsKey("title")) {
            criteria.andEqualTo("title", params.get("title"));
        }
        criteria.andEqualTo("state", 1);
        return example;
    }

}
