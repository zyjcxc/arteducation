package com.edu.admin.education.service.impl;

import com.edu.admin.education.command.ArtFileListSaveCommand;
import com.edu.admin.education.command.ArtFileListUpdateCommand;
import com.edu.admin.education.convert.ArtFileListConverter;
import com.edu.admin.education.dao.ArtFileListDao;
import com.edu.admin.education.dto.ArtFileListDto;
import com.edu.admin.education.enums.ResultEnum;
import com.edu.admin.education.exception.HumanResourceException;
import com.edu.admin.education.model.ArtFileList;
import com.edu.admin.education.service.IArtFileListService;
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
public class ArtFileListServiceImpl implements IArtFileListService {

    @Autowired
    private ArtFileListDao artFileListDao;
    
    @Override
    public ArtFileListDto getById(Long id) {
        return ArtFileListConverter.convertToArtFileListDto(artFileListDao.selectByPrimaryKey(id));
    }

    @Override
    public ArtFileListDto save(ArtFileListSaveCommand command) {
        ArtFileList artFileList = ArtFileListConverter.convertToArtFileList(command);
        artFileListDao.insertSelective(artFileList);
        return ArtFileListConverter.convertToArtFileListDto(artFileList);
    }

    @Override
    public ArtFileListDto update(ArtFileListUpdateCommand command) {
        ArtFileList oldData = artFileListDao.selectByPrimaryKey(command.getId());
        if (oldData == null) {
            throw new HumanResourceException(ResultEnum.NO_FIND_DATA);
        }
        oldData = ArtFileListConverter.convertToArtFileList(command);
        oldData.setId(command.getId());
        artFileListDao.updateByPrimaryKeySelective(oldData);
        return ArtFileListConverter.convertToArtFileListDto(oldData);
    }

    @Override
    public List<ArtFileListDto> list(Map<String, Object> params, Integer offset, Integer limit) {
        PageHelper.offsetPage(offset, limit);

        Example example = getQueryExample(params);

        List<ArtFileList> list = artFileListDao.selectByExample(example);

        return ArtFileListConverter.convertToListArtFileListDto(list);
    }

    /**
     * 单表QBC查询
     * @param params 查询参数
     */
    private Example getQueryExample(Map<String, Object> params) {
        // 直等查询
        Example example = new Example(ArtFileList.class);
        Example.Criteria criteria = example.createCriteria();
        if (params.containsKey("id")) {
            criteria.andEqualTo("id", params.get("id"));
        }
        if (params.containsKey("title")) {
            criteria.andEqualTo("title", params.get("title"));
        }
        if (params.get("orderBy") != null) {
            String orderBy = (String) params.get("orderBy");
            example.setOrderByClause(orderBy.replace("order by", ""));
        }
        return example;
    }

    @Override
    public int count(Map<String, Object> params) {
        // 直等查询
        ArtFileList queryObject = BeanUtil.getQueryObject(params, ArtFileList.class);
        queryObject.setOrderBy(null);
        return artFileListDao.selectCount(queryObject);
    }

    @Override
    public int delete(Long id) {
        return artFileListDao.deleteByPrimaryKey(id);
    }

    @Override
    public List<ArtFileListDto> findAll() {
        List<ArtFileList> ArtFileList = artFileListDao.selectAll();
        return ArtFileListConverter.convertToListArtFileListDto(ArtFileList);
    }

}
