package com.zero2ipo.common.entity;

import org.springframework.stereotype.Component;

/**
 * 该类用于获取站点信息，暂时未对应到数据库
 * @author zhengyunfei
 *
 */

@Component("site")
public class Site {

	private int id; // 站点ID
	private String siteName; // 站点名称
	private String sitePath; // 站点路径
	private String domain; // 域名
	private String contextPath;
	private String httpUrIAddr;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getSitePath() {
		return sitePath;
	}

	public void setSitePath(String sitePath) {
		this.sitePath = sitePath;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

	public String getHttpUrIAddr() {
		return httpUrIAddr;
	}

	public void setHttpUrIAddr(String httpUrIAddr) {
		this.httpUrIAddr = httpUrIAddr;
	}

}
