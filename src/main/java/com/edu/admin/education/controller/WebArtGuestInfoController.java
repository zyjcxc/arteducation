package com.edu.admin.education.controller;

import com.edu.admin.education.command.ArtGuestInfoSaveCommand;
import com.edu.admin.education.dto.ArtGuestInfoDto;
import com.edu.admin.education.dto.ArtNewsDto;
import com.edu.admin.education.service.IArtGuestInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 联系我们 info 模块控制器s
 * @author mengqa
 * @date 2019-11-06
 */
@RestController
@RequestMapping("/web/artGuestInfo")
@Api(tags = "art-web-联系我们相关接口")
public class WebArtGuestInfoController extends BaseController {

    protected static final Logger logger = LoggerFactory.getLogger(WebArtGuestInfoController.class);

    @Autowired
    private IArtGuestInfoService artGuestInfoServiceImpl;

    @PostMapping
    @ApiOperation(value = "新增联系我们客户", notes = "新增联系我们客户")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功", response = ArtNewsDto.class),
            @ApiResponse(code = 500, message = "参数异常")})
//    @ApiImplicitParam(name = "age", value = "年龄", required = true, paramType = "form", dataType="int")
    public ArtGuestInfoDto save(@RequestBody @Valid ArtGuestInfoSaveCommand command, BindingResult bindingResult) {
        logger.info("=== command ==> {} ", command);
        validFormInfo(bindingResult);
        return artGuestInfoServiceImpl.save(command);
    }

}
