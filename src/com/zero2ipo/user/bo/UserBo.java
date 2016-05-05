package com.zero2ipo.user.bo;

public class UserBo implements java.io.Serializable{
	private long id;//主键
	private String mobile;//手机号码
	private String empNo;//员工号
	private int jiFen;//积分
	private String createTime;//注册时间
	private String lastLoginTime;//最近一次登陆时间
	private int  rank;//会员等级
    private int loginNum;//登陆次数

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public int getLoginNum() {
		return loginNum;
	}

	public void setLoginNum(int loginNum) {
		this.loginNum = loginNum;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public int getJiFen() {
		return jiFen;
	}

	public void setJiFen(int jiFen) {
		this.jiFen = jiFen;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
}
