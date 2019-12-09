package com.edu.admin.education.controller;

import com.edu.admin.education.dto.ArtHomeSchoolDto;
import com.edu.admin.education.service.IArtHomeSchoolService;
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
 * artHomeSchool info 模块控制器s
 * @author mengqa
 * @date 2019-11-06
 */
@RestController
@RequestMapping("/web/artHomeSchool")
@Api(tags = "art-web-HomeSchool相关接口")
public class WebArtHomeSchoolController extends BaseController {

    protected static final Logger logger = LoggerFactory.getLogger(WebArtHomeSchoolController.class);

    @Autowired
    private IArtHomeSchoolService artHomeSchoolServiceImpl;

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取数据")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功", response = ArtHomeSchoolDto.class),
            @ApiResponse(code = 500, message = "参数异常")})
    public ArtHomeSchoolDto get(@PathVariable Long id) {
        logger.info("=== get id ==> {} ", id);
        return artHomeSchoolServiceImpl.getById(id);
    }

    @GetMapping("/findRecommendList")
    @ApiOperation(value = "查询需要展示的列表")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功", response = ArtHomeSchoolDto.class),
            @ApiResponse(code = 500, message = "参数异常")})
    public List<ArtHomeSchoolDto> findRecommendList() {
        return artHomeSchoolServiceImpl.findRecommendList();
    }

}
