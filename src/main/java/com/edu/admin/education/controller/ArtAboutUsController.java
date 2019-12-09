package com.edu.admin.education.controller;

import com.edu.admin.education.dao.ArtAboutUsDao;
import com.edu.admin.education.model.ArtAboutUs;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * 关于我们 info 模块控制器s
 * @author mengqa
 * @date 2019-11-06
 */
@RestController
@RequestMapping("/artAboutUs")
@Api(tags = "art-关于我们相关接口")
public class ArtAboutUsController extends BaseController {

    protected static final Logger logger = LoggerFactory.getLogger(ArtAboutUsController.class);

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

    @PutMapping
    @ApiOperation(value = "修改")
    public ArtAboutUs update(@RequestBody @Valid ArtAboutUs artAboutUs, BindingResult bindingResult) {
        validFormInfo(bindingResult);
        artAboutUs.setCreatetime(new Date());
        artAboutUs.setUpdatetime(new Date());
        artAboutUsDao.updateByPrimaryKey(artAboutUs);

        return null;
    }
}
