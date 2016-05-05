package com.zero2ipo.common.interceptor;

import com.zero2ipo.common.log.IpLog;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 
 * IP日志记录拦截器
 * @date 2015-04-22
 */
public class IpLogInterceptor extends HandlerInterceptorAdapter{
	
	Logger logger = Logger.getLogger("ip"); 
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
		
		String uri = request.getRequestURI();
		
		IpLog.ipLog(logger, getRemortIP(request) + " " + uri);
	
		return true;
	}

	/*
	 * 获取客户端IP
	 */
	public String getRemortIP(HttpServletRequest request) {
		if (request.getHeader("x-forwarded-for") == null) 
		{
			return request.getRemoteAddr();
		}
		return request.getHeader("x-forwarded-for");
	}
	
}
