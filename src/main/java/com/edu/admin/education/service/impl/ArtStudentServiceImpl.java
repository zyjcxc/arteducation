package com.edu.admin.education.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edu.admin.education.dao.ArtStudentMapper;
import com.edu.admin.education.enums.PublicState;
import com.edu.admin.education.enums.ResultEnum;
import com.edu.admin.education.exception.HumanResourceException;
import com.edu.admin.education.model.ArtSchool;
import com.edu.admin.education.model.ArtStudent;
import com.edu.admin.education.model.ArtStudentImportInfoDto;
import com.edu.admin.education.model.LiveCourseClassification;
import com.edu.admin.education.service.IArtActivityService;
import com.edu.admin.education.service.IArtSchoolService;
import com.edu.admin.education.service.IArtStudentService;
import com.edu.admin.education.service.ILiveCourseClassificationService;
import com.edu.admin.education.utils.PinyinTool;
import com.edu.admin.server.page.table.OrderByObject;
import com.edu.admin.server.page.table.PageTableRequest;
import com.edu.admin.server.page.table.PageTableResponse;
import com.edu.admin.server.utils.StrUtil;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

//
//import com.edu.admin.education.dao.ArtStudentDao;
//import com.edu.admin.education.enums.PublicState;
//import com.edu.admin.education.enums.ResultEnum;
//import com.edu.admin.education.exception.HumanResourceException;
//import com.edu.admin.education.model.*;
//import com.edu.admin.education.service.IArtActivityService;
//import com.edu.admin.education.service.IArtSchoolService;
//import com.edu.admin.education.service.IArtStudentService;
//import com.edu.admin.education.service.ILiveCourseClassificationService;
//import com.edu.admin.education.utils.DateUtil;
//import com.edu.admin.education.utils.PinyinTool;
//import com.github.pagehelper.PageHelper;
//import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.util.CollectionUtils;
//import tk.mybatis.mapper.entity.Example;
//
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//
//
///**
// * 特长生 模块业务接口具体实现
// * @author mengqa
// * @date 2018-04-03
// **/
@Service
@Transactional
public class ArtStudentServiceImpl extends ServiceImpl<ArtStudentMapper, ArtStudent> implements IArtStudentService {

    @Autowired
    private ArtStudentMapper artStudentMapper;

    @Autowired
    private IArtSchoolService artSchoolService;

    @Autowired
    private ILiveCourseClassificationService liveCourseClassificationService;

    @Autowired
    private IArtActivityService artActivityService;

//    @Override
//    public List<ArtStudent> list(Map<String, Object> params, Integer offset, Integer limit) {
//                if (offset != null) {
//            PageHelper.offsetPage(offset, limit);
//        }
//        // QBC查询 sql改了，暂时不用了
////        Example example = getQueryExample(params);
////        List<LiveCourseInfo> list = liveCourseInfoDao.selectByExample(example);
//        return artStudentDao.selectByCustomSql(params);
//    }

    @Override
    public PageTableResponse queryList(PageTableRequest request) {
        Page<ArtStudent> page = new Page<>(request.getCurrentPage(),request.getLimit());
        IPage<ArtStudent> result = artStudentMapper.selectByCustomSql(page, makeQueryConditionWrapper(request));
//        List<ArtStudentlDto> artStudentDtos = ArtStudentConverter.convertToListArtStudentDto(result.getRecords());
        return new PageTableResponse((int)result.getTotal(), (int)result.getTotal(), result.getRecords());
    }

    @Override
    public List<ArtStudent> list(PageTableRequest request) {
//        request.setLimit(9999999);
//        Page<ArtStudent> page = new Page<>(request.getCurrentPage(),request.getLimit());
//        page.setSize(9999999);
        return artStudentMapper.selectByCustomSql(makeQueryConditionWrapper(request));
    }


