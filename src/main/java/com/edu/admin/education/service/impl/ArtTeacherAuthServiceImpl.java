package com.edu.admin.education.service.impl;

import com.edu.admin.education.dao.ArtTeacherAuthDao;
import com.edu.admin.education.enums.PublicState;
import com.edu.admin.education.enums.ResultEnum;
import com.edu.admin.education.exception.HumanResourceException;
import com.edu.admin.education.model.ArtSchool;
import com.edu.admin.education.model.ArtTeacherAuth;
import com.edu.admin.education.model.ArtTeacherAuthImportInfoDto;
import com.edu.admin.education.model.LiveCourseClassification;
import com.edu.admin.education.service.IArtSchoolService;
import com.edu.admin.education.service.IArtTeacherAuthService;
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
 * 模块业务接口具体实现
 * @author mengqa
 * @date 2018-04-03
 **/
@Service
@Transactional
public class ArtTeacherAuthServiceImpl implements IArtTeacherAuthService{

    @Autowired
    private ArtTeacherAuthDao artTeachAuthDao;

    @Autowired
    private IArtSchoolService artSchoolService;

    @Autowired
    private ILiveCourseClassificationService liveCourseClassificationService;

//    @Autowired
//    private IArtActivityService artActivityService;

    @Override
    public ArtTeacherAuth getById(Long id) {
        ArtTeacherAuth obj = artTeachAuthDao.selectByPrimaryKey(id);
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
    public void save(ArtTeacherAuth artTeacherAuth) {
        // 查询学生是否重复录入
        ArtTeacherAuth oldData = getByBookNo(artTeacherAuth.getBookNo());
        if (oldData != null) {
            throw new HumanResourceException(ResultEnum.REPEAT_BOOKNO_RECORD);
        }
        // 新增时默认为正常状态
        artTeacherAuth.setState(String.valueOf(PublicState.NORMAL.getCode()));
        PinyinTool tool = new PinyinTool();
        try {
            String py = tool.toPinYin(artTeacherAuth.getName(), " ", PinyinTool.Type.UPPERCASE);
            artTeacherAuth.setNamePy(py);
        } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
            badHanyuPinyinOutputFormatCombination.printStackTrace();
            artTeacherAuth.setNamePy("");
        }
        makeSchoolData(artTeacherAuth);

        if (artTeacherAuth.getBookType() == null || artTeacherAuth.getBookType() == 0) {
            // 插入两条数据
            artTeacherAuth.setBookType(1);
            artTeachAuthDao.insertSelective(artTeacherAuth);
            artTeacherAuth.setBookType(2);
            artTeacherAuth.setId(null);
            artTeachAuthDao.insertSelective(artTeacherAuth);
        } else {
            // 插入一条数据
            artTeachAuthDao.insertSelective(artTeacherAuth);
        }
    }

