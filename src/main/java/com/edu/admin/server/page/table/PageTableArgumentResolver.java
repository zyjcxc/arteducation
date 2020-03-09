package com.edu.admin.server.page.table;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 分页、查询参数解析
 * 
 * @author 小威老师
 *
 */
public class PageTableArgumentResolver implements HandlerMethodArgumentResolver {

	protected static final Logger logger = LoggerFactory.getLogger(PageTableArgumentResolver.class);

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		Class<?> cla = parameter.getParameterType();

		return cla.isAssignableFrom(PageTableRequest.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);

		PageTableRequest tableRequest = new PageTableRequest();
		Map<String, String[]> param = request.getParameterMap();

		StringBuilder kv = new StringBuilder();

		int start = 0;
		int limit = 10;
		if (param.containsKey("start")) {
			start = Integer.parseInt(request.getParameter("start"));
			tableRequest.setOffset(start);
		}

		if (param.containsKey("length")) {
			tableRequest.setLimit(Integer.parseInt(request.getParameter("length")));
			// 1 0  2 10 3 20
			int currentPage = (start / limit) + 1;
			tableRequest.setCurrentPage(currentPage);
		}

		Map<String, Object> map = Maps.newHashMap();
		tableRequest.setParams(map);

		param.forEach((k, v) -> {
			if (v.length == 1) {
				map.put(k, v[0]);
			} else {
				map.put(k, Arrays.asList(v));
			}
			if (k.contains("columns")) {
				return;
			}
			if (v.length == 1) {
				kv.append(k).append("=").append(v[0]).append(", ");
			} else {
				kv.append(k).append("=").append("[");
				for (String s : v) {
					kv.append(s).append(",");
				}
				kv.append("]").append(", ");
			}
		});

		logger.info("=== resolveArgument param === {}" , kv.toString());

		String requestURI = request.getRequestURI();
		boolean isNameHandler = true;
		if (requestURI.contains("/roles")) {
			isNameHandler = false;
		}
		setOrderBy(tableRequest, map, isNameHandler);
		removeParam(tableRequest);

		return tableRequest;
	}

	/**
	 * 去除datatables分页带的一些复杂参数
	 * 
	 * @param tableRequest
	 */
	private void removeParam(PageTableRequest tableRequest) {
		Map<String, Object> map = tableRequest.getParams();

		if (!CollectionUtils.isEmpty(map)) {
			Map<String, Object> param = new HashMap<>();
			map.forEach((k, v) -> {
				if (k.indexOf("[") < 0 && k.indexOf("]") < 0 && !"_".equals(k)) {
					param.put(k, v);
				}
				if (v instanceof String && "".equals(v)) {
					param.remove(k);
				}
			});

			tableRequest.setParams(param);
		}
	}

	/**
	 * 从datatables分页请求数据中解析排序
	 * 
	 * @param tableRequest
	 * @param map
	 */
	private void setOrderBy(PageTableRequest tableRequest, Map<String, Object> map, boolean isNameHandler) {
		StringBuilder orderBy = new StringBuilder();
		int size = map.size();
		OrderByObject order = new OrderByObject();
		for (int i = 0; i < size; i++) {
			// 排序的columns[?]是哪个
			String index = (String) map.get("order[" + i + "][column]");
			if (StringUtils.isEmpty(index)) {
				// 如果不排序的话
				break;
			}
			// 排序的字段名
			String column = (String) map.get("columns[" + index + "][data]");
			if (StringUtils.isBlank(column)) {
				continue;
			}
			order.setColumn(column);
			// 排序是asc 还是 desc
			String sort = (String) map.get("order[" + i + "][dir]");
			if (sort.equals("asc")) {
				order.setAsc(true);
			}

			if (isNameHandler) {
				// 表名转换
				orderBy.append(NameHandler.humpToLine(column)).append(" ").append(sort).append(", ");
			} else {
				orderBy.append(column).append(" ").append(sort).append(", ");
			}
		}

		if (orderBy.length() > 0) {
			tableRequest.getParams().put("orderBy",
					" order by " + StringUtils.substringBeforeLast(orderBy.toString(), ","));
			order.setOrderBy(true);
			tableRequest.setOrderByObject(order);
		}
	}

}
