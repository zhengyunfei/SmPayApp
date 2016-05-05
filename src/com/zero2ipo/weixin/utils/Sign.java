package com.zero2ipo.weixin.utils;

import com.zero2ipo.shop.bo.ShopBo;
import com.zero2ipo.smPay.utils.MD5Util;
import org.apache.commons.codec.binary.Hex;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Sign {
    public static void main(String[] args) {
        String jsapi_ticket = Ticket.getTicket();

        // 注意 URL 一定要动态获取，不能 hardcode
        String url = "http://www.pestreet.cn/mobile/share.html";
        Map<String, String> ret = sign(jsapi_ticket, url);
        for (Map.Entry entry : ret.entrySet()) {
            System.out.println(entry.getKey() + ", " + entry.getValue());
        }
    }

    /**
     * 获取分享配置参数信息
     * @return
     */
    public static Map<String, String> getConfigMessage(String url){
        String jsapi_ticket = Ticket.getTicket();
        // 注意 URL 一定要动态获取，不能 hardcode
        Map<String, String> ret = sign(jsapi_ticket, url);
        return ret;
    }
    public static Map<String, String> getConfigMessageByUrl(String url){
        String jsapi_ticket = Ticket.getTicket();
        Map<String, String> ret = new HashMap<String, String>();
        String nonce_str = create_nonce_str();
        String timestamp = create_timestamp();
        String string1;
        String signature = "";

        //注意这里参数名必须全部小写，且必须有序
        string1 = "jsapi_ticket=" + jsapi_ticket +
                "&noncestr=" + nonce_str +
                "&timestamp=" + timestamp +
                "&url=" + url;
        System.out.println(string1);

        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }

        ret.put("url", url);
        ret.put("jsapi_ticket", jsapi_ticket);
        ret.put("nonceStr", nonce_str);
        ret.put("timestamp", timestamp);
        ret.put("signature", signature);
        return ret;
    }
    public static Map<String, String> sign(String jsapi_ticket, String url) {
        Map<String, String> ret = new HashMap<String, String>();
        String nonce_str = create_nonce_str();
        String timestamp = create_timestamp();
        String string1;
        String signature = "";

        //注意这里参数名必须全部小写，且必须有序
        string1 = "jsapi_ticket=" + jsapi_ticket +
                  "&noncestr=" + nonce_str +
                  "&timestamp=" + timestamp +
                  "&url=" + url;
        System.out.println(string1);

        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }

        ret.put("url", url);
        ret.put("jsapi_ticket", jsapi_ticket);
        ret.put("nonceStr", nonce_str);
        ret.put("timestamp", timestamp);
        ret.put("signature", signature);

        return ret;
    }
 public static Map<String, String> sMsign(String appid,String mchId) {
        Map<String, String> ret = new HashMap<String, String>();
        String nonce_str = create_nonce_str();
        String timestamp = create_timestamp();
        String string1;
        String signature = "";
        //注意这里参数名必须全部小写，且必须有序
        string1 = "appid=" + appid +
                  "&mch_id=" + mchId +
                  "&nonce_str=" + nonce_str +
                  "&product_id=" + "dev_342425r2350980000"+
                 "&time_stamp=" + timestamp+
                    "&key=1Q2W3E4R5t6y7u8i9o0pachengdikeik";

        System.out.println(string1);
        try
        {
            MessageDigest crypt = MessageDigest.getInstance("MD5");
           // crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature=  Hex.encodeHexString(crypt.digest()).toUpperCase();
          //  signature = byteToHex(crypt.digest());
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }

        ret.put("nonceStr", nonce_str);
        ret.put("timestamp", timestamp);
        ret.put("signature", signature);
        ret.put("productid", "dev_342425r2350980000");
        return ret;
    }

    /**
     * 根据当前登陆的店铺生成签名
     * @param bo
     * @return
     */
    public static Map<String, String> sMsign(ShopBo bo,String productId) {
        Map<String, String> ret = new HashMap<String, String>();
        String nonce_str = create_nonce_str();
        String timestamp = create_timestamp();
        String string1;
        String signature = "";
        //注意这里参数名必须全部小写，且必须有序
        string1 = "appid=" + bo.getAppId() +
                "&mch_id=" + bo.getPartenKey() +
                "&nonce_str=" + nonce_str +
                "&product_id=" +productId+
                "&time_stamp=" + timestamp+
                "&key="+bo.getPartenValue().trim();

        System.out.println(string1);
        try
        {
            MessageDigest crypt = MessageDigest.getInstance("MD5");
            // crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature=  Hex.encodeHexString(crypt.digest()).toUpperCase();
            //  signature = byteToHex(crypt.digest());
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }

        ret.put("nonceStr", nonce_str);
        ret.put("timestamp", timestamp);
        ret.put("signature", signature);
        ret.put("productid", productId+"");
        return ret;
    }
    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
    public static String createSign(SortedMap packageParams) {
        StringBuffer sb = new StringBuffer();
        Set<Map.Entry> es = packageParams.entrySet();
        Iterator<Map.Entry> it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = entry.getValue() != null ? entry.getValue().toString() : null;
            if (null != v&& !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + "1Q2W3E4R5t6y7u8i9o0pachengdikeik");
        System.out.println("sbstring====================================================="+sb.toString());
        return MD5Util.MD5Encode(sb.toString(), "MD5").toUpperCase();
    }
    public static String createSign(Map packageParams) {

        StringBuffer sb = new StringBuffer();

        Set<Map.Entry> es = packageParams.entrySet();

        Iterator<Map.Entry> it = es.iterator();

        while (it.hasNext()) {

            Map.Entry entry = (Map.Entry) it.next();

            String k = (String) entry.getKey();

            String v = entry.getValue() != null ? entry.getValue().toString() : null;

            if (null != v

                    && !"".equals(v)

                    && !"sign".equals(k)

                    && !"key".equals(k)) {

                sb.append(k + "=" + v + "&");

            }

        }

        sb.append("key=" + "1Q2W3E4R5t6y7u8i9o0pachengdikeik");

        return MD5Util.MD5Encode(sb.toString(), "MD5").toUpperCase();

    }
    public static String create_nonce_str() {
        return UUID.randomUUID().toString().replace("-","");
    }

    public static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
}
