package com.edu.admin.server.controller;

import com.edu.admin.server.config.QiNiuAccountConfig;
import com.qiniu.util.Auth;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "七牛调用接口")
@RestController
@RequestMapping("/qiniu")
public class QiniuTokenController {


    @Autowired
    private Auth auth;

    @Autowired
    private QiNiuAccountConfig qiNiuAccountConfig;


    /**
     * 获得token,此token是上传图片的空间
     * @return
     */
    @GetMapping("/token")
    @ApiOperation(value = "获得token")
    public String getToken() {

        return auth.uploadToken(qiNiuAccountConfig.getBucketname());
    }

    /**
     * 获得token,此token是上传视频的空间
     * @return
     */
    @GetMapping("/tokenVideo")
    @ApiOperation(value = "获得token")
    public String getVideoToken() {

        return auth.uploadToken(qiNiuAccountConfig.getVideobucketname());
    }


    @GetMapping("/domain")
    @ApiOperation(value = "获得Domain")
    public String Domain() {

        return qiNiuAccountConfig.getDomain();
    }
}
