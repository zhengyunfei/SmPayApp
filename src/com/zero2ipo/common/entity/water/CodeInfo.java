/**
 * @(#)CodeManageCtrl.java	07/03/2013
 *
 * Copyright (c) 2013 S9,Inc.All rights reserved.
 * Created by 2013-07-03
 */
package com.zero2ipo.common.entity.water;

import java.io.Serializable;
import java.util.List;

/**
 * @title：标准代码项信息实体类
 * @description: 标准代码项信息实体类
 * @author： zhengYunFei
 * @date： 2013-07-03 10:10
 */
public class CodeInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codeId; //代码唯一标识

	private String codeName; //代码值的名称

	private String codeValue; //代码值

	private String codeSortId; //代码分类唯一标识

	private String codeType; //代码类型  代码分类编码

	private String pcode; //上级代码值

	private String dispSn; //代码显示顺序号

	private String validFlag; //代码是否有效
	private String isMenu;//是否是菜单

	private String content1; //代码内容一

	private String content2; //代码内容二

	private String content3; //代码内容三
	private String id;
	private String text;

	private List<CodeInfo> children;

	public String getCodeId() {
		return codeId;
	}
	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}
	public String getCodeName() {
		return codeName;
	}
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	public String getCodeValue() {
		return codeValue;
	}
	public void setCodeValue(String codeValue) {
		this.codeValue = codeValue;
	}
	public String getCodeSortId() {
		return codeSortId;
	}
	public void setCodeSortId(String codeSortId) {
		this.codeSortId = codeSortId;
	}
	public String getDispSn() {
		return dispSn;
	}
	public void setDispSn(String dispSn) {
		this.dispSn = dispSn;
	}

	public String getContent1() {
		return content1;
	}
	public void setContent1(String content1) {
		this.content1 = content1;
	}
	public String getContent2() {
		return content2;
	}
	public void setContent2(String content2) {
		this.content2 = content2;
	}
	public String getContent3() {
		return content3;
	}
	public void setContent3(String content3) {
		this.content3 = content3;
	}
	public String getValidFlag() {
		return validFlag;
	}
	public void setValidFlag(String validFlag) {
		this.validFlag = validFlag;
	}
	public String getCodeType() {
		return codeType;
	}
	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}
	public String getPcode() {
		return pcode;
	}
	public void setPcode(String pcode) {
		this.pcode = pcode;
	}
	public List<CodeInfo> getChildren() {
		return children;
	}
	public void setChildren(List<CodeInfo> children) {
		this.children = children;
	}
	public String getId() {
		return this.codeValue;
	}
	public void setId(String id) {
		this.id = this.codeId;
	}
	public String getText() {
		return this.codeName;
	}
	public void setText(String text) {
		this.text = this.codeName;
	}
	public String getIsMenu() {
		return isMenu;
	}
	public void setIsMenu(String isMenu) {
		this.isMenu = isMenu;
	}

}
