package com.edu.admin.education.controller;

import com.edu.admin.education.model.ArtActivity;
import com.edu.admin.education.service.IArtActivityService;
import com.edu.admin.server.page.table.PageTableHandler;
import com.edu.admin.server.page.table.PageTableHandler.CountHandler;
import com.edu.admin.server.page.table.PageTableHandler.ListHandler;
import com.edu.admin.server.page.table.PageTableRequest;
import com.edu.admin.server.page.table.PageTableResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
/**
 * 活动 模块控制器
 * @author mengqa
 * @date 2018-11-21
 */
@RestController
@RequestMapping("/artActivitys")
public class ArtActivityController extends BaseController {

    @Autowired
    private IArtActivityService artActivityServiceImpl;

    @PostMapping
    @ApiOperation(value = "保存")
    public ArtActivity save(@RequestBody @Valid ArtActivity artAcitivty, BindingResult bindingResult) {
        validFormInfo(bindingResult);

        artActivityServiceImpl.save(artAcitivty);

        return artAcitivty;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public ArtActivity get(@PathVariable Long id) {
        return artActivityServiceImpl.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public ArtActivity update(@RequestBody @Valid ArtActivity artAcitivty, BindingResult bindingResult) {
        validFormInfo(bindingResult);

        artActivityServiceImpl.update(artAcitivty);

        return artAcitivty;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return artActivityServiceImpl.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<ArtActivity> list(PageTableRequest request) {
                return artActivityServiceImpl.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @GetMapping("/findAll")
    @ApiOperation(value = "全部列表")
    public List<ArtActivity> findAll(PageTableRequest request) {
        return artActivityServiceImpl.findAll();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        artActivityServiceImpl.delete(id);
    }

}
