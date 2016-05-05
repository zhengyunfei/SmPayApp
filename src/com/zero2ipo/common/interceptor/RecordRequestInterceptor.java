package com.zero2ipo.common.interceptor;

import com.zero2ipo.core.MobileContants;
import com.zero2ipo.mobile.web.SessionHelper;
import com.zero2ipo.mobile.web.URLHelper;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 记录请求拦截器
 *
 */
public class RecordRequestInterceptor extends HandlerInterceptorAdapter{

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
		
		String url = URLHelper.getURI(request);
		SessionHelper.setAttribute(request, MobileContants.PAGE_SESSION_KEY, url);
		return true;
		
	}
	
}
