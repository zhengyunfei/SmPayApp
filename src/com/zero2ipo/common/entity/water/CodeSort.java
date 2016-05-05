/**
 * @(#)CodeManageCtrl.java	07/03/2013
 *
 * Copyright (c) 2013 S9,Inc.All rights reserved.
 * Created by 2013-07-03
 */
package com.zero2ipo.common.entity.water;

import java.io.Serializable;

/**
 * @title：代码分类信息实体类
 * @description: 代码分类信息实体类
 * @author： zhengYunFei
 * @date： 2013-07-03 10:10
 */
public class CodeSort implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codeSortId; //代码分类唯一标识
	private String codeType; //代码分类编码
	private String codeSortName; //代码分类的名称

	private String codeSortType;//代码分类类型

	private String maintOrgNo;	//代码分类维护机构编码

	private String maintOrgName;	//代码分类维护机构名称  临时字段

	private String codeVn;	//代码版本

	private String validFlag;	//代码是否有效

	private String effectDate;	//代码生效日期

	public String getCodeSortId() {
		return codeSortId;
	}
	public void setCodeSortId(String codeSortId) {
		this.codeSortId = codeSortId;
	}
	public String getCodeSortName() {
		return codeSortName;
	}
	public void setCodeSortName(String codeSortName) {
		this.codeSortName = codeSortName;
	}
	public String getCodeSortType() {
		return codeSortType;
	}
	public void setCodeSortType(String codeSortType) {
		this.codeSortType = codeSortType;
	}
	public String getMaintOrgNo() {
		return maintOrgNo;
	}
	public void setMaintOrgNo(String maintOrgNo) {
		this.maintOrgNo = maintOrgNo;
	}
	public String getMaintOrgName() {
		return maintOrgName;
	}
	public void setMaintOrgName(String maintOrgName) {
		this.maintOrgName = maintOrgName;
	}
	public String getCodeVn() {
		return codeVn;
	}
	public void setCodeVn(String codeVn) {
		this.codeVn = codeVn;
	}
	public String getValidFlag() {
		return validFlag;
	}
	public void setValidFlag(String validFlag) {
		this.validFlag = validFlag;
	}
	public String getEffectDate() {
		return effectDate;
	}
	public void setEffectDate(String effectDate) {
		this.effectDate = effectDate;
	}
	public String getCodeType() {
		return codeType;
	}
	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

}
