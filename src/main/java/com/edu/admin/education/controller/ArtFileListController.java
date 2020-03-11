package com.edu.admin.education.controller;

import com.edu.admin.education.command.ArtFileListSaveCommand;
import com.edu.admin.education.command.ArtFileListUpdateCommand;
import com.edu.admin.education.dto.ArtFileListDto;
import com.edu.admin.education.service.IArtFileListService;
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
 * file list 模块控制器s
 * @author mengqa
 * @date 2019-12-19
 */
@RestController
@RequestMapping("/artFileList")
@Api(tags = "art-文件管理相关接口")
public class ArtFileListController extends BaseController {

    protected static final Logger logger = LoggerFactory.getLogger(ArtFileListController.class);

    @Autowired
    private IArtFileListService artFileListServiceImpl;

    @PostMapping
    @ApiOperation(value = "新增文件记录", notes = "新增文件记录")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功", response = ArtFileListDto.class),
            @ApiResponse(code = 500, message = "参数异常")})
    public ArtFileListDto save(@RequestBody @Valid ArtFileListSaveCommand command, BindingResult bindingResult) {
        logger.info("=== command ==> {} ", command);
        validFormInfo(bindingResult);
        return artFileListServiceImpl.save(command);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取数据")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功", response = ArtFileListDto.class),
            @ApiResponse(code = 500, message = "参数异常")})
    public ArtFileListDto get(@PathVariable Long id) {
        logger.info("=== get id ==> {} ", id);
        return artFileListServiceImpl.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改文件记录", notes = "修改文件记录")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功", response = ArtFileListDto.class),
            @ApiResponse(code = 500, message = "参数异常")})
    public ArtFileListDto update(@RequestBody @Valid ArtFileListUpdateCommand command, BindingResult bindingResult) {
        logger.info("=== command ==> {} ", command);
        validFormInfo(bindingResult);
        return artFileListServiceImpl.update(command);
    }

    @GetMapping
    @ApiOperation(value = "列表")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功", response = ArtFileListDto.class),
            @ApiResponse(code = 500, message = "参数异常")})
    public PageTableResponse list(PageTableRequest request) {
//        return new PageTableHandler(new CountHandler() {
//
//            @Override
//            public int count(PageTableRequest request) {
//                return artFileListServiceImpl.count(request.getParams());
//            }
//        }, new ListHandler() {
//
//            @Override
//            public List<ArtFileListDto> list(PageTableRequest request) {
//                Map<String, Integer> page = getPageOffsetAndLimit(request);
//                return artFileListServiceImpl.list(request.getParams(), page.get("offset"), page.get("limit"));
//            }
//        }).handle(request);
        return artFileListServiceImpl.queryList(request);
    }

    @GetMapping("/findAll")
    @ApiOperation(value = "全部列表")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功", response = ArtFileListDto.class),
            @ApiResponse(code = 500, message = "参数异常")})
    public List<ArtFileListDto> findAll(PageTableRequest request) {
        return artFileListServiceImpl.findAll();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        artFileListServiceImpl.delete(id);
    }

}
