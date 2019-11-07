package com.edu.admin.education.controller;

import com.edu.admin.education.command.ArtBannerInfoSaveCommand;
import com.edu.admin.education.command.ArtBannerInfoUpdateCommand;
import com.edu.admin.education.dto.ArtBannerInfoDto;
import com.edu.admin.education.service.IArtBannerInfoService;
import com.edu.admin.server.page.table.PageTableHandler;
import com.edu.admin.server.page.table.PageTableHandler.CountHandler;
import com.edu.admin.server.page.table.PageTableHandler.ListHandler;
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
import java.util.Map;

/**
 * banner info 模块控制器s
 * @author mengqa
 * @date 2019-11-06
 */
@RestController
@RequestMapping("/artBannerInfo")
@Api(tags = "art-Banner相关接口")
public class ArtBannerInfoController extends BaseController {

    protected static final Logger logger = LoggerFactory.getLogger(ArtBannerInfoController.class);

    @Autowired
    private IArtBannerInfoService artBannerInfoServiceImpl;

    @PostMapping
    @ApiOperation(value = "新增Banner", notes = "Banner管理")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功", response = ArtBannerInfoDto.class),
            @ApiResponse(code = 500, message = "参数异常")})
    public ArtBannerInfoDto save(@RequestBody @Valid ArtBannerInfoSaveCommand command, BindingResult bindingResult) {
        logger.info("=== command ==> {} ", command);
        validFormInfo(bindingResult);
        return artBannerInfoServiceImpl.save(command);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取数据")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功", response = ArtBannerInfoDto.class),
            @ApiResponse(code = 500, message = "参数异常")})
    public ArtBannerInfoDto get(@PathVariable Long id) {
        logger.info("=== get id ==> {} ", id);
        return artBannerInfoServiceImpl.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改Banner", notes = "Banner管理")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功", response = ArtBannerInfoDto.class),
            @ApiResponse(code = 500, message = "参数异常")})
    public ArtBannerInfoDto update(@RequestBody @Valid ArtBannerInfoUpdateCommand command, BindingResult bindingResult) {
        logger.info("=== command ==> {} ", command);
        validFormInfo(bindingResult);
        return artBannerInfoServiceImpl.update(command);
    }

    @GetMapping
    @ApiOperation(value = "列表")
    @ApiImplicitParam(name = "site", value = "位置 1.顶部 2.中部", paramType = "query", dataType="int")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功", response = ArtBannerInfoDto.class),
            @ApiResponse(code = 500, message = "参数异常")})
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return artBannerInfoServiceImpl.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<ArtBannerInfoDto> list(PageTableRequest request) {
                Map<String, Integer> page = getPageOffsetAndLimit(request);
                return artBannerInfoServiceImpl.list(request.getParams(), page.get("offset"), page.get("limit"));
            }
        }).handle(request);
    }

    @GetMapping("/findAll")
    @ApiOperation(value = "全部列表")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功", response = ArtBannerInfoDto.class),
            @ApiResponse(code = 500, message = "参数异常")})
    public List<ArtBannerInfoDto> findAll(PageTableRequest request) {
        return artBannerInfoServiceImpl.findAll();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        artBannerInfoServiceImpl.delete(id);
    }

}
