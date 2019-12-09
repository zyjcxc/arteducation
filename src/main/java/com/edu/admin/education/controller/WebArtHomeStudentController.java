package com.edu.admin.education.controller;

import com.edu.admin.education.dto.ArtHomeStudentDto;
import com.edu.admin.education.service.IArtHomeStudentService;
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
 * artHomeStudent info 模块控制器s
 * @author mengqa
 * @date 2019-11-06
 */
@RestController
@RequestMapping("/web/artHomeStudent")
@Api(tags = "art-web-HomeStudent相关接口")
public class WebArtHomeStudentController extends BaseController {

    protected static final Logger logger = LoggerFactory.getLogger(WebArtHomeStudentController.class);

    @Autowired
    private IArtHomeStudentService artHomeStudentServiceImpl;

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取数据")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功", response = ArtHomeStudentDto.class),
            @ApiResponse(code = 500, message = "参数异常")})
    public ArtHomeStudentDto get(@PathVariable Long id) {
        logger.info("=== get id ==> {} ", id);
        return artHomeStudentServiceImpl.getById(id);
    }

    @GetMapping("/findRecommendList")
    @ApiOperation(value = "查询需要展示的列表")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功", response = ArtHomeStudentDto.class),
            @ApiResponse(code = 500, message = "参数异常")})
    public List<ArtHomeStudentDto> findRecommendList() {
        return artHomeStudentServiceImpl.findRecommendList();
    }

}
