package com.edu.admin.education.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edu.admin.education.command.ArtBannerInfoSaveCommand;
import com.edu.admin.education.command.ArtBannerInfoUpdateCommand;
import com.edu.admin.education.convert.ArtBannerInfoConverter;
import com.edu.admin.education.dao.ArtBannerInfoMapper;
import com.edu.admin.education.dto.ArtBannerInfoDto;
import com.edu.admin.education.enums.PublicState;
import com.edu.admin.education.enums.ResultEnum;
import com.edu.admin.education.exception.HumanResourceException;
import com.edu.admin.education.model.ArtBannerInfo;
import com.edu.admin.education.service.IArtBannerInfoService;
import com.edu.admin.server.page.table.OrderByObject;
import com.edu.admin.server.page.table.PageTableRequest;
import com.edu.admin.server.page.table.PageTableResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ArtBannerInfoServiceImpl extends ServiceImpl<ArtBannerInfoMapper, ArtBannerInfo> implements IArtBannerInfoService {
    
    @Autowired
    private ArtBannerInfoMapper artBannerInfoMapper;
    
    @Override
    public ArtBannerInfoDto getById(Long id) {
        return ArtBannerInfoConverter.convertToArtBannerInfoDto(artBannerInfoMapper.selectById(id));
    }

    @Override
    public ArtBannerInfoDto save(ArtBannerInfoSaveCommand command) {
        ArtBannerInfo artBannerInfo = ArtBannerInfoConverter.convertToArtBannerInfo(command);
        artBannerInfo.setState(PublicState.NORMAL.getDataBase());
//        artBannerInfo.setCreateUserId(UserUtil.getCurrentUser().getId());
        artBannerInfoMapper.insert(artBannerInfo);
        return ArtBannerInfoConverter.convertToArtBannerInfoDto(artBannerInfo);
    }

    @Override
    public ArtBannerInfoDto update(ArtBannerInfoUpdateCommand command) {
        ArtBannerInfo oldData = artBannerInfoMapper.selectById(command.getId());
        if (oldData == null) {
            throw new HumanResourceException(ResultEnum.NO_FIND_DATA);
        }
        oldData = ArtBannerInfoConverter.convertToArtBannerInfo(command);
        oldData.setId(command.getId());
        artBannerInfoMapper.updateById(oldData);
        return ArtBannerInfoConverter.convertToArtBannerInfoDto(oldData);
    }

    @Override
    public int delete(Long id) {
        return artBannerInfoMapper.deleteById(id);
    }

    @Override
    public List<ArtBannerInfoDto> findAll() {
        return ArtBannerInfoConverter.convertToListArtBannerInfoDto(artBannerInfoMapper.selectList(null));
    }

    @Override
    public List<ArtBannerInfoDto> findAllBySite(Map<String, Object> params) {
        List<ArtBannerInfo> artBannerInfos =
                artBannerInfoMapper.selectList(Wrappers.<ArtBannerInfo>lambdaQuery()
                        .eq(ArtBannerInfo::getSite, params.get(ArtBannerInfo.Column.SITE.key()))
                .eq(ArtBannerInfo::getRecommend, "1").orderByAsc(ArtBannerInfo::getSort));
        return ArtBannerInfoConverter.convertToListArtBannerInfoDto(artBannerInfos);
    }

    @Override
    public PageTableResponse queryList(PageTableRequest request) {
        Page<ArtBannerInfo> page = new Page<>(request.getCurrentPage(),request.getLimit());
        Page<ArtBannerInfo> result = artBannerInfoMapper.selectPage(page, makeQueryConditionWrapper(request));
        List<ArtBannerInfoDto> artBannerInfoDtos = ArtBannerInfoConverter.convertToListArtBannerInfoDto(result.getRecords());
        return new PageTableResponse((int)result.getTotal(), (int)result.getTotal(), artBannerInfoDtos);
    }

    private QueryWrapper<ArtBannerInfo> makeQueryConditionWrapper(PageTableRequest request) {
        OrderByObject orderByObject = request.getOrderByObject();
        QueryWrapper<ArtBannerInfo> query = Wrappers.query();
        Map<String, Object> params = request.getParams();
        query.eq(params.containsKey(ArtBannerInfo.Column.RECOMMEND.key()),
                ArtBannerInfo.Column.RECOMMEND.key(),
                params.get(ArtBannerInfo.Column.RECOMMEND.key()));
        query.eq(params.containsKey(ArtBannerInfo.Column.SITE.key()),
                ArtBannerInfo.Column.SITE.key(),
                params.get(ArtBannerInfo.Column.SITE.key()));
        query.eq(params.containsKey(ArtBannerInfo.Column.ID.key()),
                ArtBannerInfo.Column.ID.key(),
                params.get(ArtBannerInfo.Column.ID.key()));
        if (orderByObject != null) {
            query.orderBy(orderByObject.isOrderBy(), orderByObject.isAsc(), orderByObject.getColumn(true));
        } else {
            query.orderBy(true, true, ArtBannerInfo.Column.ID.key());
        }
        return query;
    }
}
