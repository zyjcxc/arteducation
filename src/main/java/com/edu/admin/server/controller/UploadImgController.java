package com.edu.admin.server.controller;

import com.alibaba.fastjson.JSON;
import com.edu.admin.server.config.QiNiuAccountConfig;
import com.edu.admin.server.dto.KindeditorResultVo;
import com.edu.admin.server.dto.QiNiuVo;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 七牛图片上传控制器
 *
 * @author xuqiang
 * @create 2017-12-26 上午11:43
 **/
@RestController
@RequestMapping("/api/upload")
@Slf4j
public class UploadImgController {


    @Autowired
    private UploadManager uploadManager;

    @Autowired
    private QiNiuAccountConfig qiNiuAccountConfig;

    @Autowired
    private Auth auth;

    @PostMapping(value = "/uploadImg")
    public String upload(@RequestParam(value = "imgFile", required = false) MultipartFile file) {
        KindeditorResultVo kindeditorResultVo = new KindeditorResultVo();
        kindeditorResultVo.setUrl("http://www.baidu.com");
        String fileName = String.valueOf(System.currentTimeMillis());
        QiNiuVo qiNiuVo;
        try {
            //调用put方法上传
            Response res = uploadManager.put(IOUtils.toByteArray(file.getInputStream()), fileName, auth.uploadToken(qiNiuAccountConfig.getBucketname()));
            qiNiuVo = JSON.parseObject(res.bodyString(), QiNiuVo.class);
            //打印返回的信息
            log.info("上传成功返回:{}", res.bodyString());
            kindeditorResultVo.setError(0);
            kindeditorResultVo.setUrl(qiNiuAccountConfig.getDomain().concat(qiNiuVo.getKey()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSON.toJSONString(kindeditorResultVo);
    }
}
