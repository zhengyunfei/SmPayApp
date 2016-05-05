package com.zero2ipo.common.interceptor;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.zero2ipo.common.GlobalConstant;
import com.zero2ipo.common.entity.Site;

/**
 *
 */
public class FtsContextInterceptor extends HandlerInterceptorAdapter {

	private static final String REQUEST_HTTP = "http://";

	@Autowired
	private Site site;				//注入站点对象

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {

		boolean flg = true;

		//产点信息记录
		String contextPath = request.getContextPath();
		String siteName = request.getServerName();
		String domain = request.getRemoteHost();
		int port = request.getLocalPort();

		StringBuffer hua = new StringBuffer(REQUEST_HTTP).append(siteName);
		if(port != 80) {
			hua.append(":").append(port);
		}

		if(site != null)
		{
			site.setContextPath(contextPath);
			site.setSiteName(siteName);
			site.setDomain(domain);
			site.setHttpUrIAddr(hua.toString());

		}

		//将站点信息放入request中
		request.setAttribute(GlobalConstant.SITE_KEY, site);
		return flg;
	}

}
