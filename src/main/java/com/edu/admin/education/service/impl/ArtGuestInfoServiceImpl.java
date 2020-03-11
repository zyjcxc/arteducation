package com.edu.admin.education.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edu.admin.education.command.ArtGuestInfoSaveCommand;
import com.edu.admin.education.convert.ArtGuestInfoConverter;
import com.edu.admin.education.dao.ArtGuestInfoMapper;
import com.edu.admin.education.dto.ArtGuestInfoDto;
import com.edu.admin.education.enums.ResultEnum;
import com.edu.admin.education.exception.HumanResourceException;
import com.edu.admin.education.model.ArtGuestInfo;
import com.edu.admin.education.service.IArtGuestInfoService;
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
public class ArtGuestInfoServiceImpl extends ServiceImpl<ArtGuestInfoMapper, ArtGuestInfo> implements IArtGuestInfoService {
   
    @Autowired
    private ArtGuestInfoMapper artGuestInfoMapper;
    
    @Override
    public ArtGuestInfoDto save(ArtGuestInfoSaveCommand command) {
        ArtGuestInfo artGuestInfo = ArtGuestInfoConverter.convertToArtGuestInfo(command);
        ArtGuestInfo oldData
                = artGuestInfoMapper.selectOne(Wrappers.<ArtGuestInfo>lambdaQuery().eq(ArtGuestInfo::getPhone, command.getPhone()));
        if (oldData != null) {
            throw new HumanResourceException(ResultEnum.REPEAT_SUBMIT_RECORD);
        }
        artGuestInfoMapper.insert(artGuestInfo);
        return ArtGuestInfoConverter.convertToArtGuestInfoDto(artGuestInfo);
    }

    @Override
    public PageTableResponse queryList(PageTableRequest request) {
        Page<ArtGuestInfo> page = new Page<>(request.getCurrentPage(),request.getLimit());
        Page<ArtGuestInfo> result = artGuestInfoMapper.selectPage(page, makeQueryConditionWrapper(request));
        List<ArtGuestInfoDto> artGuestInfoDtos = ArtGuestInfoConverter.convertToListArtGuestInfoDto(result.getRecords());
        return new PageTableResponse((int)result.getTotal(), (int)result.getTotal(), artGuestInfoDtos);
    }

    private QueryWrapper<ArtGuestInfo> makeQueryConditionWrapper(PageTableRequest request) {
        OrderByObject orderByObject = request.getOrderByObject();
        QueryWrapper<ArtGuestInfo> query = Wrappers.query();
        Map<String, Object> params = request.getParams();
        query.like(params.containsKey(ArtGuestInfo.Column.NAME.key()),
                ArtGuestInfo.Column.NAME.key(),
                params.get(ArtGuestInfo.Column.NAME.key()));
        query.eq(params.containsKey(ArtGuestInfo.Column.ID.key()),
                ArtGuestInfo.Column.ID.key(),
                params.get(ArtGuestInfo.Column.ID.key()));
        if (orderByObject != null) {
            query.orderBy(orderByObject.isOrderBy(), orderByObject.isAsc(), orderByObject.getColumn(true));
        } else {
            query.orderBy(true, true, ArtGuestInfo.Column.ID.key());
        }
        return query;
    }
}
