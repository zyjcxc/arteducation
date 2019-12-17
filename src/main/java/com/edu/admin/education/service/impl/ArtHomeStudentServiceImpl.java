package com.edu.admin.education.service.impl;

import com.edu.admin.education.command.ArtHomeStudentSaveCommand;
import com.edu.admin.education.command.ArtHomeStudentUpdateCommand;
import com.edu.admin.education.convert.ArtHomeStudentConverter;
import com.edu.admin.education.dao.ArtHomeStudentDao;
import com.edu.admin.education.dto.ArtHomeStudentDto;
import com.edu.admin.education.enums.ResultEnum;
import com.edu.admin.education.exception.HumanResourceException;
import com.edu.admin.education.model.ArtHomeStudent;
import com.edu.admin.education.service.IArtHomeStudentService;
import com.edu.admin.server.utils.BeanUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ArtHomeStudentServiceImpl implements IArtHomeStudentService {

    @Autowired
    private ArtHomeStudentDao artHomeStudentDao;
    
    @Override
    public ArtHomeStudentDto getById(Long id) {
        return ArtHomeStudentConverter.convertToArtHomeStudentDto(artHomeStudentDao.selectByPrimaryKey(id));
    }
    
    @Override
    public List<ArtHomeStudentDto> findRecommendList() {
        Map<String, Object> params = new HashMap<>();
        
        params.put("recommend", 1);
        params.put("orderBy", "sort asc");

        Example example = getQueryExample(params);

        List<ArtHomeStudent> list = artHomeStudentDao.selectByExample(example);

        return ArtHomeStudentConverter.convertToListArtHomeStudentDto(list);
    }

    @Override
    public ArtHomeStudentDto save(ArtHomeStudentSaveCommand command) {
        if (command.getRecommend() == 1) {
            Example example = new Example(ArtHomeStudent.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("recommend", 1);
            int count = artHomeStudentDao.selectCountByExample(example);
            if (count == 9) {
                throw new HumanResourceException(ResultEnum.STUDENT_SIZE_FULL_RECORD);
            }
        }
        ArtHomeStudent artHomeStudent = ArtHomeStudentConverter.convertToArtHomeStudent(command);
        artHomeStudentDao.insertSelective(artHomeStudent);
        return ArtHomeStudentConverter.convertToArtHomeStudentDto(artHomeStudent);
    }

    @Override
    public ArtHomeStudentDto update(ArtHomeStudentUpdateCommand command) {
        ArtHomeStudent oldData = artHomeStudentDao.selectByPrimaryKey(command.getId());
        if (oldData == null) {
            throw new HumanResourceException(ResultEnum.NO_FIND_DATA);
        }
        oldData = ArtHomeStudentConverter.convertToArtHomeStudent(command);
        oldData.setId(command.getId());
        artHomeStudentDao.updateByPrimaryKeySelective(oldData);
        return ArtHomeStudentConverter.convertToArtHomeStudentDto(oldData);
    }

    @Override
    public List<ArtHomeStudentDto> list(Map<String, Object> params, Integer offset, Integer limit) {
        PageHelper.offsetPage(offset, limit);

        Example example = getQueryExample(params);

        List<ArtHomeStudent> list = artHomeStudentDao.selectByExample(example);

        return ArtHomeStudentConverter.convertToListArtHomeStudentDto(list);
    }

    @Override
    public int count(Map<String, Object> params) {
        // 直等查询
        ArtHomeStudent queryObject = BeanUtil.getQueryObject(params, ArtHomeStudent.class);
        queryObject.setOrderBy(null);
        return artHomeStudentDao.selectCount(queryObject);
    }

    @Override
    public int delete(Long id) {
        return artHomeStudentDao.deleteByPrimaryKey(id);
    }

    @Override
    public List<ArtHomeStudentDto> findAll() {
        List<ArtHomeStudent> ArtHomeStudent = artHomeStudentDao.selectAll();
        return ArtHomeStudentConverter.convertToListArtHomeStudentDto(ArtHomeStudent);
    }

    /**
     * 单表QBC查询
     * @param params 查询参数
     */
    private Example getQueryExample(Map<String, Object> params) {
        // criteria.andEqualTo("title", params.get("title"));
        // criteria.andEqualTo("status", params.get("status"));
        // 直等查询
        ArtHomeStudent queryObject = BeanUtil.getQueryObject(params, ArtHomeStudent.class);
        if (params.get("orderBy") != null) {
            queryObject.setOrderBy((String)params.get("orderBy"));
        }
        Example example = new Example(ArtHomeStudent.class);
        Example.Criteria criteria = example.createCriteria();
        if (params.get("orderBy") != null) {
            String orderBy = (String) params.get("orderBy");
            example.setOrderByClause(orderBy.replace("order by", ""));
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