    private ArtTeacherAuth getByBookNo(String bookNo) {
        Example example = new Example(ArtTeacherAuth.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("bookNo", bookNo);
        criteria.andEqualTo("state", 1);
        List<ArtTeacherAuth> list = artTeachAuthDao.selectByExample(example);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public void update(ArtTeacherAuth artTeacherAuth) {
        makeSchoolData(artTeacherAuth);
        artTeachAuthDao.updateByPrimaryKeySelective(artTeacherAuth);
    }

    private void makeSchoolData(ArtTeacherAuth artTeacherAuth) {
        if (!StringUtils.isEmpty(artTeacherAuth.getSchoolName())) {
            ArtSchool school = artSchoolService.getByName(artTeacherAuth.getSchoolName());
            if (school == null) {
                school = new ArtSchool();
                school.setName(artTeacherAuth.getSchoolName());
                artSchoolService.save(school);
            }
            artTeacherAuth.setSchoolId(school.getId());
        }
    }

    @Override
    public void saveDatas(List<ArtTeacherAuthImportInfoDto> dtoList) {
        int i = 1;
        for (ArtTeacherAuthImportInfoDto dto : dtoList) {
            valid(dto, i);
            ArtTeacherAuth artTeacherAuth = new ArtTeacherAuth();
            artTeacherAuth.setName(dto.getName());
            artTeacherAuth.setNamePy(dto.getNamePy());
            artTeacherAuth.setState(String.valueOf(PublicState.NORMAL.getCode()));
            artTeacherAuth.setBorn(DateUtil.parseDate(dto.getBorn(), "yyyy.MM.dd"));
//            artTeacherAuth.setSchool(dto.getSchool());
            if (!StringUtils.isEmpty(dto.getSchool())) {
                ArtSchool school = artSchoolService.getByName(dto.getSchool());
                if (school == null) {
                    school = new ArtSchool();
                    school.setName(dto.getSchool());
                    artSchoolService.save(school);
                }
                artTeacherAuth.setSchoolId(school.getId());
            }
            artTeacherAuth.setPosition(dto.getPosition());
            artTeacherAuth.setVatime(dto.getVatime());
            artTeacherAuth.setSex("女".equals(dto.getSex()) ? "g" : "m");
            artTeacherAuth.setCreatetime(new Date());
            artTeacherAuth.setBookNo(dto.getBookNo());

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
            artTeacherAuth.setClassificationId(project.getId());
            // 查询是否重复导入
            ArtTeacherAuth oldStudent = getByBookNo(artTeacherAuth.getBookNo());
            if (oldStudent != null) {
                i++;
                continue;
            }
            if (dto.getBookType() == null || "全部".equals(dto.getBookType())) {
                artTeacherAuth.setBookType(1);
                artTeachAuthDao.insertSelective(artTeacherAuth);
                artTeacherAuth.setBookType(2);
                artTeacherAuth.setId(null);
                artTeachAuthDao.insertSelective(artTeacherAuth);
            } else {
                if ("红皮".equals(dto.getBookType())) {
                    artTeacherAuth.setBookType(1);
                } else if ("绿皮".equals(dto.getBookType())) {
                    artTeacherAuth.setBookType(2);
                }
                artTeachAuthDao.insertSelective(artTeacherAuth);
            }
            i++;
        }
    }

    @Override
    public ArtTeacherAuth getByCondition(Map<String, Object> params) {
        Example example = new Example(ArtTeacherAuth.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("bookNo", params.get("bookNo"));
        criteria.andEqualTo("name", params.get("name"));
        criteria.andEqualTo("bookType", params.get("bookType"));
//        criteria.andEqualTo("school", params.get("school"));
        List<ArtTeacherAuth> list = artTeachAuthDao.selectByExample(example);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        ArtTeacherAuth artTeacherAuth = list.get(0);
        LiveCourseClassification byId = liveCourseClassificationService.getById(artTeacherAuth.getClassificationId());
        artTeacherAuth.setClassificationName(byId.getName());
        return artTeacherAuth;
    }

    @Override
    public List<ArtTeacherAuth> list(Map<String, Object> params, Integer offset, Integer limit) {
        if (offset != null) {
            PageHelper.offsetPage(offset, limit);
        }
        // QBC查询 sql改了，暂时不用了
//        Example example = getQueryExample(params);
//        List<LiveCourseInfo> list = liveCourseInfoDao.selectByExample(example);
        return artTeachAuthDao.selectByCustomSql(params);
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
            count = artTeachAuthDao.countByCustomSql(params);
            params.put("orderBy", orderBy);
        }
        return count;

    }

    @Override
    public int deleteLogic(ArtTeacherAuth artTeacherAuth) {
        return artTeachAuthDao.updateByPrimaryKeySelective(artTeacherAuth);
    }

    @Override
    public void deleteLogicBatch(List<String> ids) {
        ids.forEach(id -> {
            ArtTeacherAuth artTeacherAuth = new ArtTeacherAuth();
            artTeacherAuth.setState("2");
            artTeacherAuth.setId(Long.parseLong(id));
            artTeachAuthDao.updateByPrimaryKeySelective(artTeacherAuth);
        });
    }

    private void valid(ArtTeacherAuthImportInfoDto dto, int i) {
        String line = "第" + i + "行";
        if (StringUtils.isEmpty(dto.getName())) {
            throw new HumanResourceException(ResultEnum.NO_STUDENT_NAME_RECORD.getCode(),
                    line + ResultEnum.NO_STUDENT_NAME_RECORD.getMessage());
        }
        if (StringUtils.isEmpty(dto.getBookNo())) {
            throw new HumanResourceException(ResultEnum.NO_BOOK_NO_RECORD.getCode(),
                    line + ResultEnum.NO_BOOK_NO_RECORD.getMessage());
        }
        if (StringUtils.isEmpty(dto.getSex())) {
            throw new HumanResourceException(ResultEnum.NO_SEX_RECORD.getCode(),
                    line + ResultEnum.NO_SEX_RECORD.getMessage());
        }
        if (StringUtils.isEmpty(dto.getProjectName())) {
            throw new HumanResourceException(ResultEnum.NO_TYPE_RECORD.getCode(),
                    line + ResultEnum.NO_TYPE_RECORD.getMessage());
        }
        if (StringUtils.isEmpty(dto.getBorn())) {
            throw new HumanResourceException(ResultEnum.NO_BORN_RECORD.getCode(),
                    line + ResultEnum.NO_BORN_RECORD.getMessage());
        }
        if (StringUtils.isEmpty(dto.getPosition())) {
            throw new HumanResourceException(ResultEnum.NO_POS_RECORD.getCode(),
                    line + ResultEnum.NO_POS_RECORD.getMessage());
        }
        if (StringUtils.isEmpty(dto.getVatime())) {
            throw new HumanResourceException(ResultEnum.NO_VATIME_RECORD.getCode(),
                    line + ResultEnum.NO_VATIME_RECORD.getMessage());
        }
    }

}
