package com.edu.admin.education.controller;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * banner info 模块控制器s
 * @author mengqa
 * @date 2019-11-06
 */
@RestController
@RequestMapping("/web/artBannerInfo")
@Api(tags = "art-web-Banner相关接口")
public class WebArtBannerInfoController extends BaseController {

    protected static final Logger logger = LoggerFactory.getLogger(WebArtBannerInfoController.class);

    @Autowired
    private IArtBannerInfoService artBannerInfoServiceImpl;

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取数据")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功", response = ArtBannerInfoDto.class),
            @ApiResponse(code = 500, message = "参数异常")})
    public ArtBannerInfoDto get(@PathVariable Long id) {
        logger.info("=== get id ==> {} ", id);
        return artBannerInfoServiceImpl.getById(id);
    }

    @GetMapping
    @ApiOperation(value = "列表")
    @ApiImplicitParam(name = "site", value = "位置 1.首页顶部 2.其他页面顶部 3.首页底部", paramType = "query", dataType="int")
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

    @GetMapping("/findAllBySite")
    @ApiOperation(value = "根据位置查询全部列表")
    @ApiImplicitParam(name = "site", value = "位置", paramType = "query", dataType="int")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功", response = ArtBannerInfoDto.class),
            @ApiResponse(code = 500, message = "参数异常")})
    public List<ArtBannerInfoDto> findAllBySite(PageTableRequest request) {
        return artBannerInfoServiceImpl.findAllBySite(request.getParams());
    }

}
