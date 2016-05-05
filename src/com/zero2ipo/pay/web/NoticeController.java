package com.zero2ipo.pay.web;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.jdom2.JDOMException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zero2ipo.pay.model.MdlTemplate;
import com.zero2ipo.pay.service.WXOrderQuery;
import com.zero2ipo.pay.util.XMLUtil;

@Controller
public class NoticeController {
	// 此处为微信支付配置的域名
	String base_url = "http://wxpay.omsapp.cn";
	/**
	 * 通知调用方法
	 * @param request
	 * @param response
	 * @throws java.io.IOException
	 */
	@RequestMapping("/notice")
	public void notice(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		InputStream inStream = request.getInputStream();
		ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outSteam.write(buffer, 0, len);
		}
		outSteam.close();
		inStream.close();
		String result = new String(outSteam.toByteArray(), "utf-8");
		Map<String, String> map = null;
		try {
			map = XMLUtil.doXMLParse(result);
		} catch (JDOMException e) {
			e.printStackTrace();
		}

		// 此处获取accessToken
		String accessToken = "3oWof69djPQPm2SWHrLmpDpmpMWMFYgxE4UKCc0QwEIt7MhdITnCOCdKxo6Df2LxAXkPRIsoDtyAqAGaQEDWlIKkqseoy5dA8VjOlul6UMs";
		// 此处调用订单查询接口验证是否交易成功
		boolean isSucc = reqOrderquery(map, accessToken);
		// 支付成功，商户处理后同步返回给微信参数
		if (!isSucc) {
			// 支付失败
			System.out.println("支付失败");
		} else {
			System.out.println("===============付款成功==============");
			// ------------------------------
			// 处理业务开始
			// ------------------------------
			// 此处处理订单状态，结合自己的订单数据完成订单状态的更新
			// ------------------------------
			// 处理业务完毕
			// ------------------------------
			String noticeStr = setXML("SUCCESS", "");
			out.print(new ByteArrayInputStream(noticeStr.getBytes(Charset.forName("UTF-8"))));
		}
	}

	public static String setXML(String return_code, String return_msg) {
		return "<xml><return_code><![CDATA[" + return_code + "]]></return_code><return_msg><![CDATA[" + return_msg + "]]></return_msg></xml>";
	}

	/**
	 * 请求订单查询接口
	 * @param map
	 * @param accessToken
	 * @return
	 */
	public static boolean reqOrderquery(Map<String, String> map, String accessToken) {
		WXOrderQuery orderQuery = new WXOrderQuery();
		orderQuery.setAppid(map.get("appid"));
		orderQuery.setMch_id(map.get("mch_id"));
		orderQuery.setTransaction_id(map.get("transaction_id"));
		orderQuery.setOut_trade_no(map.get("out_trade_no"));
		orderQuery.setNonce_str(map.get("nonce_str"));
		
		//此处需要密钥PartnerKey，此处直接写死，自己的业务需要从持久化中获取此密钥，否则会报签名错误
		orderQuery.setPartnerKey("c9f09543c97719849d51286905261d76");
		
		Map<String, String> orderMap = orderQuery.reqOrderquery();
		//此处添加支付成功后，支付金额和实际订单金额是否等价，防止钓鱼
		if (orderMap.get("return_code") != null && orderMap.get("return_code").equalsIgnoreCase("SUCCESS")) {
			if (orderMap.get("trade_state") != null && orderMap.get("trade_state").equalsIgnoreCase("SUCCESS")) {
				String total_fee = map.get("total_fee");
				String order_total_fee = map.get("total_fee");
				if (Integer.parseInt(order_total_fee) >= Integer.parseInt(total_fee)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 支付成功后发送支付成功的模版消息
	 * @param accessToken
	 * @param openid
	 * @param orderid
	 * @return
	 */
	public boolean sendTemplateMsg(String accessToken, String openid, String orderid) {
		MdlTemplate tempMsg = new MdlTemplate();
		tempMsg.addItem("remark", "欢迎再次购买！", "#4169E1");
		tempMsg.addItem("expDate", "2015年12月24日", "#4169E1");
		tempMsg.addItem("number", "1份", "#4169E1");
		tempMsg.addItem("name", "赞助费", "#4169E1");
		tempMsg.addItem("productType", "商品名", "#4169E1");
		tempMsg.setTemplate_id("5ODqfQRJl-dpMf8s-S9TNDeQo4DATDJZDhUvr1gOAcU");
		tempMsg.setTopcolor("#7CFC00");
		tempMsg.setTouser(openid);
		//此处构造支付订单详情页面地址，此处demo不做详细处理，实际项目中需要处理
		tempMsg.setUrl(base_url + "/order?orderid=" + orderid);
		JSONObject obj = JSONObject.fromObject(tempMsg);
		System.out.println("obj=" + obj);
		// 此处调用发送模版消息的API
		/*
		 * JSONObject jo = WeixinUtil.sendTemplate(accessToken, tempMsg);
		 * System.out.println("jo===" + jo); 
		 * if (jo != null && jo.getInt("errcode") == 0) {
		 *  	return true; 
		 *  }
		 * System.out.println("========发送模版消息失败=========");
		 */
		return false;
	}
}
