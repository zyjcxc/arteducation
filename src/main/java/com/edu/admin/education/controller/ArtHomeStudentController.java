package com.edu.admin.education.controller;

import com.edu.admin.education.command.ArtHomeStudentSaveCommand;
import com.edu.admin.education.command.ArtHomeStudentUpdateCommand;
import com.edu.admin.education.dto.ArtHomeStudentDto;
import com.edu.admin.education.service.IArtHomeStudentService;
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
@RequestMapping("/artHomeStudent")
@Api(tags = "art-HomeStudent相关接口")
public class ArtHomeStudentController extends BaseController {

    protected static final Logger logger = LoggerFactory.getLogger(ArtHomeStudentController.class);

    @Autowired
    private IArtHomeStudentService artHomeStudentServiceImpl;

    @PostMapping
    @ApiOperation(value = "新增展示学生", notes = "学生展示管理")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功", response = ArtHomeStudentDto.class),
            @ApiResponse(code = 500, message = "参数异常")})
    public ArtHomeStudentDto save(@RequestBody @Valid ArtHomeStudentSaveCommand command, BindingResult bindingResult) {
        logger.info("=== command ==> {} ", command);
        validFormInfo(bindingResult);
        return artHomeStudentServiceImpl.save(command);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取数据")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功", response = ArtHomeStudentDto.class),
            @ApiResponse(code = 500, message = "参数异常")})
    public ArtHomeStudentDto get(@PathVariable Long id) {
        logger.info("=== get id ==> {} ", id);
        return artHomeStudentServiceImpl.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改展示学生", notes = "学生展示管理")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功", response = ArtHomeStudentDto.class),
            @ApiResponse(code = 500, message = "参数异常")})
    public ArtHomeStudentDto update(@RequestBody @Valid ArtHomeStudentUpdateCommand command, BindingResult bindingResult) {
        logger.info("=== command ==> {} ", command);
        validFormInfo(bindingResult);
        return artHomeStudentServiceImpl.update(command);
    }

    @GetMapping
    @ApiOperation(value = "列表")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功", response = ArtHomeStudentDto.class),
            @ApiResponse(code = 500, message = "参数异常")})
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return artHomeStudentServiceImpl.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<ArtHomeStudentDto> list(PageTableRequest request) {
                Map<String, Integer> page = getPageOffsetAndLimit(request);
                return artHomeStudentServiceImpl.list(request.getParams(), page.get("offset"), page.get("limit"));
            }
        }).handle(request);
    }

    @GetMapping("/findAll")
    @ApiOperation(value = "全部列表")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功", response = ArtHomeStudentDto.class),
            @ApiResponse(code = 500, message = "参数异常")})
    public List<ArtHomeStudentDto> findAll(PageTableRequest request) {
        return artHomeStudentServiceImpl.findAll();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        artHomeStudentServiceImpl.delete(id);
    }

}
