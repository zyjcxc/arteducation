package com.edu.admin.education.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edu.admin.education.command.ArtAwardSaveCommand;
import com.edu.admin.education.command.ArtAwardUpdateCommand;
import com.edu.admin.education.convert.ArtAwardConverter;
import com.edu.admin.education.dao.ArtAwardMapper;
import com.edu.admin.education.dto.ArtAwardDto;
import com.edu.admin.education.enums.PublicState;
import com.edu.admin.education.enums.ResultEnum;
import com.edu.admin.education.exception.HumanResourceException;
import com.edu.admin.education.model.ArtAward;
import com.edu.admin.education.service.IArtAwardService;
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
public class ArtAwardServiceImpl extends ServiceImpl<ArtAwardMapper, ArtAward> implements IArtAwardService {

    @Autowired
    private ArtAwardMapper artAwardMapper;

    @Override
    public ArtAwardDto getById(Long id) {
        return ArtAwardConverter.convertToArtAwardDto(artAwardMapper.selectById(id));
    }

    @Override
    public ArtAwardDto save(ArtAwardSaveCommand command) {
        ArtAward artAward = ArtAwardConverter.convertToArtAward(command);
        artAward.setState(PublicState.NORMAL.getDataBase());
        artAward.setCreateUserId(UserUtil.getCurrentUser().getId());
        artAwardMapper.insert(artAward);
        return ArtAwardConverter.convertToArtAwardDto(artAward);
    }

    @Override
    public ArtAwardDto update(ArtAwardUpdateCommand command) {
        ArtAward oldData = artAwardMapper.selectById(command.getId());
        if (oldData == null) {
            throw new HumanResourceException(ResultEnum.NO_FIND_DATA);
        }
        oldData = ArtAwardConverter.convertToArtAward(command);
        oldData.setId(command.getId());
        artAwardMapper.updateById(oldData);
        return ArtAwardConverter.convertToArtAwardDto(oldData);
    }

    @Override
    public int delete(Long id) {
        return artAwardMapper.deleteById(id);
    }

    @Override
    public List<ArtAwardDto> findAll() {
        return ArtAwardConverter.convertToListArtAwardDto(artAwardMapper.selectList(null));
    }

    @Override
    public PageTableResponse queryList(PageTableRequest request) {
        Page<ArtAward> page = new Page<>(request.getCurrentPage(),request.getLimit());
        Page<ArtAward> result = artAwardMapper.selectPage(page, makeQueryConditionWrapper(request));
        List<ArtAwardDto> artAwardDtos = ArtAwardConverter.convertToListArtAwardDto(result.getRecords());
        return new PageTableResponse((int)result.getTotal(), (int)result.getTotal(), artAwardDtos);
    }

    private QueryWrapper<ArtAward> makeQueryConditionWrapper(PageTableRequest request) {
        OrderByObject orderByObject = request.getOrderByObject();
        QueryWrapper<ArtAward> query = Wrappers.query();
        Map<String, Object> params = request.getParams();
        query.like(params.containsKey(ArtAward.Column.TITLE.key()),
                ArtAward.Column.TITLE.key(),
                params.get(ArtAward.Column.TITLE.key()));
        query.eq(params.containsKey(ArtAward.Column.ID.key()),
                ArtAward.Column.ID.key(),
                params.get(ArtAward.Column.ID.key()));
        if (orderByObject != null) {
            query.orderBy(orderByObject.isOrderBy(), orderByObject.isAsc(), orderByObject.getColumn(true));
        } else {
            query.orderBy(true, true, ArtAward.Column.ID.key());
        }
        return query;
    }

}
