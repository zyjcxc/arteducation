package com.edu.admin.education.controller;

import com.edu.admin.education.dto.ArtTeacherDto;
import com.edu.admin.education.service.IArtTeacherService;
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
 * 老师 模块控制器s
 * @author mengqa
 * @date 2019-11-05
 */
@RestController
@RequestMapping("/web/artTeacher")
@Api(tags = "art-web-老师相关接口")
public class WebArtTeacherController extends BaseController {

    protected static final Logger logger = LoggerFactory.getLogger(WebArtTeacherController.class);

    @Autowired
    private IArtTeacherService artTeacherServiceImpl;

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取数据")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功", response = ArtTeacherDto.class),
            @ApiResponse(code = 500, message = "参数异常")})
    public ArtTeacherDto get(@PathVariable Long id) {
        logger.info("=== get id ==> {} ", id);
        return artTeacherServiceImpl.getById(id);
    }

    @GetMapping
    @ApiOperation(value = "列表")
    @ApiImplicitParam(name = "name", value = "老师姓名", paramType = "query", dataType="string")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功", response = ArtTeacherDto.class),
            @ApiResponse(code = 500, message = "参数异常")})
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return artTeacherServiceImpl.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<ArtTeacherDto> list(PageTableRequest request) {
                Map<String, Integer> page = getPageOffsetAndLimit(request);
                return artTeacherServiceImpl.list(request.getParams(), page.get("offset"), page.get("limit"));
            }
        }).handle(request);
    }

    @GetMapping("/findAll")
    @ApiOperation(value = "全部列表")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功", response = ArtTeacherDto.class),
            @ApiResponse(code = 500, message = "参数异常")})
    public List<ArtTeacherDto> findAll(PageTableRequest request) {
        return artTeacherServiceImpl.findAll();
    }

}
