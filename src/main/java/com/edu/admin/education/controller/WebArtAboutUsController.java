package com.edu.admin.education.controller;

import com.edu.admin.education.dao.ArtAboutUsDao;
import com.edu.admin.education.model.ArtAboutUs;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 关于我们 info 模块控制器s
 * @author mengqa
 * @date 2019-11-06
 */
@RestController
@RequestMapping("/web/artAboutUs")
@Api(tags = "art-web-关于我们相关接口")
public class WebArtAboutUsController extends BaseController {

    protected static final Logger logger = LoggerFactory.getLogger(WebArtAboutUsController.class);

    @Autowired
    private ArtAboutUsDao artAboutUsDao;

    @GetMapping
    @ApiOperation(value = "查询关于我们数据")
    public ArtAboutUs findOne() {
        List<ArtAboutUs> artAboutUses = artAboutUsDao.selectAll();
        if (!CollectionUtils.isEmpty(artAboutUses)) {
            return artAboutUses.get(0);
        }
        return null;
    }
}
