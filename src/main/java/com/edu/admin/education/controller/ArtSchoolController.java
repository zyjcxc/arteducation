package com.edu.admin.education.controller;

import com.edu.admin.education.model.ArtSchool;
import com.edu.admin.education.service.IArtSchoolService;
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
        return artSchoolService.queryList(request);
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
