package com.edu.admin.education.controller;

import com.edu.admin.education.enums.PublicState;
import com.edu.admin.education.model.LiveCourseClassification;
import com.edu.admin.education.service.ILiveCourseClassificationService;
import com.edu.admin.server.page.table.PageTableRequest;
import com.edu.admin.server.page.table.PageTableResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 课程分类 模块控制器
 * @author mengqa
 * @date 2018-04-05
 */
@RestController
@RequestMapping("/liveCourseClassifications")
public class LiveCourseClassificationController {

    @Autowired
    private ILiveCourseClassificationService liveCourseClassificationServiceImpl;

    @PostMapping
    @ApiOperation(value = "保存")
    public LiveCourseClassification save(@RequestBody LiveCourseClassification liveCourseClassification) {
        liveCourseClassificationServiceImpl.save(liveCourseClassification);

        return liveCourseClassification;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public LiveCourseClassification get(@PathVariable Long id) {
        return liveCourseClassificationServiceImpl.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public LiveCourseClassification update(@RequestBody LiveCourseClassification liveCourseClassification) {
        if (PublicState.DELETE.getDataBase().equals(liveCourseClassification.getState())) {
            liveCourseClassificationServiceImpl.delete(liveCourseClassification.getId());
            return liveCourseClassification;
        }
        liveCourseClassificationServiceImpl.update(liveCourseClassification);
        return liveCourseClassification;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
//        Map<String, Object> params = request.getParams();
//        params.put("noDelState", PublicState.DELETE.getCode());
//        return new PageTableHandler(new CountHandler() {
//
//            @Override
//            public int count(PageTableRequest request) {
//                return liveCourseClassificationServiceImpl.count(request.getParams());
//            }
//        }, new ListHandler() {
//
//            @Override
//            public List<LiveCourseClassification> list(PageTableRequest request) {
//                return liveCourseClassificationServiceImpl.list(request.getParams(), request.getOffset(), request.getLimit());
//            }
//        }).handle(request);
        return liveCourseClassificationServiceImpl.queryList(request);
    }

    @GetMapping("/findAll")
    @ApiOperation(value = "全部列表")
    public List<LiveCourseClassification> findAll(PageTableRequest request) {
       return liveCourseClassificationServiceImpl.findAll();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        liveCourseClassificationServiceImpl.delete(id);
    }
}
