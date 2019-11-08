package com.edu.admin.education.service.impl;

import com.edu.admin.education.command.ArtAwardSaveCommand;
import com.edu.admin.education.command.ArtAwardUpdateCommand;
import com.edu.admin.education.convert.ArtAwardConverter;
import com.edu.admin.education.dao.ArtAwardDao;
import com.edu.admin.education.dto.ArtAwardDto;
import com.edu.admin.education.enums.ResultEnum;
import com.edu.admin.education.exception.HumanResourceException;
import com.edu.admin.education.model.ArtAward;
import com.edu.admin.education.service.IArtAwardService;
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
public class ArtAwardServiceImpl implements IArtAwardService {

    @Autowired
    private ArtAwardDao artAwardDao;

    @Override
    public ArtAwardDto getById(Long id) {
        return ArtAwardConverter.convertToArtAwardDto(artAwardDao.selectByPrimaryKey(id));
    }

    @Override
    public ArtAwardDto save(ArtAwardSaveCommand command) {
        ArtAward artAward = ArtAwardConverter.convertToArtAward(command);
        artAward.setState("1");
        artAward.setCreateUserId(UserUtil.getCurrentUser().getId());
        artAwardDao.insertSelective(artAward);
        return ArtAwardConverter.convertToArtAwardDto(artAward);
    }

    @Override
    public ArtAwardDto update(ArtAwardUpdateCommand command) {
        ArtAward oldData = artAwardDao.selectByPrimaryKey(command.getId());
        if (oldData == null) {
            throw new HumanResourceException(ResultEnum.NO_FIND_DATA);
        }
        oldData = ArtAwardConverter.convertToArtAward(command);
        oldData.setId(command.getId());
        artAwardDao.updateByPrimaryKeySelective(oldData);
        return ArtAwardConverter.convertToArtAwardDto(oldData);
    }

    @Override
    public List<ArtAwardDto> list(Map<String, Object> params, Integer offset, Integer limit) {
        PageHelper.offsetPage(offset, limit);

        Example example = getQueryExample(params);

        List<ArtAward> list = artAwardDao.selectByExample(example);

        return ArtAwardConverter.convertToListArtAwardDto(list);
    }

    @Override
    public int count(Map<String, Object> params) {
        // 直等查询
        ArtAward queryObject = BeanUtil.getQueryObject(params, ArtAward.class);
        queryObject.setOrderBy(null);
        return artAwardDao.selectCount(queryObject);
    }

    @Override
    public int delete(Long id) {
        return artAwardDao.deleteByPrimaryKey(id);
    }

    @Override
    public List<ArtAwardDto> findAll() {
        List<ArtAward> ArtAward = artAwardDao.selectAll();
        return ArtAwardConverter.convertToListArtAwardDto(ArtAward);
    }

    /**
     * 单表QBC查询
     * @param params 查询参数
     */
    private Example getQueryExample(Map<String, Object> params) {
        // criteria.andEqualTo("title", params.get("title"));
        // criteria.andEqualTo("status", params.get("status"));
        // 直等查询
        ArtAward queryObject = BeanUtil.getQueryObject(params, ArtAward.class);
        if (params.get("orderBy") != null) {
            queryObject.setOrderBy((String)params.get("orderBy"));
        }
        Example example = new Example(ArtAward.class);
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
