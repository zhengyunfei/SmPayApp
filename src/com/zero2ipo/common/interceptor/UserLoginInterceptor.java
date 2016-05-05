package com.zero2ipo.common.interceptor;

import com.zero2ipo.core.MobileContants;
import com.zero2ipo.mobile.web.SessionHelper;
import com.zero2ipo.mobile.web.URLHelper;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 用户登录拦截器
 *
 */
public class UserLoginInterceptor extends HandlerInterceptorAdapter{
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		boolean flag = false;
		Object o = session.getAttribute(MobileContants.USER_SESSION_KEY);
		if(o == null) 
		{
		   String url = URLHelper.getURI(request);
		   if(url.contains("/user/login.html")){
	        	return true;
	        }
			SessionHelper.setAttribute(request, MobileContants.PAGE_SESSION_KEY, url);
			response.sendRedirect(new StringBuffer(request.getContextPath()).append("/user/login.html").toString());
			//request.getRequestDispatcher("/user/toLogin.do").forward(request, response);
			//return false;
		}else{
			flag=true;
		}
	
		return flag;
	}
	
}
