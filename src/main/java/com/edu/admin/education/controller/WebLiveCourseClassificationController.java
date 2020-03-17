package com.edu.admin.education.controller;

import com.edu.admin.education.model.LiveCourseClassification;
import com.edu.admin.education.service.ILiveCourseClassificationService;
import com.edu.admin.server.page.table.PageTableRequest;
import com.edu.admin.server.page.table.PageTableResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 课程分类 模块控制器
 * @author mengqa
 * @date 2018-04-05
 */
@RestController
@RequestMapping("/web/liveCourseClassifications")
@Api(tags = "art-web-课程分类相关接口")
public class WebLiveCourseClassificationController {

    @Autowired
    private ILiveCourseClassificationService liveCourseClassificationServiceImpl;

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public LiveCourseClassification get(@PathVariable Long id) {
        return liveCourseClassificationServiceImpl.getById(id);
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

}
