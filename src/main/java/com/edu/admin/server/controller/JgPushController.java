package com.edu.admin.server.controller;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.PushPayload;
import com.edu.admin.server.config.JgPushConfig;
import com.edu.admin.server.jpush.PushInformation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mengqa
 * @create 2018-04-26 11:03
 **/
@Api(tags = "极光推送")
@RestController
@RequestMapping("/jgPush")
public class JgPushController {

    private static final Logger LOG = LoggerFactory.getLogger(JgPushController.class);

    @Autowired
    private JgPushConfig jPushConfig;

    @ApiOperation(value = "推送测试")
    @GetMapping("/push")
    public void push() {
        JPushClient jPushClient = jPushConfig.jPushConfig();
//        List<String> users = new ArrayList<>();
//        users.add("15840506867");
//        users.add("13940081004");
//        PushPayload payload = JgPushUtil.send("tit", "cont");
        PushInformation info = new PushInformation("荣1111111", "标题");
        PushPayload payload = info.getPushPayLoad();
        try {
            PushResult result = jPushClient.sendPush(payload);
            LOG.info("推送结果 - " + result);
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
            LOG.error("Sendno: " + payload.getSendno());
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
            LOG.info("Msg ID: " + e.getMsgId());
            LOG.error("Sendno: " + payload.getSendno());
        }
    }

}
