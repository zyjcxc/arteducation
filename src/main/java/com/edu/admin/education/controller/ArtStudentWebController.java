//package com.edu.admin.education.controller;
//
//import com.edu.admin.education.model.ArtStudent;
//import com.edu.admin.education.service.IArtStudentService;
//import com.edu.admin.server.page.table.PageTableHandler;
//import com.edu.admin.server.page.table.PageTableHandler.CountHandler;
//import com.edu.admin.server.page.table.PageTableHandler.ListHandler;
//import com.edu.admin.server.page.table.PageTableRequest;
//import com.edu.admin.server.page.table.PageTableResponse;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
///**
// * 特长生信息 模块控制器
// * @author mengqa
// * @date 2018-04-01
// */
//@RestController
//@RequestMapping("/search/artStudents")
//@Api(tags = "作废接口")
//public class ArtStudentWebController {
//
//    @Autowired
//    private IArtStudentService artStudentService;
//
//    @GetMapping("/{id}")
//    @ApiOperation(value = "根据id获取")
//    public ArtStudent get(@PathVariable Long id) {
//        return artStudentService.getById(id);
//    }
//
//
//    @GetMapping("/list")
//    @ApiOperation(value = "列表")
//    public PageTableResponse list(PageTableRequest request) {
//        return new PageTableHandler(new CountHandler() {
//
//            @Override
//            public int count(PageTableRequest request) {
//                return artStudentService.count(request.getParams());
//            }
//        }, new ListHandler() {
//
//            @Override
//            public List<ArtStudent> list(PageTableRequest request) {
//                return artStudentService.list(request.getParams(), request.getOffset(), request.getLimit());
//            }
//        }).handle(request);
//    }
//
//}
