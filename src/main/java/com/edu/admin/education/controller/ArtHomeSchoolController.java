package com.edu.admin.education.controller;

import com.edu.admin.education.command.ArtHomeSchoolSaveCommand;
import com.edu.admin.education.command.ArtHomeSchoolUpdateCommand;
import com.edu.admin.education.dto.ArtHomeSchoolDto;
import com.edu.admin.education.service.IArtHomeSchoolService;
import com.edu.admin.server.page.table.PageTableHandler;
import com.edu.admin.server.page.table.PageTableHandler.CountHandler;
import com.edu.admin.server.page.table.PageTableHandler.ListHandler;
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
import java.util.Map;

/**
 * banner info 模块控制器s
 * @author mengqa
 * @date 2019-11-06
 */
@RestController
@RequestMapping("/artHomeSchool")
@Api(tags = "art-HomeSchool相关接口")
public class ArtHomeSchoolController extends BaseController {

    protected static final Logger logger = LoggerFactory.getLogger(ArtHomeSchoolController.class);

    @Autowired
    private IArtHomeSchoolService artHomeSchoolServiceImpl;

    @PostMapping
    @ApiOperation(value = "新增展示学生", notes = "学生展示管理")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功", response = ArtHomeSchoolDto.class),
            @ApiResponse(code = 500, message = "参数异常")})
    public ArtHomeSchoolDto save(@RequestBody @Valid ArtHomeSchoolSaveCommand command, BindingResult bindingResult) {
        logger.info("=== command ==> {} ", command);
        validFormInfo(bindingResult);
        return artHomeSchoolServiceImpl.save(command);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取数据")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功", response = ArtHomeSchoolDto.class),
            @ApiResponse(code = 500, message = "参数异常")})
    public ArtHomeSchoolDto get(@PathVariable Long id) {
        logger.info("=== get id ==> {} ", id);
        return artHomeSchoolServiceImpl.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改展示学生", notes = "学生展示管理")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功", response = ArtHomeSchoolDto.class),
            @ApiResponse(code = 500, message = "参数异常")})
    public ArtHomeSchoolDto update(@RequestBody @Valid ArtHomeSchoolUpdateCommand command, BindingResult bindingResult) {
        logger.info("=== command ==> {} ", command);
        validFormInfo(bindingResult);
        return artHomeSchoolServiceImpl.update(command);
    }

    @GetMapping
    @ApiOperation(value = "列表")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功", response = ArtHomeSchoolDto.class),
            @ApiResponse(code = 500, message = "参数异常")})
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return artHomeSchoolServiceImpl.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<ArtHomeSchoolDto> list(PageTableRequest request) {
                Map<String, Integer> page = getPageOffsetAndLimit(request);
                return artHomeSchoolServiceImpl.list(request.getParams(), page.get("offset"), page.get("limit"));
            }
        }).handle(request);
    }

    @GetMapping("/findAll")
    @ApiOperation(value = "全部列表")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功", response = ArtHomeSchoolDto.class),
            @ApiResponse(code = 500, message = "参数异常")})
    public List<ArtHomeSchoolDto> findAll(PageTableRequest request) {
        return artHomeSchoolServiceImpl.findAll();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        artHomeSchoolServiceImpl.delete(id);
    }

}
