package com.edu.admin.education.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edu.admin.education.command.ArtHomeSchoolSaveCommand;
import com.edu.admin.education.command.ArtHomeSchoolUpdateCommand;
import com.edu.admin.education.convert.ArtHomeSchoolConverter;
import com.edu.admin.education.dao.ArtHomeSchoolMapper;
import com.edu.admin.education.dto.ArtHomeSchoolDto;
import com.edu.admin.education.enums.ResultEnum;
import com.edu.admin.education.exception.HumanResourceException;
import com.edu.admin.education.model.ArtHomeSchool;
import com.edu.admin.education.service.IArtHomeSchoolService;
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
public class ArtHomeSchoolServiceImpl extends ServiceImpl<ArtHomeSchoolMapper, ArtHomeSchool> implements IArtHomeSchoolService {

    @Autowired
    private ArtHomeSchoolMapper artHomeSchoolMapper;

    @Override
    public ArtHomeSchoolDto getById(Long id) {
        return ArtHomeSchoolConverter.convertToArtHomeSchoolDto(artHomeSchoolMapper.selectById(id));
    }

    @Override
    public ArtHomeSchoolDto save(ArtHomeSchoolSaveCommand command) {
        ArtHomeSchool artHomeSchool = ArtHomeSchoolConverter.convertToArtHomeSchool(command);
        artHomeSchoolMapper.insert(artHomeSchool);
        return ArtHomeSchoolConverter.convertToArtHomeSchoolDto(artHomeSchool);
    }

    @Override
    public ArtHomeSchoolDto update(ArtHomeSchoolUpdateCommand command) {
        ArtHomeSchool oldData = artHomeSchoolMapper.selectById(command.getId());
        if (oldData == null) {
            throw new HumanResourceException(ResultEnum.NO_FIND_DATA);
        }
        oldData = ArtHomeSchoolConverter.convertToArtHomeSchool(command);
        oldData.setId(command.getId());
        artHomeSchoolMapper.updateById(oldData);
        return ArtHomeSchoolConverter.convertToArtHomeSchoolDto(oldData);
    }

    @Override
    public int delete(Long id) {
        return artHomeSchoolMapper.deleteById(id);
    }

    @Override
    public List<ArtHomeSchoolDto> findAll() {
        return ArtHomeSchoolConverter.convertToListArtHomeSchoolDto(artHomeSchoolMapper.selectList(null));
    }

    @Override
    public List<ArtHomeSchoolDto> findRecommendList() {
        List<ArtHomeSchool> artHomeSchools =
                artHomeSchoolMapper.selectList(Wrappers.<ArtHomeSchool>lambdaQuery()
                        .eq(ArtHomeSchool::getRecommend, "1").orderByAsc(ArtHomeSchool::getSort));
        return ArtHomeSchoolConverter.convertToListArtHomeSchoolDto(artHomeSchools);
    }

    @Override
    public PageTableResponse queryList(PageTableRequest request) {
        Page<ArtHomeSchool> page = new Page<>(request.getCurrentPage(),request.getLimit());
        Page<ArtHomeSchool> result = artHomeSchoolMapper.selectPage(page, makeQueryConditionWrapper(request));
        List<ArtHomeSchoolDto> artHomeSchoolDtos = ArtHomeSchoolConverter.convertToListArtHomeSchoolDto(result.getRecords());
        return new PageTableResponse((int)result.getTotal(), (int)result.getTotal(), artHomeSchoolDtos);
    }

    private QueryWrapper<ArtHomeSchool> makeQueryConditionWrapper(PageTableRequest request) {
        OrderByObject orderByObject = request.getOrderByObject();
        QueryWrapper<ArtHomeSchool> query = Wrappers.query();
        Map<String, Object> params = request.getParams();
        query.eq(params.containsKey(ArtHomeSchool.Column.RECOMMEND.key()),
                ArtHomeSchool.Column.RECOMMEND.key(),
                params.get(ArtHomeSchool.Column.RECOMMEND.key()));
        query.like(params.containsKey(ArtHomeSchool.Column.NAME.key()),
                ArtHomeSchool.Column.NAME.key(),
                params.get(ArtHomeSchool.Column.NAME.key()));
        query.eq(params.containsKey(ArtHomeSchool.Column.ID.key()),
                ArtHomeSchool.Column.ID.key(),
                params.get(ArtHomeSchool.Column.ID.key()));
        if (orderByObject != null) {
            query.orderBy(orderByObject.isOrderBy(), orderByObject.isAsc(), orderByObject.getColumn(true));
        } else {
            query.orderBy(true, true, ArtHomeSchool.Column.ID.key());
        }
        return query;
    }


}
