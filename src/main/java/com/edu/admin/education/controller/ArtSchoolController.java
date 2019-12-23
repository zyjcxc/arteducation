package com.edu.admin.education.controller;

import com.edu.admin.education.enums.PublicState;
import com.edu.admin.education.model.ArtSchool;
import com.edu.admin.education.service.IArtSchoolService;
import com.edu.admin.server.page.table.PageTableHandler;
import com.edu.admin.server.page.table.PageTableHandler.CountHandler;
import com.edu.admin.server.page.table.PageTableHandler.ListHandler;
import com.edu.admin.server.page.table.PageTableRequest;
import com.edu.admin.server.page.table.PageTableResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 课程分类 模块控制器
 * @author mengqa
 * @date 2018-04-05
 */
@RestController
@RequestMapping("/artSchool")
public class ArtSchoolController {

    @Autowired
    private IArtSchoolService artSchoolService;

    @PostMapping
    @ApiOperation(value = "保存")
    public ArtSchool save(@RequestBody ArtSchool artSchool) {
        artSchoolService.save(artSchool);

        return artSchool;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public ArtSchool get(@PathVariable Long id) {
        return artSchoolService.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public ArtSchool update(@RequestBody ArtSchool artSchool) {
        artSchoolService.update(artSchool);

        return artSchool;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        Map<String, Object> params = request.getParams();
        params.put("noDelState", PublicState.DELETE.getCode());
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return artSchoolService.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<ArtSchool> list(PageTableRequest request) {
                return artSchoolService.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @GetMapping("/findAll")
    @ApiOperation(value = "全部列表")
    public List<ArtSchool> findAll(PageTableRequest request) {
       return artSchoolService.findAll();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        artSchoolService.delete(id);
    }
}
