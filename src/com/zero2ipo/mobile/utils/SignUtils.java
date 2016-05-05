package com.zero2ipo.mobile.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * 标记工具类
 */
public class SignUtils {
	
	/*
	 * 生成指定格式时间戳
	 */
	public static String generateDateSign (String dateFormat) {
		SimpleDateFormat sf = new SimpleDateFormat(dateFormat);
		return sf.format(new Date());
	}
	
	/*
	 * 生成指定位数随机数
	 */
	public static String generateRandom (int num) {
		String s = "";
		while (s.length() < num) 
		{
			s += (int) (Math.random() * 10);
		}
		return s;
	}
	
}
