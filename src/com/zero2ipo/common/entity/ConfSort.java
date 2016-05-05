/**
 * Copyright (c) 2010 CEPRI,Inc.All rights reserved.
 * Created by 2012-6-1 
 */
package com.zero2ipo.common.entity;

import java.io.Serializable;

/**
 * @title :网站项目配置分类信息
 * @description :网站项目配置分类信息
 * @author: zhengYunFei
 * @data: 2013-7-11
 */
public class ConfSort implements Serializable{
	private static final long serialVersionUID = 1L;
	private String sortCode;//配置项分类编码，实体记录的唯一标识
	private String proType;//项目分类：01 外网，02 内网
	private String proCode;//项目编码：01 csweb，02 cscms，03 csbiz，04 cspms，05 outerscan，06 innersacn
	private String confDes;//配置描述信息
	private String remark;//备注

	public String getSortCode() {
		return sortCode;
	}
	public void setSortCode(String sortCode) {
		this.sortCode = sortCode;
	}
	public String getProType() {
		return proType;
	}
	public void setProType(String proType) {
		this.proType = proType;
	}
	public String getProCode() {
		return proCode;
	}
	public void setProCode(String proCode) {
		this.proCode = proCode;
	}
	public String getConfDes() {
		return confDes;
	}
	public void setConfDes(String confDes) {
		this.confDes = confDes;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	

}
