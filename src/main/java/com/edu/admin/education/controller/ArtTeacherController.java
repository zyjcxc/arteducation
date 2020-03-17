package com.edu.admin.education.controller;

import com.edu.admin.education.command.ArtTeacherSaveCommand;
import com.edu.admin.education.command.ArtTeacherUpdateCommand;
import com.edu.admin.education.dto.ArtTeacherDto;
import com.edu.admin.education.service.IArtTeacherService;
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
 * 老师 模块控制器s
 * @author mengqa
 * @date 2019-11-05
 */
@RestController
@RequestMapping("/artTeacher")
@Api(tags = "art-老师相关接口")
public class ArtTeacherController extends BaseController {

    protected static final Logger logger = LoggerFactory.getLogger(ArtTeacherController.class);

    @Autowired
    private IArtTeacherService artTeacherServiceImpl;

    @PostMapping
    @ApiOperation(value = "新增老师", notes = "师资团队管理")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功", response = ArtTeacherDto.class),
            @ApiResponse(code = 500, message = "参数异常")})
    public ArtTeacherDto save(@RequestBody @Valid ArtTeacherSaveCommand command, BindingResult bindingResult) {
        logger.info("=== command ==> {} ", command);
        validFormInfo(bindingResult);
        return artTeacherServiceImpl.save(command);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取数据")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功", response = ArtTeacherDto.class),
            @ApiResponse(code = 500, message = "参数异常")})
    public ArtTeacherDto get(@PathVariable Long id) {
        logger.info("=== get id ==> {} ", id);
        return artTeacherServiceImpl.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改老师", notes = "师资团队管理")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功", response = ArtTeacherDto.class),
            @ApiResponse(code = 500, message = "参数异常")})
    public ArtTeacherDto update(@RequestBody @Valid ArtTeacherUpdateCommand command, BindingResult bindingResult) {
        logger.info("=== command ==> {} ", command);
        validFormInfo(bindingResult);
        return artTeacherServiceImpl.update(command);
    }

    @GetMapping
    @ApiOperation(value = "列表")
    @ApiImplicitParam(name = "name", value = "老师姓名", paramType = "query", dataType="string")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功", response = ArtTeacherDto.class),
            @ApiResponse(code = 500, message = "参数异常")})
    public PageTableResponse list(PageTableRequest request) {
        return artTeacherServiceImpl.queryList(request);
    }

    @GetMapping("/findAll")
    @ApiOperation(value = "全部列表")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功", response = ArtTeacherDto.class),
            @ApiResponse(code = 500, message = "参数异常")})
    public List<ArtTeacherDto> findAll(PageTableRequest request) {
        return artTeacherServiceImpl.findAll();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        artTeacherServiceImpl.delete(id);
    }

}
