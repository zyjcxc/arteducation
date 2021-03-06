package com.edu.admin.education.controller;

import com.edu.admin.education.dto.ArtAwardDto;
import com.edu.admin.education.service.IArtAwardService;
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
 * award 模块控制器s
 * @author mengqa
 * @date 2019-11-06
 */
@RestController
@RequestMapping("/web/artAward")
@Api(tags = "art-web-证书相关接口")
public class WebArtAwardController extends BaseController {

    protected static final Logger logger = LoggerFactory.getLogger(WebArtAwardController.class);

    @Autowired
    private IArtAwardService artAwardServiceImpl;

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取数据")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功", response = ArtAwardDto.class),
            @ApiResponse(code = 500, message = "参数异常")})
    public ArtAwardDto get(@PathVariable Long id) {
        logger.info("=== get id ==> {} ", id);
        return artAwardServiceImpl.getById(id);
    }

    @GetMapping
    @ApiOperation(value = "列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "textbookTypeId", value = "教材分类id", paramType = "query", dataType="int"),
            @ApiImplicitParam(name = "title", value = "教材名", paramType = "query", dataType="string")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功", response = ArtAwardDto.class),
            @ApiResponse(code = 500, message = "参数异常")})
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return artAwardServiceImpl.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<ArtAwardDto> list(PageTableRequest request) {
                Map<String, Integer> page = getPageOffsetAndLimit(request);
                return artAwardServiceImpl.list(request.getParams(), page.get("offset"), page.get("limit"));
            }
        }).handle(request);
    }

    @GetMapping("/findAll")
    @ApiOperation(value = "全部列表")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功", response = ArtAwardDto.class),
            @ApiResponse(code = 500, message = "参数异常")})
    public List<ArtAwardDto> findAll(PageTableRequest request) {
        return artAwardServiceImpl.findAll();
    }


}
