package com.edu.admin.education.controller;

import com.edu.admin.education.dto.ArtBannerInfoDto;
import com.edu.admin.education.model.ArtTeacherAuth;
import com.edu.admin.education.service.IArtTeacherAuthService;
import com.edu.admin.server.page.table.PageTableHandler;
import com.edu.admin.server.page.table.PageTableHandler.CountHandler;
import com.edu.admin.server.page.table.PageTableHandler.ListHandler;
import com.edu.admin.server.page.table.PageTableRequest;
import com.edu.admin.server.page.table.PageTableResponse;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 模块控制器
 * @author mengqa
 * @date 2018-04-01
 */
@RestController
@RequestMapping("/web/artTeacherAuth")
@Api(tags = "art-web-学生证书相关接口")
public class WebArtTeacherAuthController {

    @Autowired
    private IArtTeacherAuthService artTeacherAuthService;

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public ArtTeacherAuth get(@PathVariable Long id) {
        return artTeacherAuthService.getById(id);
    }


    @GetMapping("/list")
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return artTeacherAuthService.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<ArtTeacherAuth> list(PageTableRequest request) {
                return artTeacherAuthService.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @GetMapping("/getByCondition")
    @ApiOperation(value = "根据条件查询证书详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "姓名 必填", paramType = "query", dataType="string"),
            @ApiImplicitParam(name = "bookNo", value = "证书编号 必填", paramType = "query", dataType="string"),
            @ApiImplicitParam(name = "bookType", value = "证书类型 1.白皮 2.红皮 必填", paramType = "query", dataType="int")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功", response = ArtBannerInfoDto.class),
            @ApiResponse(code = 500, message = "参数异常")})
    public ArtTeacherAuth getByCondition(PageTableRequest request) {
        return artTeacherAuthService.getByCondition(request.getParams());
    }


}
