package com.edu.admin.server.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 七牛上传实体属性类
 *
 * @author xuqiang
 * @create 2017-11-28 下午4:47
 **/
@Data
@ConfigurationProperties(prefix = "qn")
@Component
public class QiNiuAccountConfig {

    /**
     * 七牛key
     */
    private String key;

    /**
     * 七牛secret
     */
    private String secret;

    /**
     * 七牛域
     */
    private String bucketname;

    /**
     * 七牛域名地址
     */
    private String domain;

    /**
     * 上传视频用到的bucket
     */
    private String  videobucketname;

    /**
     * 上传视频用到的domain
     */
    private String videodomain;



}
