package com.edu.admin.education.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edu.admin.education.dao.ArtActivityMapper;
import com.edu.admin.education.enums.ResultEnum;
import com.edu.admin.education.exception.HumanResourceException;
import com.edu.admin.education.model.ArtActivity;
import com.edu.admin.education.service.IArtActivityService;
import com.edu.admin.server.page.table.OrderByObject;
import com.edu.admin.server.page.table.PageTableRequest;
import com.edu.admin.server.page.table.PageTableResponse;
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
public class ArtActivityServiceImpl extends ServiceImpl<ArtActivityMapper, ArtActivity> implements IArtActivityService {

    @Autowired
    private ArtActivityMapper artActivityMapper;

    @Override
    public ArtActivity getById(Long id) {
        return artActivityMapper.selectById(id);
    }

    @Override
    public boolean save(ArtActivity artActivity) {
        ArtActivity oldData = getByActivityName(artActivity.getName());
        if (oldData != null) {
            throw new HumanResourceException(ResultEnum.REPEAT_RECORD);
        }
        artActivityMapper.insert(artActivity);
//        return artAcitivtyDao.insertSelective(artActivity);
        return true;
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
        return artActivityMapper.selectOne(Wrappers.<ArtActivity>lambdaQuery().eq(ArtActivity::getName, artActivityName));
    }

    @Override
    public int update(ArtActivity artAcitivty) {
//        return artAcitivtyDao.updateByPrimaryKeySelective(artAcitivty);
        artActivityMapper.updateById(artAcitivty);
        return 0;
    }

    @Override
    public PageTableResponse queryList(PageTableRequest request) {
        Page<ArtActivity> page = new Page<>(request.getCurrentPage(),request.getLimit());
        Page<ArtActivity> result = artActivityMapper.selectPage(page, makeQueryConditionWrapper(request));
//        List<ArtActivity> artAuthbookDtos = ArtAuthbookConverter.convertToListArtAuthbookDto(result.getRecords());
        return new PageTableResponse((int)result.getTotal(), (int)result.getTotal(), result.getRecords());
    }

    private QueryWrapper<ArtActivity> makeQueryConditionWrapper(PageTableRequest request) {
        OrderByObject orderByObject = request.getOrderByObject();
        QueryWrapper<ArtActivity> query = Wrappers.query();
        Map<String, Object> params = request.getParams();
        query.eq(ArtActivity.Column.STATE.key(), 1);
        query.eq(params.containsKey(ArtActivity.Column.ID.key()),
                ArtActivity.Column.ID.key(),
                params.get(ArtActivity.Column.ID.key()));
        query.eq(params.containsKey(ArtActivity.Column.STATUS.key()),
                ArtActivity.Column.STATUS.key(),
                params.get(ArtActivity.Column.STATUS.key()));
        query.eq(params.containsKey(ArtActivity.Column.TITLE.key()),
                ArtActivity.Column.TITLE.key(),
                params.get(ArtActivity.Column.TITLE.key()));
        if (orderByObject != null) {
            query.orderBy(orderByObject.isOrderBy(), orderByObject.isAsc(), orderByObject.getColumn(true));
        } else {
            query.orderBy(true, true, ArtActivity.Column.ID.key());
        }
        return query;
    }

//    @Override
//    public List<ArtActivity> list(Map<String, Object> params, Integer offset, Integer limit) {
////        PageHelper.offsetPage(offset, limit);
////        // 直等查询
//
//
//
////        List<ArtActivity> list = artAcitivtyDao.selectByExample(example);
////        // QBC查询
////        /*Example example = getQueryExample(params);
////        List<ArtAcitivty> list = artAcitivtyDao.selectByExample(example);*/
////        return list;
//        return null;
//    }

//    @Override
//    public int count(Map<String, Object> params) {
//        // 直等查询
////        ArtActivity queryObject = BeanUtil.getQueryObject(params, ArtActivity.class);
////        queryObject.setOrderBy(null);
////        int count = artAcitivtyDao.selectCount(queryObject);
////        // QBC查询
////        /*Example example = getQueryExample(params);
////        int count = artAcitivtyDao.selectCountByExample(example);*/
////
////        return count;
//        return 0;
//    }

    @Override
    public int delete(Long id) {
//        return artAcitivtyDao.deleteByPrimaryKey(id);
        artActivityMapper.deleteById(id);
        return 0;
    }

    @Override
    public List<ArtActivity> findAll() {
//        Example example = getQueryExample(new HashMap<>(1));
//        return artAcitivtyDao.selectByExample(example);
        return artActivityMapper.selectList(null);
    }

//    /**
//     * 单表QBC查询
//     * @param params 查询参数
//     */
//    private Example getQueryExample(Map<String, Object> params) {
////        Example example = new Example(ArtActivity.class);
////        Example.Criteria criteria = example.createCriteria();
////        // criteria.andEqualTo("title", params.get("title"));
////        // criteria.andEqualTo("status", params.get("status"));
////        if (params.get("orderBy") != null) {
////            String orderBy = (String) params.get("orderBy");
////            example.setOrderByClause(orderBy.replace("order by", ""));
////        }
////        return example;
//        return null;
//    }
}
