package com.edu.admin;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;

import javax.servlet.MultipartConfigElement;
//import org.springframework.jms.annotation.EnableJms;

/**
 * 启动类
 * 
 * @author 小威老师
 *
 *         2017年4月18日
 */
//@EnableJms
@SpringBootApplication
@MapperScan(basePackages = {"com.edu.admin.server.dao", "com.edu.admin.education.dao"})
@Slf4j
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

	/**
	 * 文件上传临时路径
	 */
	@Bean
	MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		// TODO 线上
		factory.setLocation("/data/temp");
		//
//		 factory.setLocation("D:/data/temp");
		return factory.createMultipartConfig();
	}
}
