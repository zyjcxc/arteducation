package com.edu.admin.education.controller;

import com.edu.admin.education.command.ArtAuthbookSaveCommand;
import com.edu.admin.education.command.ArtAuthbookUpdateCommand;
import com.edu.admin.education.dto.ArtAuthbookDto;
import com.edu.admin.education.service.IArtAuthbookService;
import com.edu.admin.server.page.table.PageTableRequest;
import com.edu.admin.server.page.table.PageTableResponse;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * authbook 模块控制器s
 * @author mengqa
 * @date 2019-11-06
 */
@RestController
@RequestMapping("/artAuthbook")
@Api(tags = "art-授权书相关接口")
public class ArtAuthbookController extends BaseController {

    protected static final Logger logger = LoggerFactory.getLogger(ArtAuthbookController.class);

    @Autowired
    private IArtAuthbookService artAuthbookServiceImpl;

    @PostMapping
    @ApiOperation(value = "新增授权书", notes = "授权书管理")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功", response = ArtAuthbookDto.class),
            @ApiResponse(code = 500, message = "参数异常")})
    public ArtAuthbookDto save(@RequestBody @Valid ArtAuthbookSaveCommand command, BindingResult bindingResult) {
        logger.info("=== command ==> {} ", command);
        validFormInfo(bindingResult);
        return artAuthbookServiceImpl.save(command);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取数据")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功", response = ArtAuthbookDto.class),
            @ApiResponse(code = 500, message = "参数异常")})
    public ArtAuthbookDto get(@PathVariable Long id) {
        logger.info("=== get id ==> {} ", id);
        return artAuthbookServiceImpl.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改授权书", notes = "授权书管理")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功", response = ArtAuthbookDto.class),
            @ApiResponse(code = 500, message = "参数异常")})
    public ArtAuthbookDto update(@RequestBody @Valid ArtAuthbookUpdateCommand command, BindingResult bindingResult) {
        logger.info("=== command ==> {} ", command);
        validFormInfo(bindingResult);
        return artAuthbookServiceImpl.update(command);
    }

    @GetMapping
    @ApiOperation(value = "列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "授权书名", paramType = "query", dataType="string")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功", response = ArtAuthbookDto.class),
            @ApiResponse(code = 500, message = "参数异常")})
    public PageTableResponse list(PageTableRequest request) {
        return artAuthbookServiceImpl.queryList(request);
    }

    @GetMapping("/findAll")
    @ApiOperation(value = "全部列表")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功", response = ArtAuthbookDto.class),
            @ApiResponse(code = 500, message = "参数异常")})
    public List<ArtAuthbookDto> findAll(PageTableRequest request) {
        return artAuthbookServiceImpl.findAll();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        artAuthbookServiceImpl.delete(id);
    }

}
