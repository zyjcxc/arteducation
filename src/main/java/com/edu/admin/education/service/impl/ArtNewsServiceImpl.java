package com.edu.admin.education.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edu.admin.education.command.ArtNewsSaveCommand;
import com.edu.admin.education.command.ArtNewsUpdateCommand;
import com.edu.admin.education.convert.ArtNewsConverter;
import com.edu.admin.education.dao.ArtNewsMapper;
import com.edu.admin.education.dto.ArtNewsDto;
import com.edu.admin.education.enums.PublicState;
import com.edu.admin.education.enums.ResultEnum;
import com.edu.admin.education.exception.HumanResourceException;
import com.edu.admin.education.model.ArtNews;
import com.edu.admin.education.service.IArtNewsService;
import com.edu.admin.server.page.table.OrderByObject;
import com.edu.admin.server.page.table.PageTableRequest;
import com.edu.admin.server.page.table.PageTableResponse;
import com.edu.admin.server.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ArtNewsServiceImpl extends ServiceImpl<ArtNewsMapper, ArtNews> implements IArtNewsService {
    
    @Autowired
    private ArtNewsMapper artNewsMapper;
    
    @Override
    public ArtNewsDto getById(Long id) {
        return ArtNewsConverter.convertToArtNewsDto(artNewsMapper.selectById(id));
    }

    @Override
    public ArtNewsDto save(ArtNewsSaveCommand command) {
        ArtNews artNews = ArtNewsConverter.convertToArtNews(command);
        artNews.setState(PublicState.NORMAL.getDataBase());
        artNews.setCreateUserId(UserUtil.getCurrentUser().getId());
        artNewsMapper.insert(artNews);
        return ArtNewsConverter.convertToArtNewsDto(artNews);
    }

    @Override
    public ArtNewsDto update(ArtNewsUpdateCommand command) {
        ArtNews oldData = artNewsMapper.selectById(command.getId());
        if (oldData == null) {
            throw new HumanResourceException(ResultEnum.NO_FIND_DATA);
        }
        oldData = ArtNewsConverter.convertToArtNews(command);
        oldData.setId(command.getId());
        artNewsMapper.updateById(oldData);
        return ArtNewsConverter.convertToArtNewsDto(oldData);
    }
    

    @Override
    public int delete(Long id) {
        return artNewsMapper.deleteById(id);
    }

    @Override
    public List<ArtNewsDto> findAll() {
        return ArtNewsConverter.convertToListArtNewsDto(artNewsMapper.selectList(null));
    }

    @Override
    public PageTableResponse queryList(PageTableRequest request) {
        Page<ArtNews> page = new Page<>(request.getCurrentPage(),request.getLimit());
        Page<ArtNews> result = artNewsMapper.selectPage(page, makeQueryConditionWrapper(request));
        List<ArtNewsDto> artNewsDtos = ArtNewsConverter.convertToListArtNewsDto(result.getRecords());
        return new PageTableResponse((int)result.getTotal(), (int)result.getTotal(), artNewsDtos);
    }

    private QueryWrapper<ArtNews> makeQueryConditionWrapper(PageTableRequest request) {
        OrderByObject orderByObject = request.getOrderByObject();
        QueryWrapper<ArtNews> query = Wrappers.query();
        Map<String, Object> params = request.getParams();
        query.eq(params.containsKey(ArtNews.Column.TYPE.key()),
                ArtNews.Column.TYPE.key(),
                params.get(ArtNews.Column.TYPE.key()));
        query.eq(params.containsKey(ArtNews.Column.ID.key()),
                ArtNews.Column.ID.key(),
                params.get(ArtNews.Column.ID.key()));
        if (orderByObject != null) {
            query.orderBy(orderByObject.isOrderBy(), orderByObject.isAsc(), orderByObject.getColumn(true));
        } else {
            query.orderBy(true, true, ArtNews.Column.ID.key());
        }
        return query;
    }
    
    
//    @Override
//    public ArtNewsDto save(ArtNewsSaveCommand command) {
//        ArtNews artNews = ArtNewsConverter.convertToArtNews(command);
//        artNews.setState("1");
//        artNews.setCreateUserId(UserUtil.getCurrentUser().getId());
//        artNewsDao.insertSelective(artNews);
//        return ArtNewsConverter.convertToArtNewsDto(artNews);
//    }
//
//    @Override
//    public ArtNewsDto update(ArtNewsUpdateCommand command) {
//        ArtNews oldData = artNewsDao.selectByPrimaryKey(command.getId());
//        if (oldData == null) {
//            throw new HumanResourceException(ResultEnum.NO_FIND_DATA);
//        }
//        oldData = ArtNewsConverter.convertToArtNews(command);
//        oldData.setId(command.getId());
//        artNewsDao.updateByPrimaryKeySelective(oldData);
//        return ArtNewsConverter.convertToArtNewsDto(oldData);
//    }
//
//    @Override
//    public List<ArtNewsDto> list(Map<String, Object> params, Integer offset, Integer limit) {
//        PageHelper.offsetPage(offset, limit);
//
////        Example example = getQueryExample(params);
//
//        List<ArtNews> list = artNewsDao.selectByCustomSql(params);
//
//        return ArtNewsConverter.convertToListArtNewsDto(list);
//    }
//
//    @Override
//    public int count(Map<String, Object> params) {
//        // 直等查询
////        ArtNews queryObject = BeanUtil.getQueryObject(params, ArtNews.class);
////        queryObject.setOrderBy(null);
//        return artNewsDao.countByCustomSql(params);
//    }
//
//    @Override
//    public int delete(Long id) {
//        return artNewsDao.deleteByPrimaryKey(id);
//    }
//
//    @Override
//    public List<ArtNewsDto> findAll() {
//        List<ArtNews> artNews = artNewsDao.selectAll();
//        return ArtNewsConverter.convertToListArtNewsDto(artNews);
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
//        ArtNews queryObject = BeanUtil.getQueryObject(params, ArtNews.class);
//        if (params.get("orderBy") != null) {
//            queryObject.setOrderBy((String)params.get("orderBy"));
//        }
//        Example example = new Example(ArtNews.class);
//        Example.Criteria criteria = example.createCriteria();
//        if (params.get("orderBy") != null) {
//            String orderBy = (String) params.get("orderBy");
//            example.setOrderByClause(orderBy.replace("order by", ""));
//        }
//
//        criteria.andEqualTo("state", 1);
//        if (params.containsKey("type")) {
//            criteria.andEqualTo("type", params.get("type"));
//        }
//
//        return example;
//    }
//
}
