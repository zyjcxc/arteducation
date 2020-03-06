package com.edu.admin.education.service.impl;

import com.edu.admin.education.dao.ArtAcitivtyDao;
import com.edu.admin.education.enums.ResultEnum;
import com.edu.admin.education.exception.HumanResourceException;
import com.edu.admin.education.model.ArtActivity;
import com.edu.admin.education.service.IArtActivityService;
import io.swagger.annotations.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

//import tk.mybatis.mapper.entity.Example;
;

/**
 * 活动 模块业务接口具体实现
 * @author mengqa
 * @date 2018-11-21
 **/
@Service
@Transactional
public class ArtActivityServiceImpl implements IArtActivityService {

    @Autowired
    private ArtAcitivtyDao artAcitivtyDao;

    @Override
    public ArtActivity getById(Long id) {
//        return artAcitivtyDao.selectByPrimaryKey(id);
        return null;
    }

    @Override
    public int save(ArtActivity artActivity) {
        ArtActivity oldData = getByActivityName(artActivity.getName());
        if (oldData != null) {
            throw new HumanResourceException(ResultEnum.REPEAT_RECORD);
        }
//        return artAcitivtyDao.insertSelective(artActivity);
        return 0;
    }

    @Override
    public ArtActivity getByActivityName(String artActivityName) {
//        Example example = new Example(ArtActivity.class);
//        Example.Criteria criteria = example.createCriteria();
//        criteria.andEqualTo("name", artActivityName);
//        List<ArtActivity> list = artAcitivtyDao.selectByExample(example);
//        if (CollectionUtils.isEmpty(list)) {
//            return null;
//        }
//        return list.get(0);
        return null;
    }

    @Override
    public int update(ArtActivity artAcitivty) {
//        return artAcitivtyDao.updateByPrimaryKeySelective(artAcitivty);
        return 0;
    }

    @Override
    public List<ArtActivity> list(Map<String, Object> params, Integer offset, Integer limit) {
//        PageHelper.offsetPage(offset, limit);
//        // 直等查询
//        ArtActivity queryObject = BeanUtil.getQueryObject(params, ArtActivity.class);
//        if (params.get("orderBy") != null) {
//            queryObject.setOrderBy((String)params.get("orderBy"));
//        }
//        Example example = new Example(ArtActivity.class);
//        Example.Criteria criteria = example.createCriteria();
//         criteria.andEqualTo("state", 1);
//        // criteria.andEqualTo("status", params.get("status"));
//        if (params.get("orderBy") != null) {
//            String orderBy = (String) params.get("orderBy");
//            example.setOrderByClause(orderBy.replace("order by", ""));
//        }
//        List<ArtActivity> list = artAcitivtyDao.selectByExample(example);
//        // QBC查询
//        /*Example example = getQueryExample(params);
//        List<ArtAcitivty> list = artAcitivtyDao.selectByExample(example);*/
//        return list;
        return null;
    }

    @Override
    public int count(Map<String, Object> params) {
        // 直等查询
//        ArtActivity queryObject = BeanUtil.getQueryObject(params, ArtActivity.class);
//        queryObject.setOrderBy(null);
//        int count = artAcitivtyDao.selectCount(queryObject);
//        // QBC查询
//        /*Example example = getQueryExample(params);
//        int count = artAcitivtyDao.selectCountByExample(example);*/
//
//        return count;
        return 0;
    }

    @Override
    public int delete(Long id) {
//        return artAcitivtyDao.deleteByPrimaryKey(id);
        return 0;
    }

    @Override
    public List<ArtActivity> findAll() {
//        Example example = getQueryExample(new HashMap<>(1));
//        return artAcitivtyDao.selectByExample(example);
        return null;
    }

    /**
     * 单表QBC查询
     * @param params 查询参数
     */
    private Example getQueryExample(Map<String, Object> params) {
//        Example example = new Example(ArtActivity.class);
//        Example.Criteria criteria = example.createCriteria();
//        // criteria.andEqualTo("title", params.get("title"));
//        // criteria.andEqualTo("status", params.get("status"));
//        if (params.get("orderBy") != null) {
//            String orderBy = (String) params.get("orderBy");
//            example.setOrderByClause(orderBy.replace("order by", ""));
//        }
//        return example;
        return null;
    }
}
