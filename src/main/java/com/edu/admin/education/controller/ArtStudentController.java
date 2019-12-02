package com.edu.admin.education.controller;

import com.edu.admin.education.enums.ResultEnum;
import com.edu.admin.education.excel.ExcelTitles;
import com.edu.admin.education.excel.ExportExcel;
import com.edu.admin.education.exception.HumanResourceException;
import com.edu.admin.education.model.ArtStudent;
import com.edu.admin.education.service.IArtStudentService;
import com.edu.admin.server.page.table.PageTableHandler;
import com.edu.admin.server.page.table.PageTableHandler.CountHandler;
import com.edu.admin.server.page.table.PageTableHandler.ListHandler;
import com.edu.admin.server.page.table.PageTableRequest;
import com.edu.admin.server.page.table.PageTableResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 特长生信息 模块控制器
 * @author mengqa
 * @date 2018-04-01
 */
@RestController
@RequestMapping("/artStudents")
public class ArtStudentController {

    @Autowired
    private IArtStudentService artStudentService;

    @PostMapping
    @ApiOperation(value = "保存")
    public ArtStudent save(@RequestBody ArtStudent artStudent) {
        validParams(artStudent);
        if (StringUtils.isEmpty(artStudent.getBookNo())) {
            throw new HumanResourceException(ResultEnum.PARAMS_ERROR_BON);
        }
        artStudentService.save(artStudent);
        return artStudent;
    }

    private void validParams(@RequestBody ArtStudent artStudent) {
        if (StringUtils.isEmpty(artStudent.getCardNo())) {
            throw new HumanResourceException(ResultEnum.PARAMS_ERROR_CARD_NO);
        }
        if (artStudent.getBorn() == null) {
            throw new HumanResourceException(ResultEnum.PARAMS_ERROR_BORN);
        }
        if (artStudent.getActivityId() == null) {
            throw new HumanResourceException(ResultEnum.PARAMS_ERROR_ACTIVITY);
        }
        if (artStudent.getClassificationId() == null) {
            throw new HumanResourceException(ResultEnum.PARAMS_ERROR_PROJECT);
        }
        if (StringUtils.isEmpty(artStudent.getName())) {
            throw new HumanResourceException(ResultEnum.PARAMS_ERROR_NAME);
        }
        if (StringUtils.isEmpty(artStudent.getNation())) {
            throw new HumanResourceException(ResultEnum.PARAMS_ERROR_P);
        }
        if (StringUtils.isEmpty(artStudent.getCountry())) {
            throw new HumanResourceException(ResultEnum.PARAMS_ERROR_N);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public ArtStudent get(@PathVariable Long id) {
        return artStudentService.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public ArtStudent update(@RequestBody ArtStudent artStudent) {
        validParams(artStudent);
        artStudentService.update(artStudent);

        return artStudent;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return artStudentService.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<ArtStudent> list(PageTableRequest request) {
                return artStudentService.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @PostMapping("/deleteLogic")
    @ApiOperation(value = "删除")
//    @MethodLog(remark = "删除特长生")
    public void deleteLogic(@RequestBody ArtStudent artStudent) {
        artStudentService.deleteLogic(artStudent);
    }


    @GetMapping("/export")
    @ApiOperation(value = "导出")
    public void export(PageTableRequest request, HttpServletResponse response) {
        List<ArtStudent> list = artStudentService.list(request.getParams(), request.getOffset(), request.getLimit());
        if (!CollectionUtils.isEmpty(list)) {
            ExportExcel ex = new ExportExcel("特长生列表", beanCopy(list), ArtStudent.class, response);
            try {
                ex.export();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            throw new HumanResourceException(ResultEnum.NO_RECORD);
        }
    }

    private List<Object[]> beanCopy(List<ArtStudent> list) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        List<Object[]>  dataList = new ArrayList<>();
        Object[] objs;
        for (int i = 0; i < list.size(); i++) {
            ArtStudent source = list.get(i);
            objs = new Object[ArtStudent.class.getDeclaredAnnotation(ExcelTitles.class).value().length];
            objs[0] = i;
            objs[1] = source.getId();
            objs[2] = source.getName();
            objs[3] = source.getSex();
            objs[4] = source.getCountry();
            objs[5] = source.getNation();
            String bornDate = "";
            if (source.getBorn() != null) {
                bornDate = df.format(source.getBorn());
            }
            objs[6] = bornDate;
            objs[7] = source.getClassificationName();
            objs[8] = source.getLevel();
            objs[9] = source.getCardNo();
            objs[10] = source.getBookNo();
            objs[11] = source.getBookType();
            dataList.add(objs);
        }
        return dataList;
    }


}
