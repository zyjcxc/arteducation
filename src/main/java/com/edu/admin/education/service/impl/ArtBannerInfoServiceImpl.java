package com.edu.admin.education.service.impl;

import com.edu.admin.education.command.ArtBannerInfoSaveCommand;
import com.edu.admin.education.command.ArtBannerInfoUpdateCommand;
import com.edu.admin.education.convert.ArtBannerInfoConverter;
import com.edu.admin.education.dao.ArtBannerInfoDao;
import com.edu.admin.education.dto.ArtBannerInfoDto;
import com.edu.admin.education.enums.ResultEnum;
import com.edu.admin.education.exception.HumanResourceException;
import com.edu.admin.education.model.ArtBannerInfo;
import com.edu.admin.education.service.IArtBannerInfoService;
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
public class ArtBannerInfoServiceImpl implements IArtBannerInfoService {

    @Autowired
    private ArtBannerInfoDao artBannerInfoDao;

    @Override
    public ArtBannerInfoDto getById(Long id) {
        return ArtBannerInfoConverter.convertToArtBannerInfoDto(artBannerInfoDao.selectByPrimaryKey(id));
    }

    @Override
    public ArtBannerInfoDto save(ArtBannerInfoSaveCommand command) {
        ArtBannerInfo artBannerInfo = ArtBannerInfoConverter.convertToArtBannerInfo(command);
        artBannerInfo.setState("1");
        artBannerInfoDao.insertSelective(artBannerInfo);
        return ArtBannerInfoConverter.convertToArtBannerInfoDto(artBannerInfo);
    }

    @Override
    public ArtBannerInfoDto update(ArtBannerInfoUpdateCommand command) {
        ArtBannerInfo oldData = artBannerInfoDao.selectByPrimaryKey(command.getId());
        if (oldData == null) {
            throw new HumanResourceException(ResultEnum.NO_FIND_DATA);
        }
        oldData = ArtBannerInfoConverter.convertToArtBannerInfo(command);
        oldData.setId(command.getId());
        artBannerInfoDao.updateByPrimaryKeySelective(oldData);
        return ArtBannerInfoConverter.convertToArtBannerInfoDto(oldData);
    }

    @Override
    public List<ArtBannerInfoDto> list(Map<String, Object> params, Integer offset, Integer limit) {
        PageHelper.offsetPage(offset, limit);

        Example example = getQueryExample(params);

        List<ArtBannerInfo> list = artBannerInfoDao.selectByExample(example);

        return ArtBannerInfoConverter.convertToListArtBannerInfoDto(list);
    }

    @Override
    public int count(Map<String, Object> params) {
        // 直等查询
        ArtBannerInfo queryObject = BeanUtil.getQueryObject(params, ArtBannerInfo.class);
        queryObject.setOrderBy(null);
        return artBannerInfoDao.selectCount(queryObject);
    }

    @Override
    public int delete(Long id) {
        return artBannerInfoDao.deleteByPrimaryKey(id);
    }

    @Override
    public List<ArtBannerInfoDto> findAll() {
        List<ArtBannerInfo> ArtBannerInfo = artBannerInfoDao.selectAll();
        return ArtBannerInfoConverter.convertToListArtBannerInfoDto(ArtBannerInfo);
    }

    @Override
    public List<ArtBannerInfoDto> findAllBySite(Map<String, Object> params) {

        params.put("recommend", 1);
        params.put("orderBy", "sort asc");

        Example example = getQueryExample(params);

        List<ArtBannerInfo> list = artBannerInfoDao.selectByExample(example);

        return ArtBannerInfoConverter.convertToListArtBannerInfoDto(list);
    }

    /**
     * 单表QBC查询
     * @param params 查询参数
     */
    private Example getQueryExample(Map<String, Object> params) {
        // criteria.andEqualTo("title", params.get("title"));
        // criteria.andEqualTo("status", params.get("status"));
        // 直等查询
        ArtBannerInfo queryObject = BeanUtil.getQueryObject(params, ArtBannerInfo.class);
        if (params.get("orderBy") != null) {
            queryObject.setOrderBy((String)params.get("orderBy"));
        }
        Example example = new Example(ArtBannerInfo.class);
        Example.Criteria criteria = example.createCriteria();
        if (params.get("orderBy") != null) {
            String orderBy = (String) params.get("orderBy");
            example.setOrderByClause(orderBy.replace("order by", ""));
        }

        criteria.andEqualTo("state", 1);

        if (params.containsKey("site")) {
            criteria.andEqualTo("site", params.get("site"));
        }

        if (params.containsKey("recommend")) {
            criteria.andEqualTo("recommend", params.get("recommend"));
        }

        if (params.get("orderBy") != null) {
            String orderBy = (String) params.get("orderBy");
            example.setOrderByClause(orderBy.replace("order by", ""));
        }


        return example;
    }

}
