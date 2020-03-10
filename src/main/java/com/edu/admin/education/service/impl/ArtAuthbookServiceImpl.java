package com.edu.admin.education.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edu.admin.education.command.ArtAuthbookSaveCommand;
import com.edu.admin.education.command.ArtAuthbookUpdateCommand;
import com.edu.admin.education.convert.ArtAuthbookConverter;
import com.edu.admin.education.dao.ArtAuthbookMapper;
import com.edu.admin.education.dto.ArtAuthbookDto;
import com.edu.admin.education.enums.PublicState;
import com.edu.admin.education.enums.ResultEnum;
import com.edu.admin.education.exception.HumanResourceException;
import com.edu.admin.education.model.ArtAuthbook;
import com.edu.admin.education.service.IArtAuthbookService;
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
public class ArtAuthbookServiceImpl extends ServiceImpl<ArtAuthbookMapper, ArtAuthbook> implements IArtAuthbookService {

    @Autowired
    private ArtAuthbookMapper artAuthbookMapper;

    @Override
    public ArtAuthbookDto getById(Long id) {
        return ArtAuthbookConverter.convertToArtAuthbookDto(artAuthbookMapper.selectById(id));
    }

    @Override
    public ArtAuthbookDto save(ArtAuthbookSaveCommand command) {
        ArtAuthbook artAuthbook = ArtAuthbookConverter.convertToArtAuthbook(command);
        artAuthbook.setState(PublicState.NORMAL.getDataBase());
        artAuthbook.setCreateUserId(UserUtil.getCurrentUser().getId());
        artAuthbookMapper.insert(artAuthbook);
//        artAuthbookDao.insertSelective(artAuthbook);
        return ArtAuthbookConverter.convertToArtAuthbookDto(artAuthbook);
    }

    @Override
    public ArtAuthbookDto update(ArtAuthbookUpdateCommand command) {
//        ArtAuthbook oldData = artAuthbookDao.selectByPrimaryKey(command.getId());
        ArtAuthbook oldData = artAuthbookMapper.selectById(command.getId());
        if (oldData == null) {
            throw new HumanResourceException(ResultEnum.NO_FIND_DATA);
        }
        oldData = ArtAuthbookConverter.convertToArtAuthbook(command);
        oldData.setId(command.getId());
        artAuthbookMapper.updateById(oldData);
//        artAuthbookDao.updateByPrimaryKeySelective(oldData);
        return ArtAuthbookConverter.convertToArtAuthbookDto(oldData);
    }

    @Override
    public void delete(Long id) {
        artAuthbookMapper.deleteById(id);
    }

    @Override
    public List<ArtAuthbookDto> findAll() {
        return ArtAuthbookConverter.convertToListArtAuthbookDto(artAuthbookMapper.selectList(null));
    }

    @Override
    public PageTableResponse queryList(PageTableRequest request) {
        Page<ArtAuthbook> page = new Page<>(request.getCurrentPage(),request.getLimit());
        Page<ArtAuthbook> result = artAuthbookMapper.selectPage(page, makeQueryConditionWrapper(request));
        List<ArtAuthbookDto> artAuthbookDtos = ArtAuthbookConverter.convertToListArtAuthbookDto(result.getRecords());
        return new PageTableResponse((int)result.getTotal(), (int)result.getTotal(), artAuthbookDtos);
    }

    private QueryWrapper<ArtAuthbook> makeQueryConditionWrapper(PageTableRequest request) {
        OrderByObject orderByObject = request.getOrderByObject();
        QueryWrapper<ArtAuthbook> query = Wrappers.query();
        Map<String, Object> params = request.getParams();
        query.like(params.containsKey(ArtAuthbook.Column.TITLE.key()),
                ArtAuthbook.Column.TITLE.key(),
                params.get(ArtAuthbook.Column.TITLE.key()));
        query.eq(params.containsKey(ArtAuthbook.Column.ID.key()),
                ArtAuthbook.Column.ID.key(),
                params.get(ArtAuthbook.Column.ID.key()));
        if (orderByObject != null) {
            query.orderBy(orderByObject.isOrderBy(), orderByObject.isAsc(), orderByObject.getColumn(true));
        } else {
            query.orderBy(true, true, ArtAuthbook.Column.ID.key());
        }
        return query;
    }

}
