package com.edu.admin.education.controller;

import com.edu.admin.education.enums.ResultEnum;
import com.edu.admin.education.excel.ExcelTitles;
import com.edu.admin.education.excel.ExportExcel;
import com.edu.admin.education.exception.HumanResourceException;
import com.edu.admin.education.model.ArtTeacherAuth;
import com.edu.admin.education.service.IArtTeacherAuthService;
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
 * 模块控制器
 * @author mengqa
 * @date 2018-04-01
 */
@RestController
@RequestMapping("/artTeacherAuth")
public class ArtTeacherAuthController {

    @Autowired
    private IArtTeacherAuthService artTeacherAuthService;

    @PostMapping
    @ApiOperation(value = "保存")
    public ArtTeacherAuth save(@RequestBody ArtTeacherAuth artTeacherAuth) {
        validParams(artTeacherAuth);
        artTeacherAuthService.save(artTeacherAuth);
        return artTeacherAuth;
    }

    private void validParams(@RequestBody ArtTeacherAuth artTeacherAuth) {
        if (StringUtils.isEmpty(artTeacherAuth.getBookNo())) {
            throw new HumanResourceException(ResultEnum.PARAMS_ERROR_BON);
        }
        if (StringUtils.isEmpty(artTeacherAuth.getName())) {
            throw new HumanResourceException(ResultEnum.PARAMS_ERROR_NAME);
        }
        if (artTeacherAuth.getBorn() == null) {
            throw new HumanResourceException(ResultEnum.PARAMS_ERROR_BORN);
        }
        if (artTeacherAuth.getClassificationId() == null) {
            throw new HumanResourceException(ResultEnum.PARAMS_ERROR_PROJECT);
        }
        if (StringUtils.isEmpty(artTeacherAuth.getPosition())) {
            throw new HumanResourceException(ResultEnum.PARAMS_ERROR_POS);
        }
        if (StringUtils.isEmpty(artTeacherAuth.getVatime())) {
            throw new HumanResourceException(ResultEnum.PARAMS_ERROR_VTIME);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public ArtTeacherAuth get(@PathVariable Long id) {
        return artTeacherAuthService.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public ArtTeacherAuth update(@RequestBody ArtTeacherAuth artTeacherAuth) {
        validParams(artTeacherAuth);
        artTeacherAuthService.update(artTeacherAuth);

        return artTeacherAuth;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return artTeacherAuthService.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<ArtTeacherAuth> list(PageTableRequest request) {
                return artTeacherAuthService.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @PostMapping("/deleteLogic")
    @ApiOperation(value = "删除")
//    @MethodLog(remark = "删除特长生")
    public void deleteLogic(@RequestBody ArtTeacherAuth artTeacherAuth) {
        artTeacherAuthService.deleteLogic(artTeacherAuth);
    }

    @PostMapping("/batchDel")
    @ApiOperation(value = "批量删除")
//    @MethodLog(remark = "删除特长生")
    public void batchDel(@RequestBody ArtTeacherAuth artTeacherAuth) {
        artTeacherAuthService.deleteLogicBatch(artTeacherAuth.getIds());
    }


    @GetMapping("/export")
    @ApiOperation(value = "导出")
    public void export(PageTableRequest request, HttpServletResponse response) {
        List<ArtTeacherAuth> list = artTeacherAuthService.list(request.getParams(), request.getOffset(), request.getLimit());
        if (!CollectionUtils.isEmpty(list)) {
            ExportExcel ex = new ExportExcel("老师列表", beanCopy(list), ArtTeacherAuth.class, response);
            try {
                ex.export();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            throw new HumanResourceException(ResultEnum.NO_RECORD);
        }
    }

    private List<Object[]> beanCopy(List<ArtTeacherAuth> list) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd");
        List<Object[]>  dataList = new ArrayList<>();
        Object[] objs;
        for (int i = 0; i < list.size(); i++) {
            ArtTeacherAuth source = list.get(i);
            objs = new Object[ArtTeacherAuth.class.getDeclaredAnnotation(ExcelTitles.class).value().length];
//            objs[0] = i;
            // 1.白皮 2.红皮
            if (source.getBookType() != null && source.getBookType() == 1) {
                objs[0] = "红皮";
            } else if (source.getBookType() != null && source.getBookType() == 2) {
                objs[0] = "绿皮";
            } else {
                objs[0] = "未知";
            }
            objs[1] = source.getBookNo();
            objs[2] = source.getName();
            objs[3] = source.getNamePy();
            String bornDate = "";
            if (source.getBorn() != null) {
                bornDate = df.format(source.getBorn());
            }
            objs[4] = bornDate;
            if (source.getSex() != null && "g".equals(source.getSex())) {
                objs[5] = "女";
            } else if (source.getSex() != null && "g".equals(source.getSex())) {
                objs[5] = "男";
            } else {
                objs[5] = "未知";
            }
            objs[6] = source.getClassificationName();

            // 专业
            objs[7] = source.getPosition();
            objs[8] = source.getVatime();
            objs[9] = source.getSchoolName();
            dataList.add(objs);
        }
        return dataList;
    }


}
