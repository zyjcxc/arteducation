package com.edu.admin.education.service.impl;

import com.edu.admin.education.dao.ArtStudentDao;
import com.edu.admin.education.enums.PublicState;
import com.edu.admin.education.enums.ResultEnum;
import com.edu.admin.education.exception.HumanResourceException;
import com.edu.admin.education.model.ArtActivity;
import com.edu.admin.education.model.ArtStudent;
import com.edu.admin.education.model.ArtStudentImportInfoDto;
import com.edu.admin.education.model.LiveCourseClassification;
import com.edu.admin.education.service.IArtActivityService;
import com.edu.admin.education.service.IArtStudentService;
import com.edu.admin.education.service.ILiveCourseClassificationService;
import com.edu.admin.education.utils.DateUtil;
import com.edu.admin.education.utils.PinyinTool;
import com.github.pagehelper.PageHelper;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
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

    @Autowired
    private IArtActivityService artActivityService;

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
        // 查询学生是否重复录入
        ArtStudent oldData = getByActivityAndCarNo(artStudent.getActivityId(), artStudent.getCardNo(), artStudent.getClassificationId(), artStudent.getLevel());
        if (oldData != null) {
            throw new HumanResourceException(ResultEnum.REPEAT_STUDENT_RECORD);
        }
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
        if (artStudent.getBookType() == null || artStudent.getBookType() == 0) {
            // 插入两条数据
            artStudent.setBookType(1);
            artStudentDao.insertSelective(artStudent);
            artStudent.setBookType(2);
            artStudent.setId(null);
            artStudentDao.insertSelective(artStudent);
        } else {
            // 插入一条数据
            artStudentDao.insertSelective(artStudent);
        }
    }

    private ArtStudent getByActivityAndCarNo(Integer activityId, String cardNo, Integer classificationId, String level) {
        Example example = new Example(ArtStudent.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("cardNo", cardNo);
        criteria.andEqualTo("activityId", activityId);
        criteria.andEqualTo("classificationId", classificationId);
        criteria.andEqualTo("level", level);
        List<ArtStudent> list = artStudentDao.selectByExample(example);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public void update(ArtStudent artStudent) {
        artStudentDao.updateByPrimaryKeySelective(artStudent);
    }

    @Override
    public void saveDatas(List<ArtStudentImportInfoDto> dtoList) {
        for (ArtStudentImportInfoDto dto : dtoList) {
            ArtStudent artStudent = new ArtStudent();
            artStudent.setName(dto.getName());
            artStudent.setNamePy(dto.getNamePy());
            artStudent.setState(String.valueOf(PublicState.NORMAL.getCode()));
            if (dto.getBorn() != null) {
                artStudent.setBorn(DateUtil.parseDate(dto.getBorn(), "yyyy.MM.dd"));
            }
            artStudent.setCardNo(dto.getCardNo());
            artStudent.setCountry(dto.getCountry());
            artStudent.setLevel(getChineseLevel(dto.getLevel()));
            artStudent.setNation(dto.getNation());
            artStudent.setSex("女".equals(dto.getSex()) ? "g" : "m");
            artStudent.setCreatetime(new Date());

            // 查询活动是否存在
            if (StringUtils.isEmpty(dto.getActivityName())) {
                throw new HumanResourceException(ResultEnum.NO_ACTIVITY_RECORD);
            }
            ArtActivity activity = artActivityService.getByActivityName(dto.getActivityName());
            if (activity == null) {
                // 活动不存在，新建活动
                activity = new ArtActivity();
                activity.setName(dto.getActivityName());
                activity.setCreatetime(new Date());
                activity.setUpdatetime(new Date());
                activity.setState("1");
                artActivityService.save(activity);
            }
            artStudent.setActivityId(activity.getId().intValue());
            if (StringUtils.isEmpty(dto.getProjectName())) {
                throw new HumanResourceException(ResultEnum.NO_TYPE_RECORD);
            }
            // 查询项目是否存在
            LiveCourseClassification project = liveCourseClassificationService.getByName(dto.getProjectName());
            if (project == null) {
                // 不存在，新建专业
                project = new LiveCourseClassification();
                project.setName(dto.getProjectName());
                project.setCreatetime(new Date());
                project.setState("1");
                liveCourseClassificationService.save(project);
            }
            artStudent.setClassificationId(project.getId().intValue());

            // 查询学生是否重复导入
            ArtStudent oldStudent = getByActivityAndCarNo(artStudent.getActivityId(), artStudent.getCardNo(), artStudent.getClassificationId(), artStudent.getLevel());
            if (oldStudent != null) {
                continue;
            }

            if (StringUtils.isEmpty(dto.getBookNo())) {
                throw new HumanResourceException(ResultEnum.NO_BOOK_NO_RECORD);
            }
            artStudent.setBookNo(dto.getBookNo());

            if (dto.getBookType() == null || dto.getBookType() == 0) {
                artStudent.setBookType(1);
                artStudentDao.insertSelective(artStudent);
                artStudent.setBookType(2);
                artStudentDao.insertSelective(artStudent);
            } else {
                artStudent.setBookType(dto.getBookType());
                artStudentDao.insertSelective(artStudent);
            }

        }
    }

    @Override
    public ArtStudent getByCondition(Map<String, Object> params) {
        Example example = new Example(ArtStudent.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("cardNo", params.get("cardNo"));
        criteria.andEqualTo("name", params.get("name"));
        criteria.andEqualTo("classificationId", params.get("classificationId"));
        criteria.andEqualTo("level", params.get("level"));
        criteria.andEqualTo("bookType", params.get("bookType"));
        List<ArtStudent> list = artStudentDao.selectByExample(example);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        ArtStudent artStudent = list.get(0);
        LiveCourseClassification byId = liveCourseClassificationService.getById(artStudent.getClassificationId()
                .longValue());
        artStudent.setClassificationName(byId.getName());
        return artStudent;
    }

    private String getChineseLevel(String level) {
        switch (level) {
            case "1":
                return "一级";
            case "2":
                return "二级";
            case "3":
                return "三级";
            case "4":
                return "四级";
            case "5":
                return "五级";
            case "6":
                return "六级";
            case "7":
                return "七级";
            case "8":
                return "八级";
            case "9":
                return "九级";
            case "10":
                return "十级";
        }
        return level;
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

    @Override
    public void deleteLogicBatch(List<String> ids) {
        ids.forEach(id -> {
            ArtStudent artStudent = new ArtStudent();
            artStudent.setState("2");
            artStudent.setId(Long.parseLong(id));
            artStudentDao.updateByPrimaryKeySelective(artStudent);
        });
    }


}
