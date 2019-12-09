package com.edu.admin.education.service.impl;

import com.edu.admin.education.command.ArtTextbookSaveCommand;
import com.edu.admin.education.command.ArtTextbookUpdateCommand;
import com.edu.admin.education.convert.ArtTextbookConverter;
import com.edu.admin.education.dao.ArtTextbookDao;
import com.edu.admin.education.dao.ArtTextbookTypeDao;
import com.edu.admin.education.dto.ArtTextbookDto;
import com.edu.admin.education.enums.ResultEnum;
import com.edu.admin.education.exception.HumanResourceException;
import com.edu.admin.education.model.ArtTextbook;
import com.edu.admin.education.model.ArtTextbookType;
import com.edu.admin.education.service.IArtTextbookService;
import com.edu.admin.server.utils.BeanUtil;
import com.edu.admin.server.utils.UserUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ArtTextbookServiceImpl implements IArtTextbookService {

    @Autowired
    private ArtTextbookDao artTextbookDao;

    @Autowired
    private ArtTextbookTypeDao artTextbookTypeDao;

    @Override
    public ArtTextbookDto getById(Long id) {
        return ArtTextbookConverter.convertToArtTextbookDto(artTextbookDao.selectByPrimaryKey(id));
    }

    @Override
    public ArtTextbookDto save(ArtTextbookSaveCommand command) {
        ArtTextbook artTextbook = ArtTextbookConverter.convertToArtTextbook(command);
        artTextbook.setState("1");
        artTextbook.setCreateUserId(UserUtil.getCurrentUser().getId());
        artTextbookDao.insertSelective(artTextbook);
        return ArtTextbookConverter.convertToArtTextbookDto(artTextbook);
    }

    @Override
    public ArtTextbookDto update(ArtTextbookUpdateCommand command) {
        ArtTextbook oldData = artTextbookDao.selectByPrimaryKey(command.getId());
        if (oldData == null) {
            throw new HumanResourceException(ResultEnum.NO_FIND_DATA);
        }
        oldData = ArtTextbookConverter.convertToArtTextbook(command);
        oldData.setId(command.getId());
        artTextbookDao.updateByPrimaryKeySelective(oldData);
        return ArtTextbookConverter.convertToArtTextbookDto(oldData);
    }

    @Override
    public List<ArtTextbookDto> list(Map<String, Object> params, Integer offset, Integer limit) {
        PageHelper.offsetPage(offset, limit);

        Example example = getQueryExample(params);

        List<ArtTextbook> list = artTextbookDao.selectByExample(example);
        list.forEach(d -> {
            ArtTextbookType artTextbookType = artTextbookTypeDao.selectByPrimaryKey(d.getTextbookTypeId());
            if (artTextbookType != null) {
                d.setTextBookName(artTextbookType.getName());
            }
        });


        return ArtTextbookConverter.convertToListArtTextbookDto(list);
    }

    @Override
    public int count(Map<String, Object> params) {
        // 直等查询
        ArtTextbook queryObject = BeanUtil.getQueryObject(params, ArtTextbook.class);
        queryObject.setOrderBy(null);
        return artTextbookDao.selectCount(queryObject);
    }

    @Override
    public int delete(Long id) {
        return artTextbookDao.deleteByPrimaryKey(id);
    }

    @Override
    public List<ArtTextbookDto> findAll() {
        List<ArtTextbook> ArtTextbook = artTextbookDao.selectAll();
        return ArtTextbookConverter.convertToListArtTextbookDto(ArtTextbook);
    }

    /**
     * 单表QBC查询
     * @param params 查询参数
     */
    private Example getQueryExample(Map<String, Object> params) {
        // criteria.andEqualTo("title", params.get("title"));
        // criteria.andEqualTo("status", params.get("status"));
        // 直等查询
        ArtTextbook queryObject = BeanUtil.getQueryObject(params, ArtTextbook.class);
        if (params.get("orderBy") != null) {
            queryObject.setOrderBy((String)params.get("orderBy"));
        }
        Example example = new Example(ArtTextbook.class);
        Example.Criteria criteria = example.createCriteria();
        if (params.get("orderBy") != null) {
            String orderBy = (String) params.get("orderBy");
            example.setOrderByClause(orderBy.replace("order by", ""));
        }

        criteria.andEqualTo("state", 1);
        if (params.containsKey("title")) {
            criteria.andEqualTo("title", params.get("title"));
        }
        if (params.containsKey("author")) {
            criteria.andEqualTo("author", params.get("author"));
        }
        if (params.containsKey("version")) {
            criteria.andEqualTo("version", params.get("version"));
        }
        if (params.containsKey("textbookTypeId")) {
            criteria.andEqualTo("textbookTypeId", params.get("textbookTypeId"));
        }

        return example;
    }

}
