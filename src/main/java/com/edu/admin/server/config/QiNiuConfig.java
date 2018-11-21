package com.edu.admin.server.config;


import com.qiniu.common.Zone;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 七牛相关bean配置
 *
 * @author xuqiang
 * @create 2017-12-21 下午4:00
 **/
@Component
@Log4j
public class QiNiuConfig {

    @Autowired
    private QiNiuAccountConfig qiNiuAccountConfig;




    @Bean
    public UploadManager uploadManager() {
        Zone z = Zone.autoZone();
        Configuration c = new Configuration(z);
        return new UploadManager(c);
    }

    @Bean
    public Auth auth() {
        Auth auth = Auth.create(qiNiuAccountConfig.getKey(), qiNiuAccountConfig.getSecret());
        return auth;
    }
}
