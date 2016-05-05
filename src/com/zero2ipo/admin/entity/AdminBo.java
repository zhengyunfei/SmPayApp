package com.zero2ipo.admin.entity;

/**
 * 后台用户实体类
 * @author zhengyunfei
 *
 */
public class AdminBo {

	private String userId;			//用户标识;

	private String userNo;			//用户编码;

	private String userName;		//用户名称;

	private String organId;			//组织单位标识;

	private String roleId;			//角色标识

	private String organNo;			//机构、单位等编码

	private String organName ;		//机构单位详细的名称。

	private String userRealName;	//用户真是姓名;

	private String userPwd;			//用户密码;

	private String curStatusCode;	//系统用户的状态，包括：启用、禁用、删除。01 正常、02 禁用 、03 删除、04 锁定。;

	private String gender;			//性别;

	private String position;		//职务;

	private String tel;				//联系电话;

	private String mobile;			//移动电话;

	private String email;			//e-mail;

	private String ip;				//用户绑定的IP地址。可以是多个具体的IP地址，也可以是通用IP地址（如10.152.109.*）;

	private String addDate;			//新增日期;

	private String addUser;			//添加用户操作人员;

	private String expHintDate;		//失效提醒日期;

	private String remark;			//备注

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserRealName() {
		return userRealName;
	}

	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getCurStatusCode() {
		return curStatusCode;
	}

	public void setCurStatusCode(String curStatusCode) {
		this.curStatusCode = curStatusCode;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getAddDate() {
		return addDate;
	}

	public void setAddDate(String addDate) {
		this.addDate = addDate;
	}

	public String getAddUser() {
		return addUser;
	}

	public void setAddUser(String addUser) {
		this.addUser = addUser;
	}

	public String getExpHintDate() {
		return expHintDate;
	}

	public void setExpHintDate(String expHintDate) {
		this.expHintDate = expHintDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