    private QueryWrapper<ArtStudent> makeQueryConditionWrapper(PageTableRequest request) {
        OrderByObject orderByObject = request.getOrderByObject();
        QueryWrapper<ArtStudent> query = Wrappers.query();
        Map<String, Object> params = request.getParams();
        query.eq(params.containsKey(ArtStudent.Column.NAME.key()),
                "artStudentObj." + ArtStudent.Column.NAME.key(),
                params.get(ArtStudent.Column.NAME.key()));
        query.eq(params.containsKey(ArtStudent.Column.ID.key()),
                "artStudentObj." + ArtStudent.Column.ID.key(),
                params.get(ArtStudent.Column.ID.key()));
        query.eq(params.containsKey(ArtStudent.Column.CARD_NO.key()),
                "artStudentObj." + StrUtil.humpToUnderline(ArtStudent.Column.CARD_NO.key()),
                params.get(ArtStudent.Column.CARD_NO.key()));
        query.eq(params.containsKey(ArtStudent.Column.LEVEL.key()),
                "artStudentObj." + ArtStudent.Column.LEVEL.key(),
                params.get(ArtStudent.Column.LEVEL.key()));
        query.eq(params.containsKey(ArtStudent.Column.BOOK_TYPE.key()),
                "artStudentObj." + StrUtil.humpToUnderline(ArtStudent.Column.BOOK_TYPE.key()),
                params.get(ArtStudent.Column.BOOK_TYPE.key()));
        query.eq(params.containsKey(ArtStudent.Column.BOOK_NO.key()),
                "artStudentObj." + StrUtil.humpToUnderline(ArtStudent.Column.BOOK_NO.key()),
                params.get(ArtStudent.Column.BOOK_NO.key()));
        query.eq(params.containsKey(ArtStudent.Column.SEX.key()),
                "artStudentObj." + ArtStudent.Column.SEX.key(),
                params.get(ArtStudent.Column.SEX.key()));

        query.eq(params.containsKey(ArtStudent.Column.ACTIVITY_ID.key()),
                "activityObj." + ArtStudent.Column.ID.key(),
                params.get(ArtStudent.Column.ACTIVITY_ID.key()));
        query.eq(params.containsKey(ArtStudent.Column.CLASSIFICATION_ID.key()),
                "classificationObj." + ArtStudent.Column.ID.key(),
                params.get(ArtStudent.Column.CLASSIFICATION_ID.key()));
        query.eq(params.containsKey(ArtStudent.Column.SCHOOL.key()),
                "schoolObj." + ArtStudent.Column.NAME.key(),
                params.get(ArtStudent.Column.SCHOOL.key()));

//        query.eq(params.containsKey(ArtStudent.Column.SC.key()),
//                "schoolObj.name",
//                params.get(ArtStudent.Column.CLASSIFICATION_ID.key()));

        query.eq("artStudentObj." + ArtStudent.Column.STATE.key(),
                1);
        if (orderByObject != null) {
            String column = orderByObject.getColumn(true);
            if (column.equalsIgnoreCase("CLASSIFICATION_NAME")) {
                column = "classificationObj.name";
            } else if (column.equalsIgnoreCase("ACTIVITY_NAME")) {
                column = "activityObj.name";
            } else if (column.equalsIgnoreCase("SCHOOL_NAME")) {
                column = "schoolObj.name";
            }
            query.orderBy(orderByObject.isOrderBy(), orderByObject.isAsc(), column);
        } else {
            query.orderBy(true, true, ArtStudent.Column.ID.key());
        }
        return query;
    }

    @Override
    public int deleteLogic(ArtStudent artStudent) {
        artStudentMapper.deleteById(artStudent);
        return 0;
    }

    @Override
    public void deleteLogicBatch(List<String> ids) {
        ids.forEach(id -> artStudentMapper.deleteById(id));
    }

    @Override
    public boolean save(ArtStudent artStudent) {
        // 查询学生是否重复录入
        ArtStudent oldData = getByActivityAndCarNo(artStudent.getActivityId(), artStudent.getCardNo(), artStudent.getClassificationId(), artStudent.getLevel(), artStudent.getBookType());
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

        if (isNumericZidai(artStudent.getLevel())) {
            artStudent.setLevel(getChineseLevel(artStudent.getLevel()));
        }

        makeSchoolData(artStudent);

        if (artStudent.getBookType() == null || artStudent.getBookType() == 0) {
            // 插入两条数据
            artStudent.setBookType(1);
            artStudentMapper.insert(artStudent);
            artStudent.setBookType(2);
            artStudent.setId(null);
            artStudentMapper.insert(artStudent);
        } else {
            // 插入一条数据
            artStudentMapper.insert(artStudent);
        }
        return true;
    }

    @Override
    public ArtStudent getById(Long id) {
        ArtStudent obj = artStudentMapper.selectById(id);
        if (obj != null) {
            LiveCourseClassification classObj
                    = liveCourseClassificationService.getById((long) obj.getClassificationId());
            if (classObj != null) {
                obj.setClassificationName(classObj.getName());
            }
            ArtSchool schoolObj
                    = artSchoolService.getById(obj.getSchoolId());
            if (schoolObj != null) {
                obj.setSchoolName(schoolObj.getName());
            }
        }
        return obj;
    }

    @Override
    public void update(ArtStudent artStudent) {
        if (artStudent.getBookType() == null || artStudent.getBookType() == 0) {
            throw new HumanResourceException(ResultEnum.BOOK_TYPE_NULL_RECORD);
        }
        makeSchoolData(artStudent);
        ArtStudent oldData = getByActivityAndCarNo(artStudent.getActivityId(), artStudent.getCardNo(), artStudent.getClassificationId(), artStudent.getLevel(), artStudent.getBookType());
        if (oldData != null && !oldData.getId().equals(artStudent.getId())) {
            throw new HumanResourceException(ResultEnum.REPEAT_STUDENT_RECORD);
        }
        artStudentMapper.updateById(artStudent);
    }

