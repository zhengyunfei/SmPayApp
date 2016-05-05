package com.zero2ipo.common.interceptor;

import com.zero2ipo.core.MobileContants;
import com.zero2ipo.mobile.web.SessionHelper;
import com.zero2ipo.module.entity.user.UserEntity;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 注册步骤拦截器
 */

public class RegisterStepInterceptor extends HandlerInterceptorAdapter {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
		
		Object o = SessionHelper.getAttribute(request, MobileContants.USER_SESSION_KEY);
		if(o == null) 
		{
			response.sendRedirect(new StringBuffer(request.getContextPath()).append("/url/user/register.html").toString());
			return false;
		}
		
		return true;
	}
	
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {  
        
		Object o = SessionHelper.getAttribute(request, MobileContants.USER_SESSION_KEY);
		if(o != null && o instanceof UserEntity) 
		{
			UserEntity user = (UserEntity)o;
			request.setAttribute("user", user);
		}
    }  
	
}
