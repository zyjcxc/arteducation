package com.edu.admin.education.controller;

import com.edu.admin.education.dto.ArtGuestInfoDto;
import com.edu.admin.education.service.IArtGuestInfoService;
import com.edu.admin.server.page.table.PageTableHandler;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 联系我们 info 模块控制器s
 * @author mengqa
 * @date 2019-11-06
 */
@RestController
@RequestMapping("/artGuestInfo")
@Api(tags = "art-联系我们相关接口")
public class ArtGuestInfoController extends BaseController {

    protected static final Logger logger = LoggerFactory.getLogger(ArtGuestInfoController.class);

    @Autowired
    private IArtGuestInfoService artGuestInfoServiceImpl;

    @GetMapping
    @ApiOperation(value = "列表")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功", response = ArtGuestInfoDto.class),
            @ApiResponse(code = 500, message = "参数异常")})
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new PageTableHandler.CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return artGuestInfoServiceImpl.count(request.getParams());
            }
        }, new PageTableHandler.ListHandler() {

            @Override
            public List<ArtGuestInfoDto> list(PageTableRequest request) {
                Map<String, Integer> page = getPageOffsetAndLimit(request);
                return artGuestInfoServiceImpl.list(request.getParams(), page.get("offset"), page.get("limit"));
            }
        }).handle(request);
    }

}