    @Override
    public void saveDatas(List<ArtStudentImportInfoDto> dtoList) {

    }

    @Override
    public ArtStudent getByCondition(Map<String, Object> params) {
        QueryWrapper<ArtStudent> query = Wrappers.query();
        query.eq(ArtStudent.Column.NAME.key(), params.get("name"));
        query.eq(StrUtil.humpToUnderline(ArtStudent.Column.CARD_NO.key()), params.get("cardNo"));
        query.eq(StrUtil.humpToUnderline(ArtStudent.Column.CLASSIFICATION_ID.key()), params.get("classificationId"));
        query.eq(StrUtil.humpToUnderline(ArtStudent.Column.BOOK_TYPE.key()), params.get("bookType"));
        query.eq(ArtStudent.Column.LEVEL.key(), params.get("level"));
        query.eq(ArtStudent.Column.STATE.key(), 1);

        List<ArtStudent> list = artStudentMapper.selectList(query);
        if (CollectionUtils.isEmpty(list)) {
                return null;
        }
        ArtStudent artStudent = list.get(0);
        LiveCourseClassification byId = liveCourseClassificationService.getById(artStudent.getClassificationId()
                .longValue());
        artStudent.setClassificationName(byId.getName());
        return artStudent;
    }


    private ArtStudent getByActivityAndCarNo(Integer activityId, String cardNo,
                                             Integer classificationId, String level) {
        return this.getByActivityAndCarNo(activityId, cardNo, classificationId, level, null);
    }

    private ArtStudent getByActivityAndCarNo(Integer activityId, String cardNo,
                                             Integer classificationId, String level, Integer bookType) {
        QueryWrapper<ArtStudent> query = Wrappers.query();
        query.eq(StrUtil.humpToUnderline(ArtStudent.Column.CARD_NO.key()), cardNo);
        query.eq(StrUtil.humpToUnderline(ArtStudent.Column.ACTIVITY_ID.key()), activityId);
        query.eq(StrUtil.humpToUnderline(ArtStudent.Column.CLASSIFICATION_ID.key()), classificationId);
        if (isNumericZidai(level)) {
            level = getChineseLevel(level);
        }
        query.eq(ArtStudent.Column.LEVEL.key(), level);
        query.eq(ArtStudent.Column.STATE.key(), 1);
        if (bookType != null) {
            query.eq(StrUtil.humpToUnderline(ArtStudent.Column.BOOK_TYPE.key()), bookType);
        }

        List<ArtStudent> list = artStudentMapper.selectList(query);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list.get(0);
    }

