package com.edu.admin.server.config;

import cn.jiguang.common.ClientConfig;
import cn.jpush.api.JPushClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 极光推送
 *
 * @author mengqa
 **/
@Configuration
public class JgPushConfig {
//    /**
//     * 正用式环境用key
//     */
//    private String key = "3d7edf098db7eebc8e578413";
//    /**
//     * 正用式环境用secret
//     */
//    private String secret = "e3e18d1e9b7296a153a5f906";

    /**
     * 测试环境用key
     */
    private String key = "fc531e91ae7e1c7ad9e78743";
    /**
     * 测试环境用secret
     */
    private String secret = "0076a6c44b19b6316fcf869d";

    private JPushClient jPushClient;

    @Bean("jPushConfig")
    public JPushClient jPushConfig() {
        ClientConfig clientConfig = ClientConfig.getInstance();
//        clientConfig.setTimeToLive(1000);
        if (jPushClient == null) {
            jPushClient = new JPushClient( secret, key,null, clientConfig);
        }
        return jPushClient;
    }


}
