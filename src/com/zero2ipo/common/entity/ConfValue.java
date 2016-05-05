/**
 * Copyright (c) 2010 CEPRI,Inc.All rights reserved.
 * Created by 2012-6-1 
 */
package com.zero2ipo.common.entity;

import java.io.Serializable;

/**
 * @title :网站项目配置值信息
 * @description :网站项目配置值信息
 * @author: zhengYunFei
 * @data: 2013-7-11
 */
public class ConfValue implements Serializable{
	private static final long serialVersionUID = 1L;
	private String valueId;//本实体记录的唯一标识，产生规则为流水号
	private String sortCode;//配置项分类编码。
	private String confKey;//配置项关键字
	private String confValue;//配置值
	private String confDes;//配置值描述信息
	private String remark;//备注
	public String getValueId() {
		return valueId;
	}
	public void setValueId(String valueId) {
		this.valueId = valueId;
	}
	public String getSortCode() {
		return sortCode;
	}
	public void setSortCode(String sortCode) {
		this.sortCode = sortCode;
	}
	public String getConfKey() {
		return confKey;
	}
	public void setConfKey(String confKey) {
		this.confKey = confKey;
	}
	public String getConfValue() {
		return confValue;
	}
	public void setConfValue(String confValue) {
		this.confValue = confValue;
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
