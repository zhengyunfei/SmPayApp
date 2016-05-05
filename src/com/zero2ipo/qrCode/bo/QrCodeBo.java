package com.zero2ipo.qrCode.bo;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2016/4/4.
 * 二维码生成记录
 */
public class QrCodeBo implements java.io.Serializable{
    private String id;//主键
    private String name;//商品名称
    private float money;//商品金额
    private String createTime;//生成时间
    private int status;//支付状态 0 未支付 1 已支付
    private long shopId;//此商品隶属于哪个店铺
    private String qrCodeImage;//二维码图片地址

    public String getQrCodeImage() {
        return qrCodeImage;
    }

    public void setQrCodeImage(String qrCodeImage) {
        this.qrCodeImage = qrCodeImage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getMoney() {
        BigDecimal b   =   new BigDecimal(money);
        money   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).floatValue();
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public String getCreateTime() {
        return createTime.replace(".0","");
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getShopId() {
        return shopId;
    }

    public void setShopId(long shopId) {
        this.shopId = shopId;
    }
}
