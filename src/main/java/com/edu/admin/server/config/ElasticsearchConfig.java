//package com.edu.admin.server.config;
//
//import org.elasticsearch.client.transport.TransportClient;
//import org.elasticsearch.common.settings.Settings;
//import org.elasticsearch.common.transport.InetSocketTransportAddress;
//import org.elasticsearch.transport.client.PreBuiltTransportClient;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.net.InetAddress;
//import java.net.UnknownHostException;
//
//@Configuration
//public class ElasticsearchConfig {
//
//
//    @Autowired
//    private ElasticsearchAccount elasticsearchAccount;
//
//    @Bean
//    public TransportClient client() throws UnknownHostException {
//
//        InetSocketTransportAddress node = new InetSocketTransportAddress(InetAddress.getByName(elasticsearchAccount.getIp()),elasticsearchAccount.getPort());
//
//        Settings settings= Settings.builder().put("cluster.name","elasticsearch").build();
//
//        TransportClient client = new PreBuiltTransportClient(settings);
//
//        client.addTransportAddress(node);
//
//        return  client;
//    }
//
//}
