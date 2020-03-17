package com.edu.admin.education.controller;

import com.edu.admin.education.command.ArtTextbookTypeSaveCommand;
import com.edu.admin.education.command.ArtTextbookTypeUpdateCommand;
import com.edu.admin.education.dto.ArtTextbookTypeDto;
import com.edu.admin.education.service.IArtTextbookTypeService;
import com.edu.admin.server.page.table.PageTableRequest;
import com.edu.admin.server.page.table.PageTableResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * textbookType 模块控制器s
 * @author mengqa
 * @date 2019-11-06
 */
@RestController
@RequestMapping("/artTextbookType")
@Api(tags = "art-教材分类相关接口")
public class ArtTextbookTypeController extends BaseController {

    protected static final Logger logger = LoggerFactory.getLogger(ArtTextbookTypeController.class);

    @Autowired
    private IArtTextbookTypeService artTextbookTypeServiceImpl;

    @PostMapping
    @ApiOperation(value = "新增教材分类", notes = "教材分类管理")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功", response = ArtTextbookTypeDto.class),
            @ApiResponse(code = 500, message = "参数异常")})
    public ArtTextbookTypeDto save(@RequestBody @Valid ArtTextbookTypeSaveCommand command, BindingResult bindingResult) {
        logger.info("=== command ==> {} ", command);
        validFormInfo(bindingResult);
        return artTextbookTypeServiceImpl.save(command);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取数据")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功", response = ArtTextbookTypeDto.class),
            @ApiResponse(code = 500, message = "参数异常")})
    public ArtTextbookTypeDto get(@PathVariable Long id) {
        logger.info("=== get id ==> {} ", id);
        return artTextbookTypeServiceImpl.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改教材分类", notes = "教材分类管理")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功", response = ArtTextbookTypeDto.class),
            @ApiResponse(code = 500, message = "参数异常")})
    public ArtTextbookTypeDto update(@RequestBody @Valid ArtTextbookTypeUpdateCommand command, BindingResult bindingResult) {
        logger.info("=== command ==> {} ", command);
        validFormInfo(bindingResult);
        return artTextbookTypeServiceImpl.update(command);
    }

    @GetMapping
    @ApiOperation(value = "列表")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功", response = ArtTextbookTypeDto.class),
            @ApiResponse(code = 500, message = "参数异常")})
    public PageTableResponse list(PageTableRequest request) {
//        return new PageTableHandler(new CountHandler() {
//
//            @Override
//            public int count(PageTableRequest request) {
//                return artTextbookTypeServiceImpl.count(request.getParams());
//            }
//        }, new ListHandler() {
//
//            @Override
//            public List<ArtTextbookTypeDto> list(PageTableRequest request) {
//                Map<String, Integer> page = getPageOffsetAndLimit(request);
//                return artTextbookTypeServiceImpl.list(request.getParams(), page.get("offset"), page.get("limit"));
//            }
//        }).handle(request);
        return artTextbookTypeServiceImpl.queryList(request);
    }

    @GetMapping("/findAll")
    @ApiOperation(value = "全部列表")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功", response = ArtTextbookTypeDto.class),
            @ApiResponse(code = 500, message = "参数异常")})
    public List<ArtTextbookTypeDto> findAll(PageTableRequest request) {
        return artTextbookTypeServiceImpl.findAll();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        artTextbookTypeServiceImpl.delete(id);
    }

}
