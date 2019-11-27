package com.edu.admin.education.service.impl;

import com.edu.admin.education.command.ArtNewsSaveCommand;
import com.edu.admin.education.command.ArtNewsUpdateCommand;
import com.edu.admin.education.convert.ArtNewsConverter;
import com.edu.admin.education.dao.ArtNewsDao;
import com.edu.admin.education.dto.ArtNewsDto;
import com.edu.admin.education.enums.ResultEnum;
import com.edu.admin.education.exception.HumanResourceException;
import com.edu.admin.education.model.ArtNews;
import com.edu.admin.education.service.IArtNewsService;
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
public class ArtNewsServiceImpl implements IArtNewsService {

    @Autowired
    private ArtNewsDao artNewsDao;

    @Override
    public ArtNewsDto getById(Long id) {
        return ArtNewsConverter.convertToArtNewsDto(artNewsDao.selectByPrimaryKey(id));
    }

    @Override
    public ArtNewsDto save(ArtNewsSaveCommand command) {
        ArtNews artNews = ArtNewsConverter.convertToArtNews(command);
        artNews.setState("1");
        artNews.setCreateUserId(UserUtil.getCurrentUser().getId());
        artNewsDao.insertSelective(artNews);
        return ArtNewsConverter.convertToArtNewsDto(artNews);
    }

    @Override
    public ArtNewsDto update(ArtNewsUpdateCommand command) {
        ArtNews oldData = artNewsDao.selectByPrimaryKey(command.getId());
        if (oldData == null) {
            throw new HumanResourceException(ResultEnum.NO_FIND_DATA);
        }
        oldData = ArtNewsConverter.convertToArtNews(command);
        oldData.setId(command.getId());
        artNewsDao.updateByPrimaryKeySelective(oldData);
        return ArtNewsConverter.convertToArtNewsDto(oldData);
    }

    @Override
    public List<ArtNewsDto> list(Map<String, Object> params, Integer offset, Integer limit) {
        PageHelper.offsetPage(offset, limit);

        Example example = getQueryExample(params);

        List<ArtNews> list = artNewsDao.selectByExample(example);

        return ArtNewsConverter.convertToListArtNewsDto(list);
    }

    @Override
    public int count(Map<String, Object> params) {
        // 直等查询
        ArtNews queryObject = BeanUtil.getQueryObject(params, ArtNews.class);
        queryObject.setOrderBy(null);
        return artNewsDao.selectCount(queryObject);
    }

    @Override
    public int delete(Long id) {
        return artNewsDao.deleteByPrimaryKey(id);
    }

    @Override
    public List<ArtNewsDto> findAll() {
        List<ArtNews> artNews = artNewsDao.selectAll();
        return ArtNewsConverter.convertToListArtNewsDto(artNews);
    }

    /**
     * 单表QBC查询
     * @param params 查询参数
     */
    private Example getQueryExample(Map<String, Object> params) {
        // criteria.andEqualTo("title", params.get("title"));
        // criteria.andEqualTo("status", params.get("status"));
        // 直等查询
        ArtNews queryObject = BeanUtil.getQueryObject(params, ArtNews.class);
        if (params.get("orderBy") != null) {
            queryObject.setOrderBy((String)params.get("orderBy"));
        }
        Example example = new Example(ArtNews.class);
        Example.Criteria criteria = example.createCriteria();
        if (params.get("orderBy") != null) {
            String orderBy = (String) params.get("orderBy");
            example.setOrderByClause(orderBy.replace("order by", ""));
        }

        criteria.andEqualTo("state", 1);
        if (params.containsKey("type")) {
            criteria.andEqualTo("type", params.get("type"));
        }

        return example;
    }

}
