package com.edu.admin.education.service.impl;

import com.edu.admin.education.command.ArtTeacherSaveCommand;
import com.edu.admin.education.command.ArtTeacherUpdateCommand;
import com.edu.admin.education.convert.ArtTeacherConverter;
import com.edu.admin.education.dao.ArtTeacherDao;
import com.edu.admin.education.dto.ArtTeacherDto;
import com.edu.admin.education.enums.ResultEnum;
import com.edu.admin.education.exception.HumanResourceException;
import com.edu.admin.education.model.ArtTeacher;
import com.edu.admin.education.service.IArtTeacherService;
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
public class ArtTeacherServiceImpl implements IArtTeacherService {

    @Autowired
    private ArtTeacherDao artTeacherDao;

    @Override
    public ArtTeacherDto getById(Long id) {
        return ArtTeacherConverter.convertToArtTeacherDto(artTeacherDao.selectByPrimaryKey(id));
    }

    @Override
    public ArtTeacherDto save(ArtTeacherSaveCommand command) {
        ArtTeacher artTeacher = ArtTeacherConverter.convertToArtTeacher(command);
        artTeacher.setState("1");
        artTeacherDao.insertSelective(artTeacher);
        return ArtTeacherConverter.convertToArtTeacherDto(artTeacher);
    }

    @Override
    public ArtTeacherDto update(ArtTeacherUpdateCommand command) {
        ArtTeacher oldData = artTeacherDao.selectByPrimaryKey(command.getId());
        if (oldData == null) {
            throw new HumanResourceException(ResultEnum.NO_FIND_DATA);
        }
        oldData = ArtTeacherConverter.convertToArtTeacher(command);
        oldData.setId(command.getId());
        artTeacherDao.updateByPrimaryKeySelective(oldData);
        return ArtTeacherConverter.convertToArtTeacherDto(oldData);
    }

    @Override
    public List<ArtTeacherDto> list(Map<String, Object> params, Integer offset, Integer limit) {
        PageHelper.startPage(offset, limit);

        Example example = getQueryExample(params);

        List<ArtTeacher> list = artTeacherDao.selectByExample(example);

        return ArtTeacherConverter.convertToListArtTeacherDto(list);
    }

    @Override
    public int count(Map<String, Object> params) {
        // 直等查询
        ArtTeacher queryObject = BeanUtil.getQueryObject(params, ArtTeacher.class);
        queryObject.setOrderBy(null);
        return artTeacherDao.selectCount(queryObject);
    }

    @Override
    public int delete(Long id) {
        return artTeacherDao.deleteByPrimaryKey(id);
    }

    @Override
    public List<ArtTeacherDto> findAll() {
        List<ArtTeacher> ArtTeacher = artTeacherDao.selectAll();
        return ArtTeacherConverter.convertToListArtTeacherDto(ArtTeacher);
    }

    /**
     * 单表QBC查询
     * @param params 查询参数
     */
    private Example getQueryExample(Map<String, Object> params) {
        // criteria.andEqualTo("title", params.get("title"));
        // criteria.andEqualTo("status", params.get("status"));
        // 直等查询
        ArtTeacher queryObject = BeanUtil.getQueryObject(params, ArtTeacher.class);
        if (params.get("orderBy") != null) {
            queryObject.setOrderBy((String)params.get("orderBy"));
        }
        Example example = new Example(ArtTeacher.class);
        Example.Criteria criteria = example.createCriteria();
        if (params.get("orderBy") != null) {
            String orderBy = (String) params.get("orderBy");
            example.setOrderByClause(orderBy.replace("order by", ""));
        }

        criteria.andEqualTo("state", 1);
        if (params.containsKey("name")) {
            criteria.andEqualTo("name", params.get("name"));
        }
        if (params.containsKey("sex")) {
            criteria.andEqualTo("sex", params.get("sex"));
        }

        return example;
    }

}
