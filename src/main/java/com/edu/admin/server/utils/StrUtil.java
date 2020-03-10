package com.edu.admin.server.utils;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 字符串转化工具类
 * 
 * @author 小威老师
 *
 */
public class StrUtil {

	/**
	 * 字符串转为驼峰
	 * 
	 * @param str
	 * @return
	 */
	public static String str2hump(String str) {
		StringBuffer buffer = new StringBuffer();
		if (str != null && str.length() > 0) {
			if (str.contains("_")) {
				String[] chars = str.split("_");
				int size = chars.length;
				if (size > 0) {
					List<String> list = Lists.newArrayList();
					for (String s : chars) {
						if (s != null && s.trim().length() > 0) {
							list.add(s);
						}
					}

					size = list.size();
					if (size > 0) {
						buffer.append(list.get(0));
						for (int i = 1; i < size; i++) {
							String s = list.get(i);
							buffer.append(s.substring(0, 1).toUpperCase());
							if (s.length() > 1) {
								buffer.append(s.substring(1));
							}
						}
					}
				}
			} else {
				buffer.append(str);
			}
		}

		return buffer.toString();
	}

	/***
	 * 驼峰命名转为下划线命名
	 *
	 * @param para
	 *        驼峰命名的字符串
	 */

	public static String humpToUnderline(String para){
		StringBuilder sb=new StringBuilder(para);
		int temp=0;//定位
		if (!para.contains("_")) {
			for(int i=0;i<para.length();i++){
				if(Character.isUpperCase(para.charAt(i))){
					sb.insert(i+temp, "_");
					temp+=1;
				}
			}
		}
		return sb.toString().toUpperCase();
	}

}
