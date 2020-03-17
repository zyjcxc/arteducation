package com.edu.admin.education.controller;

import com.edu.admin.education.command.ArtTextbookSaveCommand;
import com.edu.admin.education.command.ArtTextbookUpdateCommand;
import com.edu.admin.education.dto.ArtTextbookDto;
import com.edu.admin.education.service.IArtTextbookService;
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
 * textbook 模块控制器s
 * @author mengqa
 * @date 2019-11-06
 */
@RestController
@RequestMapping("/artTextbook")
@Api(tags = "art-教材相关接口")
public class ArtTextbookController extends BaseController {

    protected static final Logger logger = LoggerFactory.getLogger(ArtTextbookController.class);

    @Autowired
    private IArtTextbookService artTextbookServiceImpl;

    @PostMapping
    @ApiOperation(value = "新增教材", notes = "教材管理")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功", response = ArtTextbookDto.class),
            @ApiResponse(code = 500, message = "参数异常")})
    public ArtTextbookDto save(@RequestBody @Valid ArtTextbookSaveCommand command, BindingResult bindingResult) {
        logger.info("=== command ==> {} ", command);
        validFormInfo(bindingResult);
        return artTextbookServiceImpl.save(command);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取数据")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功", response = ArtTextbookDto.class),
            @ApiResponse(code = 500, message = "参数异常")})
    public ArtTextbookDto get(@PathVariable Long id) {
        logger.info("=== get id ==> {} ", id);
        return artTextbookServiceImpl.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改教材", notes = "教材管理")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功", response = ArtTextbookDto.class),
            @ApiResponse(code = 500, message = "参数异常")})
    public ArtTextbookDto update(@RequestBody @Valid ArtTextbookUpdateCommand command, BindingResult bindingResult) {
        logger.info("=== command ==> {} ", command);
        validFormInfo(bindingResult);
        return artTextbookServiceImpl.update(command);
    }

    @GetMapping
    @ApiOperation(value = "列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "textbookTypeId", value = "教材分类id", paramType = "query", dataType="int"),
            @ApiImplicitParam(name = "title", value = "教材名", paramType = "query", dataType="string")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功", response = ArtTextbookDto.class),
            @ApiResponse(code = 500, message = "参数异常")})
    public PageTableResponse list(PageTableRequest request) {
//        return new PageTableHandler(new CountHandler() {
//
//            @Override
//            public int count(PageTableRequest request) {
//                return artTextbookServiceImpl.count(request.getParams());
//            }
//        }, new ListHandler() {
//
//            @Override
//            public List<ArtTextbookDto> list(PageTableRequest request) {
//                Map<String, Integer> page = getPageOffsetAndLimit(request);
//                return artTextbookServiceImpl.list(request.getParams(), page.get("offset"), page.get("limit"));
//            }
//        }).handle(request);
        return artTextbookServiceImpl.queryList(request);
    }

    @GetMapping("/findAll")
    @ApiOperation(value = "全部列表")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功", response = ArtTextbookDto.class),
            @ApiResponse(code = 500, message = "参数异常")})
    public List<ArtTextbookDto> findAll(PageTableRequest request) {
        return artTextbookServiceImpl.findAll();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        artTextbookServiceImpl.delete(id);
    }

}
