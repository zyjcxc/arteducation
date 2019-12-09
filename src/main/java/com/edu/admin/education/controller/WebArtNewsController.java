package com.edu.admin.education.controller;

import com.edu.admin.education.dto.ArtNewsDto;
import com.edu.admin.education.service.IArtNewsService;
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
 * 新闻中心 模块控制器s
 * @author mengqa
 * @date 2019-11-05
 */
@RestController
@RequestMapping("/web/artNews")
@Api(tags = "art-web-新闻中心、公告相关接口")
public class WebArtNewsController extends BaseController {

    protected static final Logger logger = LoggerFactory.getLogger(WebArtNewsController.class);

    @Autowired
    private IArtNewsService artNewsServiceImpl;

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取数据")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功", response = ArtNewsDto.class),
            @ApiResponse(code = 500, message = "参数异常")})
    public ArtNewsDto get(@PathVariable Long id) {
        logger.info("=== get id ==> {} ", id);
        return artNewsServiceImpl.getById(id);
    }

    @GetMapping
    @ApiOperation(value = "列表")
    @ApiImplicitParam(name = "type", value = "类型 type=1 新闻 type=2 公告", required = true, paramType = "query", dataType="int")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功", response = ArtNewsDto.class),
            @ApiResponse(code = 500, message = "参数异常")})
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return artNewsServiceImpl.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<ArtNewsDto> list(PageTableRequest request) {
                Map<String, Integer> page = getPageOffsetAndLimit(request);
                return artNewsServiceImpl.list(request.getParams(), page.get("offset"), page.get("limit"));
            }
        }).handle(request);
    }

    @GetMapping("/findAll")
    @ApiOperation(value = "全部列表")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功", response = ArtNewsDto.class),
            @ApiResponse(code = 500, message = "参数异常")})
    public List<ArtNewsDto> findAll(PageTableRequest request) {
        return artNewsServiceImpl.findAll();
    }

}
