package com.zero2ipo.shop.bo;
/**
 * @description 多微信公众号扫码支付实体类
 * @description 每个微信公众号只需要输入商品名称,价格就可以创建永久支付二维码
 * @date 2016-04-12
 */
import java.io.Serializable;

public class ShopBo implements Serializable {
	private long id;//主键
	private String name;//店铺名
	private String remark;//店铺简介
	private String createTime;//店铺创建时间
	private String endTime;//店铺失效日期
	private String status;//店铺营业状态0休息中 1营业中
	private String appId;//微信appId
	private String appSecret;//微信秘钥
	private String  partenKey;//商户号
	private String partenValue;//商户秘钥
	private String preBody;//微信支付显示信息（例如xx店铺支付）
	private String mobile;//手机号码
	private String password;//密码
	private int total;//可以创建微信支付公众号的个数

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getPartenKey() {
		return partenKey;
	}

	public void setPartenKey(String partenKey) {
		this.partenKey = partenKey;
	}

	public String getPartenValue() {
		return partenValue;
	}

	public void setPartenValue(String partenValue) {
		this.partenValue = partenValue;
	}

	public String getPreBody() {
		return preBody;
	}

	public void setPreBody(String preBody) {
		this.preBody = preBody;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
}
