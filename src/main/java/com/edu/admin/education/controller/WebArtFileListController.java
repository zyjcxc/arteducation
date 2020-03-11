package com.edu.admin.education.controller;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * file list 模块控制器s
 * @author mengqa
 * @date 2019-12-19
 */
@RestController
@RequestMapping("/web/artFileList")
@Api(tags = "art-web-文件管理相关接口")
public class WebArtFileListController extends BaseController {

    protected static final Logger logger = LoggerFactory.getLogger(WebArtFileListController.class);

    @Autowired
    private IArtFileListService artFileListServiceImpl;

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取数据")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功", response = ArtFileListDto.class),
            @ApiResponse(code = 500, message = "参数异常")})
    public ArtFileListDto get(@PathVariable Long id) {
        logger.info("=== get id ==> {} ", id);
        return artFileListServiceImpl.getById(id);
    }

    @GetMapping
    @ApiOperation(value = "列表")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功", response = ArtFileListDto.class),
            @ApiResponse(code = 500, message = "参数异常")})
    public PageTableResponse list(PageTableRequest request) {
//        return new PageTableHandler(new CountHandler() {
////
////            @Override
////            public int count(PageTableRequest request) {
////                return artFileListServiceImpl.count(request.getParams());
////            }
////        }, new ListHandler() {
////
////            @Override
////            public List<ArtFileListDto> list(PageTableRequest request) {
////                Map<String, Integer> page = getPageOffsetAndLimit(request);
////                return artFileListServiceImpl.list(request.getParams(), page.get("offset"), page.get("limit"));
////            }
////        }).handle(request);
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


}
