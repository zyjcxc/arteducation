package com.edu.admin.education.service.impl;

import com.edu.admin.education.command.ArtGuestInfoSaveCommand;
import com.edu.admin.education.convert.ArtGuestInfoConverter;
import com.edu.admin.education.dao.ArtGuestInfoDao;
import com.edu.admin.education.dto.ArtGuestInfoDto;
import com.edu.admin.education.model.ArtBannerInfo;
import com.edu.admin.education.model.ArtGuestInfo;
import com.edu.admin.education.service.IArtGuestInfoService;
import com.edu.admin.server.utils.BeanUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

/**
 * @author mengqa
 * @create 2019-12-02 21:57
 **/

@Service
@Transactional
public class ArtGuestInfoServiceImpl implements IArtGuestInfoService {

    @Autowired
    private ArtGuestInfoDao artGuestInfoDao;

    @Override
    public ArtGuestInfoDto save(ArtGuestInfoSaveCommand command) {
        ArtGuestInfo artGuestInfo = ArtGuestInfoConverter.convertToArtGuestInfo(command);
        artGuestInfoDao.insertSelective(artGuestInfo);
        return ArtGuestInfoConverter.convertToArtGuestInfoDto(artGuestInfo);
    }

    @Override
    public int count(Map<String, Object> params) {
        // 直等查询
        ArtGuestInfo queryObject = BeanUtil.getQueryObject(params, ArtGuestInfo.class);
        queryObject.setOrderBy(null);
        return artGuestInfoDao.selectCount(queryObject);
    }

    @Override
    public List<ArtGuestInfoDto> list(Map<String, Object> params, Integer offset, Integer limit) {
        PageHelper.offsetPage(offset, limit);

        Example example = getQueryExample(params);

        List<ArtGuestInfo> list = artGuestInfoDao.selectByExample(example);

        return ArtGuestInfoConverter.convertToListArtGuestInfoDto(list);
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

        return example;
    }
}
