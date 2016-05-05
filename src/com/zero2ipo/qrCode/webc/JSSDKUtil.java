package com.zero2ipo.qrCode.webc;

import com.google.gson.JsonObject;
import com.zero2ipo.weixin.token.TokenThread;
import com.zero2ipo.weixin.utils.HttpRequest;
import org.json.JSONObject;

import java.io.*;
import java.util.Random;

public class JSSDKUtil {


	private final static String APPID="";
	private final static String SECRET="";


	public static String getSignPackage(String _url) {
		String jsapiTicket=JSSDKUtil.getJsApiTicket();

		String url=_url;
		String timestamp=String.valueOf(System.currentTimeMillis()).substring(0, 10);
	    String nonceStr=JSSDKUtil.createNonceStr();

		String result="jsapi_ticket="+jsapiTicket+"&noncestr="+nonceStr+"&timestamp="+timestamp+"&url="+url;

		String signature=null;
		try {
			 signature= SHA1Util.getSha1(result);
		} catch (Exception e) {
			e.printStackTrace();
		}


		JsonObject object= new JsonObject();
		object.addProperty("appId", JSSDKUtil.APPID);
		object.addProperty("nonceStr", nonceStr);
		object.addProperty("timestamp", timestamp);
		object.addProperty("url", url);
		object.addProperty("signature", signature);
		object.addProperty("rawString", result);

		return object.toString();
	}

	public static String createNonceStr() {
		int length = 16;
		String charString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			int a = random.nextInt(charString.length() - 1);
			sb = sb.append(charString.substring(a, a + 1));
		}

		return sb.toString();
	}

	// jsapi_ticket是公众号用于调用微信JS接口的临时票据
	// 正常情况下，jsapi_ticket的有效期为7200秒，通过access_token来获取。
	public static String getJsApiTicket() {
		//利用绝对路径来定位文件地址,文件存放于SRC目录之下
		String path=Thread.currentThread().getContextClassLoader().getResource("").toString().substring(5);
		String content = JSSDKUtil.readContent(path+"jsapi_ticket.json");
		JSONObject dataJson = null;
		String expire_time=null;
		String jsapi_ticket=null;
		try {
			dataJson = new JSONObject(content);
			expire_time = dataJson.getString("expire_time");
			if (Integer.parseInt(expire_time)<Integer.parseInt(String.valueOf(System.currentTimeMillis()).substring(0,10))) {
				//String accessToken=JSSDKUtil.getAccessToken();
				String accessToken= TokenThread.accessToken.getToken();
				String url ="https://api.weixin.qq.com/cgi-bin/ticket/getticket?type=jsapi&access_token="+accessToken;
				JSONObject resJson=new JSONObject(HttpRequest.sendGet(url, null));
				String ticket =resJson.getString("ticket");
				if(ticket!=null){
					expire_time=String.valueOf(Integer.parseInt(String.valueOf(System.currentTimeMillis()).substring(0,10))+7000);
					jsapi_ticket=ticket;
					JsonObject object= new JsonObject();
					object.addProperty("jsapi_ticket", jsapi_ticket);
					object.addProperty("expire_time", expire_time);

					System.out.println(path+"这时jsapi_ticket的路径");
					JSSDKUtil.writeContent(path+"jsapi_ticket.json", object.toString());
				}
			}else{
				jsapi_ticket=dataJson.getString("jsapi_ticket");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsapi_ticket;
	}

	public static String getAccessToken() {
		String path=Thread.currentThread().getContextClassLoader().getResource("").toString().substring(5);
		String content = JSSDKUtil.readContent(path + "access_token.json");
		JSONObject dataJson = null;
		String expire_time=null;
		String access_token =null;
		try {
			dataJson = new JSONObject(content);
			expire_time = dataJson.getString("expire_time");
			if (Integer.parseInt(expire_time)<Integer.parseInt(String.valueOf(System.currentTimeMillis()).substring(0,10))) {
				String url ="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+JSSDKUtil.APPID+"&secret="+JSSDKUtil.SECRET;
				JSONObject resJson=new JSONObject(HttpRequest.sendGet(url, null));
				access_token =resJson.getString("access_token");
				if(access_token!=null){
					expire_time=String.valueOf(Integer.parseInt(String.valueOf(System.currentTimeMillis()).substring(0,10))+7000);
					//构建JSON格式数据，写入文本文件中
					JsonObject object= new JsonObject();
					object.addProperty("access_token", access_token);
					object.addProperty("expire_time", expire_time);

					System.out.println(path+"这时access_token的路径");
					JSSDKUtil.writeContent(path+"access_token.json", object.toString());
				}
			}else {
				access_token=dataJson.getString("access_token");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return access_token;
	}


	// 读取文本文件
	public static String readContent(String path) {
		StringBuffer content = new StringBuffer();
		try {
			File file = new File(path);
			if (file.isFile() && file.exists()) {
				InputStreamReader read = new InputStreamReader(new FileInputStream(file));
				BufferedReader br = new BufferedReader(read);
				String temp = null;
				while ((temp = br.readLine()) != null) {
					content = content.append(temp);
				}
				read.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content.toString();
	}

	// 写入文本文件
	public static void writeContent(String path, String content) {
		try {
			File file = new File(path);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.flush();
			bw.close();
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
