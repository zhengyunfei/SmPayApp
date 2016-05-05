package com.zero2ipo.mobile.web;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.ContextLoader;

/**
 * 处理URL请求的类
 * @author zhengyunfei
 *
 */
public class URLHelper {
	
	private static final String URL_TYPE = "/url/((\\w|\\d|(/)?)+).html";
	
	private static final String SPRIT = "/";
	
	private static final String HTML_PATH = "html/mobile";
	private static final String SUFFIX = ".html";
	
	private static final String MOBILE_PACKAGE = "mobile";
	
	/**
	 * 获取URL请求中的静态资源路径
	 * @param request
	 * @return
	 */
	public static String getStatePath(HttpServletRequest request) {
		
		return new StringBuffer(MOBILE_PACKAGE).append(SPRIT).append(getUrlPath(getURI(request))).toString() ;
	}
	
	/**
	 * 获取URL中请求的文件真是路径（/url/xxx中是包含文件路径的）
	 * @param request
	 * @return
	 */
	public static String getFilePath(HttpServletRequest request) {
		
		String path = new StringBuffer(SPRIT).append(HTML_PATH).append(SPRIT).append(getUrlPath(getURI(request))).append(SUFFIX).toString();
		
		return ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath(path);
	}
	
	/**
	 * URI中获取资源地址
	 * @param uri
	 * @return
	 */
	public static String getUrlPath(String uri) {
		
		String path = "";
		Pattern p = Pattern.compile(URL_TYPE);
    	Matcher m = p.matcher(uri);
		if(m.find()) 
		{
			path = m.group(1);
		}
		
		return path;
	}
	
	/**
	 * 获取文件URI路径
	 * @param request
	 * @return
	 */
	public static String getURI(HttpServletRequest request) {
		
		return request.getRequestURI().replace(request.getContextPath(), "");
	}
	
}
