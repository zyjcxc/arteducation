package com.edu.admin.education.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edu.admin.education.command.ArtHomeStudentSaveCommand;
import com.edu.admin.education.command.ArtHomeStudentUpdateCommand;
import com.edu.admin.education.convert.ArtHomeStudentConverter;
import com.edu.admin.education.dao.ArtHomeStudentMapper;
import com.edu.admin.education.dto.ArtHomeStudentDto;
import com.edu.admin.education.enums.ResultEnum;
import com.edu.admin.education.exception.HumanResourceException;
import com.edu.admin.education.model.ArtHomeStudent;
import com.edu.admin.education.service.IArtHomeStudentService;
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
public class ArtHomeStudentServiceImpl extends ServiceImpl<ArtHomeStudentMapper, ArtHomeStudent> implements IArtHomeStudentService {
   
    @Autowired
    private ArtHomeStudentMapper artHomeStudentMapper;
    
    @Override
    public ArtHomeStudentDto getById(Long id) {
        return ArtHomeStudentConverter.convertToArtHomeStudentDto(artHomeStudentMapper.selectById(id));
    }

    @Override
    public ArtHomeStudentDto save(ArtHomeStudentSaveCommand command) {
        if (command.getRecommend() == 1) {
            int count = artHomeStudentMapper.selectCount(
                    Wrappers.<ArtHomeStudent>lambdaQuery().eq(ArtHomeStudent::getRecommend, 1));
            if (count == 9) {
                throw new HumanResourceException(ResultEnum.STUDENT_SIZE_FULL_RECORD);
            }
        }
        ArtHomeStudent artHomeStudent = ArtHomeStudentConverter.convertToArtHomeStudent(command);
        artHomeStudentMapper.insert(artHomeStudent);
        return ArtHomeStudentConverter.convertToArtHomeStudentDto(artHomeStudent);
    }

    @Override
    public ArtHomeStudentDto update(ArtHomeStudentUpdateCommand command) {
        ArtHomeStudent oldData = artHomeStudentMapper.selectById(command.getId());
        if (oldData == null) {
            throw new HumanResourceException(ResultEnum.NO_FIND_DATA);
        }
        if (command.getRecommend() == 1) {
            int count = artHomeStudentMapper.selectCount(
                    Wrappers.<ArtHomeStudent>lambdaQuery().eq(ArtHomeStudent::getRecommend, 1)
            .ne(ArtHomeStudent::getId, oldData.getId())
            );
            if (count == 9) {
                throw new HumanResourceException(ResultEnum.STUDENT_SIZE_FULL_RECORD);
            }
        }
        oldData = ArtHomeStudentConverter.convertToArtHomeStudent(command);
        oldData.setId(command.getId());
        artHomeStudentMapper.updateById(oldData);
        return ArtHomeStudentConverter.convertToArtHomeStudentDto(oldData);
    }
    

    @Override
    public int delete(Long id) {
        return artHomeStudentMapper.deleteById(id);
    }

    @Override
    public List<ArtHomeStudentDto> findAll() {
        return ArtHomeStudentConverter.convertToListArtHomeStudentDto(artHomeStudentMapper.selectList(null));
    }

    @Override
    public List<ArtHomeStudentDto> findRecommendList() {
        List<ArtHomeStudent> artHomeStudents =
                artHomeStudentMapper.selectList(Wrappers.<ArtHomeStudent>lambdaQuery()
                        .eq(ArtHomeStudent::getRecommend, "1").orderByAsc(ArtHomeStudent::getSort));
        return ArtHomeStudentConverter.convertToListArtHomeStudentDto(artHomeStudents);
    }

    @Override
    public PageTableResponse queryList(PageTableRequest request) {
        Page<ArtHomeStudent> page = new Page<>(request.getCurrentPage(),request.getLimit());
        Page<ArtHomeStudent> result = artHomeStudentMapper.selectPage(page, makeQueryConditionWrapper(request));
        List<ArtHomeStudentDto> artHomeStudentDtos = ArtHomeStudentConverter.convertToListArtHomeStudentDto(result.getRecords());
        return new PageTableResponse((int)result.getTotal(), (int)result.getTotal(), artHomeStudentDtos);
    }

    private QueryWrapper<ArtHomeStudent> makeQueryConditionWrapper(PageTableRequest request) {
        OrderByObject orderByObject = request.getOrderByObject();
        QueryWrapper<ArtHomeStudent> query = Wrappers.query();
        Map<String, Object> params = request.getParams();
        query.eq(params.containsKey(ArtHomeStudent.Column.RECOMMEND.key()),
                ArtHomeStudent.Column.RECOMMEND.key(),
                params.get(ArtHomeStudent.Column.RECOMMEND.key()));
        query.like(params.containsKey(ArtHomeStudent.Column.NAME.key()),
                ArtHomeStudent.Column.NAME.key(),
                params.get(ArtHomeStudent.Column.NAME.key()));
        query.eq(params.containsKey(ArtHomeStudent.Column.ID.key()),
                ArtHomeStudent.Column.ID.key(),
                params.get(ArtHomeStudent.Column.ID.key()));
        if (orderByObject != null) {
            query.orderBy(orderByObject.isOrderBy(), orderByObject.isAsc(), orderByObject.getColumn(true));
        } else {
            query.orderBy(true, true, ArtHomeStudent.Column.ID.key());
        }
        return query;
    }
}
