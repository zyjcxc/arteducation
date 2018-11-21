package com.edu.admin.education.service.impl;

import com.edu.admin.education.dao.ArtStudentDao;
import com.edu.admin.education.enums.PublicState;
import com.edu.admin.education.model.ArtStudent;
import com.edu.admin.education.model.LiveCourseClassification;
import com.edu.admin.education.service.IArtStudentService;
import com.edu.admin.education.service.ILiveCourseClassificationService;
import com.edu.admin.education.utils.PinyinTool;
import com.github.pagehelper.PageHelper;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


/**
 * 特长生 模块业务接口具体实现
 * @author mengqa
 * @date 2018-04-03
 **/
@Service
@Transactional
public class ArtStudentServiceImpl implements IArtStudentService{

    @Autowired
    private ArtStudentDao artStudentDao;

    @Autowired
    private ILiveCourseClassificationService liveCourseClassificationService;

    @Override
    public ArtStudent getById(Long id) {
        ArtStudent obj = artStudentDao.selectByPrimaryKey(id);
        if (obj != null) {
            LiveCourseClassification classObj
                    = liveCourseClassificationService.getById((long) obj.getClassificationId());
            if (classObj != null) {
                obj.setClassificationName(classObj.getName());
            }
        }
        return obj;
    }

    @Override
    public void save(ArtStudent artStudent) {
        // 新增时默认为正常状态
        artStudent.setState(String.valueOf(PublicState.NORMAL.getCode()));
        PinyinTool tool = new PinyinTool();
        try {
            String py = tool.toPinYin(artStudent.getName(), " ", PinyinTool.Type.UPPERCASE);
            artStudent.setNamePy(py);
        } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
            badHanyuPinyinOutputFormatCombination.printStackTrace();
            artStudent.setNamePy("");
        }
        artStudentDao.insertSelective(artStudent);
    }

    @Override
    public void update(ArtStudent artStudent) {
        artStudentDao.updateByPrimaryKeySelective(artStudent);
    }

    @Override
    public List<ArtStudent> list(Map<String, Object> params, Integer offset, Integer limit) {
        if (offset != null) {
            PageHelper.offsetPage(offset, limit);
        }
        // QBC查询 sql改了，暂时不用了
//        Example example = getQueryExample(params);
//        List<LiveCourseInfo> list = liveCourseInfoDao.selectByExample(example);
        return artStudentDao.selectByCustomSql(params);
    }

    public static enum Type {
        UPPERCASE,              //全部大写
        LOWERCASE,              //全部小写
        FIRSTUPPER              //首字母大写
    }

    @Override
    public int count(Map<String, Object> params) {
        // 查中间表 sql改了，暂时不用了
        /*List<Long> ids = getIds(params);
        if (CollectionUtils.isEmpty(ids)) {
            return 0;
        }
        params.put("ids", ids);
        Example example = getQueryExample(params);*/
//        int count = liveCourseInfoDao.selectCountByExample(example);
        int count = 0;
        String orderBy;
        if (params != null && params.containsKey("orderBy")) {
            orderBy = (String) params.get("orderBy");
            params.remove("orderBy");
            count = artStudentDao.countByCustomSql(params);
            params.put("orderBy", orderBy);
        }
        return count;

    }

    @Override
    public int deleteLogic(ArtStudent artStudent) {
        return artStudentDao.updateByPrimaryKeySelective(artStudent);
    }


}
