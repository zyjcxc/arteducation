package com.edu.admin.server.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *
 *  el账户配置类
 * @author 许强
 * @date 2018/05/25
 */
@Data
@ConfigurationProperties(prefix = "es")
@Component
public class ElasticsearchAccount {


    private String ip;

    private Integer port;

    private String clusterName;
}
