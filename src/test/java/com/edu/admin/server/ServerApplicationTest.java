//package com.edu.admin.server;
//
//import com.alibaba.fastjson.JSONObject;
////import com.edu.admin.server.utils.EsUtil;
//import com.edu.admin.education.dao.ZhTableDao;
//import com.edu.admin.education.model.ZhTable;
//import org.elasticsearch.client.transport.TransportClient;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.Collections;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class ServerApplicationTest {
//
////	@Autowired
////	private TransportClient transportClient;
//	@Autowired
//	private ZhTableDao zhTableDao;
//
//	@Test
//	public void test1() {
//
//		List<ZhTable> allList = zhTableDao.selectAll();
//
//		List<ZhTable> noList = allList.stream().filter(dto -> {
//			if ((dto.getMykey().equalsIgnoreCase("c01")	&& dto.getVal().equalsIgnoreCase("0"))
//					|| (dto.getMykey().equalsIgnoreCase("c02")	&& dto.getVal().equalsIgnoreCase("0"))
//					|| (dto.getMykey().equalsIgnoreCase("c03")	&& dto.getVal().equalsIgnoreCase("0"))
//					) {
//				return true;
//			}
//			return false;
//		}).collect(Collectors.toList());
////		List<ZhTable> c01 = allList.stream()
////				.filter(dto -> noList.stream().anyMatch(obj -> {
////					return obj.getMykey().equalsIgnoreCase("c019");
//////					return true;
////				})).collect(Collectors.toList());
//11
//		allList.forEach(dto -> {
//			noList.forEach(nDto -> {
//				if (dto.getMykey().equalsIgnoreCase(nDto.getMykey())) {
//
//				}
//			});
//		});
//		System.out.println("");
//				/*.forEach(dto -> {
//					// to do sth
//					System.out.println(dto);
//				});*/
//	}
//}
