package com.zero2ipo.qrcodePay.bo;
/**
 * @description 收款记录
 * @date 2016-04-12
 */

import java.io.Serializable;
import java.math.BigDecimal;

public class QrcodePayBo implements Serializable {
	private long id;//主键
	private String name;//商品名称
	private float money;//付款金额
	private String outTradeNo;//商户订单号
	private String openId;//付款人微信openId
	private String createTime;//扫码时间
	private String payTime;//付款时间
	private long shopId;//当铺id
	private String bankType;//付款银行
	private String totalFee;//实际付款金额
	private String transactionId;//微信支付订单号

	public String getBankType() {
		return bankType;
	}

	public void setBankType(String bankType) {
		this.bankType = bankType;
	}

	public String getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getPayTime() {
		return payTime;
	}

	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}

	public long getShopId() {
		return shopId;
	}

	public void setShopId(long shopId) {
		this.shopId = shopId;
	}

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

	public float getMoney() {
		BigDecimal   b   =   new BigDecimal(money);
		money   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).floatValue();
		return money;
	}

	public void setMoney(float money) {
		this.money = money;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getCreateTime() {
		return createTime.replace(".0","");
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}
