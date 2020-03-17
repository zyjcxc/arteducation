package com.edu.admin.education.service.impl;
//
//import com.edu.admin.education.command.ArtTeacherSaveCommand;
//import com.edu.admin.education.command.ArtTeacherUpdateCommand;
//import com.edu.admin.education.convert.ArtTeacherConverter;
//import com.edu.admin.education.dao.ArtTeacherDao;
//import com.edu.admin.education.dto.ArtTeacherDto;
//import com.edu.admin.education.enums.ResultEnum;
//import com.edu.admin.education.exception.HumanResourceException;
//import com.edu.admin.education.model.ArtTeacher;
//import com.edu.admin.education.service.IArtTeacherService;
//import com.edu.admin.server.utils.BeanUtil;
//import com.github.pagehelper.PageHelper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import tk.mybatis.mapper.entity.Example;
//
//import java.util.List;
//import java.util.Map;
//

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edu.admin.education.command.ArtTeacherSaveCommand;
import com.edu.admin.education.command.ArtTeacherUpdateCommand;
import com.edu.admin.education.convert.ArtTeacherConverter;
import com.edu.admin.education.dao.ArtTeacherMapper;
import com.edu.admin.education.dto.ArtTeacherDto;
import com.edu.admin.education.enums.PublicState;
import com.edu.admin.education.enums.ResultEnum;
import com.edu.admin.education.exception.HumanResourceException;
import com.edu.admin.education.model.ArtTeacher;
import com.edu.admin.education.service.IArtTeacherService;
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
public class ArtTeacherServiceImpl extends ServiceImpl<ArtTeacherMapper, ArtTeacher>  implements IArtTeacherService {
    
    @Autowired
    private ArtTeacherMapper artTeacherMapper;
    
    @Override
    public ArtTeacherDto getById(Long id) {
        return ArtTeacherConverter.convertToArtTeacherDto(artTeacherMapper.selectById(id));
    }

    @Override
    public ArtTeacherDto save(ArtTeacherSaveCommand command) {
        ArtTeacher artTeacher = ArtTeacherConverter.convertToArtTeacher(command);
        artTeacher.setState(PublicState.NORMAL.getDataBase());
        artTeacherMapper.insert(artTeacher);
        return ArtTeacherConverter.convertToArtTeacherDto(artTeacher);
    }

    @Override
    public ArtTeacherDto update(ArtTeacherUpdateCommand command) {
        ArtTeacher oldData = artTeacherMapper.selectById(command.getId());
        if (oldData == null) {
            throw new HumanResourceException(ResultEnum.NO_FIND_DATA);
        }
        oldData = ArtTeacherConverter.convertToArtTeacher(command);
        oldData.setId(command.getId());
        artTeacherMapper.updateById(oldData);
        return ArtTeacherConverter.convertToArtTeacherDto(oldData);
    }

    @Override
    public int delete(Long id) {
        return artTeacherMapper.deleteById(id);
    }

    @Override
    public List<ArtTeacherDto> findAll() {
        return ArtTeacherConverter.convertToListArtTeacherDto(artTeacherMapper.selectList(null));
    }

    @Override
    public PageTableResponse queryList(PageTableRequest request) {
        Page<ArtTeacher> page = new Page<>(request.getCurrentPage(),request.getLimit());
        Page<ArtTeacher> result = artTeacherMapper.selectPage(page, makeQueryConditionWrapper(request));
        List<ArtTeacherDto> artTeacherDtos = ArtTeacherConverter.convertToListArtTeacherDto(result.getRecords());
        return new PageTableResponse((int)result.getTotal(), (int)result.getTotal(), artTeacherDtos);
    }

    private QueryWrapper<ArtTeacher> makeQueryConditionWrapper(PageTableRequest request) {
        OrderByObject orderByObject = request.getOrderByObject();
        QueryWrapper<ArtTeacher> query = Wrappers.query();
        Map<String, Object> params = request.getParams();
        query.eq(params.containsKey(ArtTeacher.Column.NAME.key()),
                ArtTeacher.Column.NAME.key(),
                params.get(ArtTeacher.Column.NAME.key()));
        query.eq(params.containsKey(ArtTeacher.Column.SEX.key()),
                ArtTeacher.Column.SEX.key(),
                params.get(ArtTeacher.Column.SEX.key()));
        query.eq(params.containsKey(ArtTeacher.Column.ID.key()),
                ArtTeacher.Column.ID.key(),
                params.get(ArtTeacher.Column.ID.key()));
        if (orderByObject != null) {
            query.orderBy(orderByObject.isOrderBy(), orderByObject.isAsc(), orderByObject.getColumn(true));
        } else {
            query.orderBy(true, true, ArtTeacher.Column.ID.key());
        }
        return query;
    }
    
    
//
//    /**
//     * 单表QBC查询
//     * @param params 查询参数
//     */
//    private Example getQueryExample(Map<String, Object> params) {
//        // criteria.andEqualTo("title", params.get("title"));
//        // criteria.andEqualTo("status", params.get("status"));
//        // 直等查询
//        ArtTeacher queryObject = BeanUtil.getQueryObject(params, ArtTeacher.class);
//        if (params.get("orderBy") != null) {
//            queryObject.setOrderBy((String)params.get("orderBy"));
//        }
//        Example example = new Example(ArtTeacher.class);
//        Example.Criteria criteria = example.createCriteria();
//        if (params.get("orderBy") != null) {
//            String orderBy = (String) params.get("orderBy");
//            example.setOrderByClause(orderBy.replace("order by", ""));
//        }
//
//        criteria.andEqualTo("state", 1);
//        if (params.containsKey("name")) {
//            criteria.andEqualTo("name", params.get("name"));
//        }
//        if (params.containsKey("sex")) {
//            criteria.andEqualTo("sex", params.get("sex"));
//        }
//
//        return example;
//    }
//
}