    private void makeSchoolData(ArtStudent artStudent) {
        if (!StringUtils.isEmpty(artStudent.getSchoolName())) {
            ArtSchool school = artSchoolService.getByName(artStudent.getSchoolName());
            if (school == null) {
                school = new ArtSchool();
                school.setName(artStudent.getSchoolName());
                artSchoolService.save(school);
            }
            artStudent.setSchoolId(school.getId());
        }
    }

//    @Override
//    public void saveDatas(List<ArtStudentImportInfoDto> dtoList) {
//        int i = 1;
//        for (ArtStudentImportInfoDto dto : dtoList) {
//            valid(dto, i);
//            ArtStudent artStudent = new ArtStudent();
//            artStudent.setName(dto.getName());
//            artStudent.setNamePy(dto.getNamePy());
//            artStudent.setState(String.valueOf(PublicState.NORMAL.getCode()));
//            artStudent.setBorn(DateUtil.parseDate(dto.getBorn(), "yyyy.MM.dd"));
////            artStudent.setSchool(dto.getSchool());
//            if (!StringUtils.isEmpty(dto.getSchool())) {
//                ArtSchool school = artSchoolService.getByName(dto.getSchool());
//                if (school == null) {
//                    school = new ArtSchool();
//                    school.setName(dto.getSchool());
//                    artSchoolService.save(school);
//                }
//                artStudent.setSchoolId(school.getId());
//            }
//            artStudent.setCardNo(dto.getCardNo());
//            artStudent.setCountry(dto.getCountry());
//            artStudent.setLevel(getChineseLevel(dto.getLevel()));
//            artStudent.setNation(dto.getNation());
//            artStudent.setSex("女".equals(dto.getSex()) ? "g" : "m");
//            artStudent.setCreatetime(new Date());
//            artStudent.setScore(dto.getScore());
//            artStudent.setBookNo(dto.getBookNo());
//
//            ArtActivity activity = artActivityService.getByActivityName(dto.getActivityName());
//            if (activity == null) {
//                // 活动不存在，新建活动
//                activity = new ArtActivity();
//                activity.setName(dto.getActivityName());
//                activity.setCreatetime(new Date());
//                activity.setUpdatetime(new Date());
//                activity.setState("1");
//                artActivityService.save(activity);
//            }
//            artStudent.setActivityId(activity.getId().intValue());
//            // 查询项目是否存在
//            LiveCourseClassification project = liveCourseClassificationService.getByName(dto.getProjectName());
//            if (project == null) {
//                // 不存在，新建专业
//                project = new LiveCourseClassification();
//                project.setName(dto.getProjectName());
//                project.setCreatetime(new Date());
//                project.setState("1");
//                liveCourseClassificationService.save(project);
//            }
//            artStudent.setClassificationId(project.getId().intValue());
//            if (dto.getBookType() == null || "全部".equals(dto.getBookType())) {
//                artStudent.setBookType(null);
//            } else {
//                if ("红皮".equals(dto.getBookType())) {
//                    artStudent.setBookType(2);
//                } else if ("白皮".equals(dto.getBookType())) {
//                    artStudent.setBookType(1);
//                }
//            }
//            // 查询学生是否重复导入
//            ArtStudent oldStudent = getByActivityAndCarNo(artStudent.getActivityId(), artStudent.getCardNo(), artStudent.getClassificationId(), artStudent.getLevel(), artStudent.getBookType());
//            if (oldStudent != null) {
//                i++;
//                continue;
//            }
//
//            if (dto.getBookType() == null || "全部".equals(dto.getBookType())) {
//                artStudent.setBookType(1);
//                artStudentDao.insertSelective(artStudent);
//                artStudent.setBookType(2);
//                artStudent.setId(null);
//                artStudentDao.insertSelective(artStudent);
//            } else {
//                if ("红皮".equals(dto.getBookType())) {
//                    artStudent.setBookType(2);
//                } else if ("白皮".equals(dto.getBookType())) {
//                    artStudent.setBookType(1);
//                }
//                artStudentDao.insertSelective(artStudent);
//            }
//            i++;
//        }
//    }
//
//    private void valid(ArtStudentImportInfoDto dto, int i) {
//        String line = "第" + i + "行";
//        // 查询活动是否存在
//        if (StringUtils.isEmpty(dto.getActivityName())) {
//            throw new HumanResourceException(ResultEnum.NO_ACTIVITY_RECORD.getCode(),
//                    line + ResultEnum.NO_ACTIVITY_RECORD.getMessage());
//        }
//        if (StringUtils.isEmpty(dto.getName())) {
//            throw new HumanResourceException(ResultEnum.NO_STUDENT_NAME_RECORD.getCode(),
//                    line + ResultEnum.NO_STUDENT_NAME_RECORD.getMessage());
//        }
//        if (StringUtils.isEmpty(dto.getLevel())) {
//            throw new HumanResourceException(ResultEnum.NO_LEVEL_RECORD.getCode(),
//                    line + ResultEnum.NO_LEVEL_RECORD.getMessage());
//        }
//        if (StringUtils.isEmpty(dto.getBookNo())) {
//            throw new HumanResourceException(ResultEnum.NO_BOOK_NO_RECORD.getCode(),
//                    line + ResultEnum.NO_BOOK_NO_RECORD.getMessage());
//        }
//        if (StringUtils.isEmpty(dto.getCardNo())) {
//            throw new HumanResourceException(ResultEnum.NO_CARDNO_RECORD.getCode(),
//                    line + ResultEnum.NO_CARDNO_RECORD.getMessage());
//        }
//        if (StringUtils.isEmpty(dto.getSex())) {
//            throw new HumanResourceException(ResultEnum.NO_SEX_RECORD.getCode(),
//                    line + ResultEnum.NO_SEX_RECORD.getMessage());
//        }
//        if (StringUtils.isEmpty(dto.getProjectName())) {
//            throw new HumanResourceException(ResultEnum.NO_TYPE_RECORD.getCode(),
//                    line + ResultEnum.NO_TYPE_RECORD.getMessage());
//        }
//        if (StringUtils.isEmpty(dto.getBorn())) {
//            throw new HumanResourceException(ResultEnum.NO_BORN_RECORD.getCode(),
//                    line + ResultEnum.NO_BORN_RECORD.getMessage());
//        }
//        if (StringUtils.isEmpty(dto.getScore())) {
//            throw new HumanResourceException(ResultEnum.NO_SCORE_RECORD.getCode(),
//                    line + ResultEnum.NO_SCORE_RECORD.getMessage());
//        }
//    }


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

    public static boolean isNumericZidai(String str) {
        for (int i = 0; i < str.length(); i++) {
            System.out.println(str.charAt(i));
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
//
//
}
