package com.edu.admin.education.controller;

import com.edu.admin.education.dto.ArtTextbookTypeDto;
import com.edu.admin.education.service.IArtTextbookTypeService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * textbookType 模块控制器s
 * @author mengqa
 * @date 2019-11-06
 */
@RestController
@RequestMapping("/web/artTextbookType")
@Api(tags = "art-web-教材分类相关接口")
public class WebArtTextbookTypeController extends BaseController {

    protected static final Logger logger = LoggerFactory.getLogger(WebArtTextbookTypeController.class);

    @Autowired
    private IArtTextbookTypeService artTextbookTypeServiceImpl;

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取数据")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功", response = ArtTextbookTypeDto.class),
            @ApiResponse(code = 500, message = "参数异常")})
    public ArtTextbookTypeDto get(@PathVariable Long id) {
        logger.info("=== get id ==> {} ", id);
        return artTextbookTypeServiceImpl.getById(id);
    }

    @GetMapping
    @ApiOperation(value = "列表")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功", response = ArtTextbookTypeDto.class),
            @ApiResponse(code = 500, message = "参数异常")})
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return artTextbookTypeServiceImpl.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<ArtTextbookTypeDto> list(PageTableRequest request) {
                Map<String, Integer> page = getPageOffsetAndLimit(request);
                return artTextbookTypeServiceImpl.list(request.getParams(), page.get("offset"), page.get("limit"));
            }
        }).handle(request);
    }

    @GetMapping("/findAll")
    @ApiOperation(value = "全部列表")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功", response = ArtTextbookTypeDto.class),
            @ApiResponse(code = 500, message = "参数异常")})
    public List<ArtTextbookTypeDto> findAll(PageTableRequest request) {
        return artTextbookTypeServiceImpl.findAll();
    }

}
