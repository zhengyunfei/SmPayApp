package com.zero2ipo.mobile.web;

import javax.servlet.http.HttpServletRequest;

/**
 * session帮助类
 * @author zhengyunfei
 *
 */
public class SessionHelper {
	
	/**
	 * 向session中存放数据
	 */
	public static void setAttribute(HttpServletRequest request, String key, Object o) {
		request.getSession().setAttribute(key, o);
	}
	
	/**
	 * 取出session中数据
	 */
	public static Object getAttribute(HttpServletRequest request, String key) {
		return request.getSession().getAttribute(key);
	}
	
	/**
	 * 获取String类型属性
	 */
	public static String getStringAttribute(HttpServletRequest request, String key) {
		String strAttr = null;
		Object o = request.getSession().getAttribute(key);
		if(o != null) 
		{
			strAttr = (String)o;
		}
		return strAttr;
	} 
	
	/**
	 * 移除session中的某一条记录
	 */
	public static void removeAttribute(HttpServletRequest request, String key) {
		request.getSession().removeAttribute(key);
	}

	/**
	 * 清空session
	 */
	public static void invalidate(HttpServletRequest request) {
		// TODO Auto-generated method stub
		request.getSession().invalidate();
		
	}
	
}
