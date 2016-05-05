package com.zero2ipo.common.interceptor;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.zero2ipo.core.MobileContants;
import com.zero2ipo.mobile.web.SessionHelper;
import com.zero2ipo.mobile.web.URLHelper;
import com.zero2ipo.module.entity.user.UserEntity;

/**
 * 用户登录拦截器
 *
 */
public class FundDetailInterceptor extends HandlerInterceptorAdapter{
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
		
		boolean flg = true;
		String forward_url="/url/user/login.html";//登陆url
		HttpSession session = request.getSession();
		Object o =  session.getAttribute(MobileContants.USER_SESSION_KEY);
		if(o != null && o instanceof UserEntity) 
		{
			UserEntity user = (UserEntity)o;
			if(!"2".equals(user.getUserStatus()))//不是审核通过
			{
				flg = false;
				//建议将此处调整为连接方式，提示为可跳转到完善信息部分的连接。
				if("0".equals(user.getUserStatus())){//信息未完善
					forward_url="/url/user/wszl.html";
					request.setAttribute("error", "错误提示:您的资料尚未完善,暂时不能查看");
				}else if("1".equals(user.getUserStatus())){
					forward_url="/url/mobile/main/ckxq_shz.html";
				}
				
			}
		}
		else 
		{
			flg = false;
		}
		
		if(!flg) 
		{
			String url = URLHelper.getURI(request);
			SessionHelper.setAttribute(request, MobileContants.PAGE_SESSION_KEY, url);
			//FmUtils.FmData(request, model);
			request.getRequestDispatcher(new StringBuffer(forward_url).toString()).forward(request, response);
			//response.sendRedirect(new StringBuffer(request.getContextPath()).append("/url/user/login.html").toString());
		}
		return flg;
	}
	
}
