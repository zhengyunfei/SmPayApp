package com.zero2ipo.qrCode.webc;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.zero2ipo.common.http.FmUtils;
import com.zero2ipo.common.web.BaseCtrl;
import com.zero2ipo.core.MobileContants;
import com.zero2ipo.framework.util.StringUtil;
import com.zero2ipo.mobile.utils.QrCodeContants;
import com.zero2ipo.mobile.utils.WaterPageContants;
import com.zero2ipo.mobile.web.SessionHelper;
import com.zero2ipo.pay.service.WXPrepay;
import com.zero2ipo.pay.util.MapToXMLString;
import com.zero2ipo.pay.util.XMLUtil;
import com.zero2ipo.qrCode.bizc.IQrCodeService;
import com.zero2ipo.qrCode.bo.QrCodeBo;
import com.zero2ipo.qrCode.bo.WxPayResult;
import com.zero2ipo.qrcodePay.bizc.IQrcodePayService;
import com.zero2ipo.qrcodePay.bo.QrcodePayBo;
import com.zero2ipo.shop.bizc.IShopService;
import com.zero2ipo.shop.bo.ShopBo;
import com.zero2ipo.shop.bo.ShopContants;
import com.zero2ipo.weixin.utils.HttpRequest;
import com.zero2ipo.weixin.utils.Sign;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.StringUtils;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.xml.sax.InputSource;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

/**
 * 二维码功能开发
 * @author zhengyunfei
 *
 */
@Controller
public class QrCodeAction extends BaseCtrl {
	@Resource(name = "QrCodeService")
	private IQrCodeService qrCodeService;
	@Resource(name = "shopService")
	private IShopService shopService;

	@Resource(name = "qrcodePayService")
	private IQrcodePayService qrcodePayService;
	//通知回调地址
	//String notifyUrl = "http://bj-sienwanmei.com/order/smPayUpdate.html";
	String notifyUrl = "/order/smPayUpdate.html";
	/**
	 * 扫码支付首页
	 * @return
	 */
	@RequestMapping(value = "/qrCode/index.html",method = RequestMethod.GET)
	public ModelAndView forInit(HttpServletRequest request,
								HttpServletResponse response, ModelMap model){
		FmUtils.FmData(request, model);
		//首先判断是否已登陆
		ModelAndView mv=new ModelAndView();
		ShopBo shopBo= (ShopBo) SessionHelper.getAttribute(request,ShopContants.CURRENT_LOGIN_KEY);
		if(!StringUtil.isNullOrEmpty(shopBo)){
			mv.setViewName(QrCodeContants.INDEX_PAGE);
		}else{
			mv.setViewName(ShopContants.LOGIN_PAGE);
		}

		return mv;
	}

	/**
	 2      * 生成二维码图片并返回Base64编码格式的图片
	 3      * @param content
	 4      * @param response
	 5      */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static String encodeQrcodeToBase64(String content,HttpServletResponse response){
		String base64Image="";
		if(StringUtils.isBlank(content)){
			return "";
		}
		MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
		response.setContentType("image/jpeg");
		Map hints = new HashMap();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8"); //设置字符集编码类型
		BitMatrix bitMatrix = null;
		try {
			bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 300, 300,hints);
			BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);
			//输出二维码图片流
			try {
				base64Image=getImageBinary(image);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (WriterException e1) {
			e1.printStackTrace();
		}
		return base64Image;
	}

	/**
	 * 生成带logo的二维码
	 * @param content
	 * @return
	 */
	public static String createLogoQRCode(String content,HttpServletResponse response) {
		String base64Image="";
		try {
			if(StringUtils.isBlank(content)){
				return "";
			}
			MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
			response.setContentType("image/jpeg");
			Map hints = new HashMap();
			hints.put(EncodeHintType.CHARACTER_SET, "UTF-8"); //设置字符集编码类型
			BitMatrix bitMatrix = null;
				bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 300, 300,hints);
				BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);
				Graphics2D gs = image.createGraphics();
				gs.setBackground(Color.WHITE);
				gs.clearRect(0, 0, 140, 140);
				// 设定图像颜色 > BLACK
				gs.setColor(Color.BLACK);
				// 设置偏移量 不设置可能导致解析出错
				int pixoff = 2;
				Image img = ImageIO.read(new File("D:/s360/WaterApp/web/res/images/bdh01.jpg"));//实例化一个Image对象。
				gs.drawImage(img, 50,50, null);
				gs.dispose();
				image.flush();
				//输出二维码图片流
				base64Image=getImageBinary(image);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			return base64Image;
	}
	/**
	 * 将图片转换成base64编码格式
	 * @param bi
	 * @return
	 */
	public static String getImageBinary(BufferedImage bi){
		BASE64Encoder encoder = new sun.misc.BASE64Encoder();
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(bi, "jpg", baos);
			byte[] bytes = baos.toByteArray();
			return "data:image/png;base64,"+encoder.encodeBuffer(bytes).trim();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/************************************扫码付款回调方法******start******************************************************/
	@RequestMapping(value = "/order/smPayUpdate.html",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> updateQrCode(HttpServletRequest request,HttpServletResponse response, ModelMap model){
		System.out.println("扫码付款回调方法start===============================");
		Map<String,Object> queryMap=new HashMap<String, Object>();
		String inputLine;
		String notityXml = "";
		String resXml = "";
		try {
			while ((inputLine = request.getReader().readLine()) != null) {
				notityXml += inputLine;
			}
			request.getReader().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println("扫码支付回调返回结果：" + notityXml);
		//返回参数如下
		/*<xml>
		<appid><![CDATA[wx2421b1c4370ec43b]]></appid>
		<attach><![CDATA[支付测试]]></attach>
		<bank_type><![CDATA[CFT]]></bank_type>
		<fee_type><![CDATA[CNY]]></fee_type>
		<is_subscribe><![CDATA[Y]]></is_subscribe>
		<mch_id><![CDATA[10000100]]></mch_id>
		<nonce_str><![CDATA[5d2b6c2a8db53831f7eda20af46e531c]]></nonce_str>
		<openid><![CDATA[oUpF8uMEb4qRXf22hE3X68TekukE]]></openid>
		<out_trade_no><![CDATA[1409811653]]></out_trade_no>
		<result_code><![CDATA[SUCCESS]]></result_code>
		<return_code><![CDATA[SUCCESS]]></return_code>
		<sign><![CDATA[B552ED6B279343CB493C5DD0D78AB241]]></sign>
		<sub_mch_id><![CDATA[10000100]]></sub_mch_id>
		<time_end><![CDATA[20140903131540]]></time_end>
		<total_fee>1</total_fee>
		<trade_type><![CDATA[JSAPI]]></trade_type>
		<transaction_id><![CDATA[1004400740201409030005092168]]></transaction_id>
		</xml>*/
		Map m = parseXmlToList2(notityXml);
		String openid=m.get("openid")+"";
		String return_code=m.get("return_code")+"";//付款成功与否的标志
		String bank_type=m.get("bank_type")+"";//付款银行
		String total_fee=m.get("total_fee")+"";//付款金额
		String transaction_id=m.get("transaction_id")+"";//微信支付订单号
		String out_trade_no=m.get("out_trade_no")+"";//商户订单号
		String attach=m.get("attach")+"";//商家数据包
		String time_end=m.get("time_end")+"";//支付时间
		try {
			//qrCodeService.updateQrcodeImage(bo);
			System.out.println("openid==============================="+openid);
			System.out.println("付款成功与否的标志==============================="+"SUCCESS".equals(return_code));
			if("SUCCESS".equals(return_code)){
				//更新扫码记录的付款状态
				QrcodePayBo smJiLu=new QrcodePayBo();
				smJiLu.setOutTradeNo(out_trade_no);
				//首先将long类型的时间转换为正常的格式
				long time=Long.valueOf(time_end); //long类型的字符串转换
				SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				smJiLu.setPayTime(time+"");
				smJiLu.setBankType(bank_type);
				smJiLu.setTotalFee(total_fee);
				smJiLu.setTransactionId(transaction_id);
				System.out.println("付款银行:"+bank_type+"\t付款金额："+total_fee+"\t付款用户openid=="+openid+"\t微信支付订单号="+transaction_id+"\t支付时间："+time);
				qrcodePayService.update(smJiLu);
			}

		}catch (Exception e){
			e.printStackTrace();
		}
		return queryMap;
	}
	/************************************扫码付款回调方法******end********************************************************/




























	/**
	 * 二维码生成记录
	 * @return
	 */
	@RequestMapping(value = "/qrCode/list.html",method = RequestMethod.GET)
	public ModelAndView qrcodeList(HttpServletRequest request,
								   HttpServletResponse response, ModelMap model,String pageNo){
		FmUtils.FmData(request, model);
		//首先判断是否已登陆
		ModelAndView mv=new ModelAndView();
		ShopBo shopBo= (ShopBo) SessionHelper.getAttribute(request,ShopContants.CURRENT_LOGIN_KEY);
		if(!StringUtil.isNullOrEmpty(shopBo)){
			mv.setViewName(QrCodeContants.QRCODE_LIST_PAGE);
		}else{
			mv.setViewName(ShopContants.LOGIN_PAGE);
		}
		if(StringUtil.isNullOrEmpty(pageNo)){
			pageNo="1";
		}
		model.put("pageNo", Integer.valueOf(pageNo));
		model.put("pageSize", WaterPageContants.PAGE_SIZE);
		model.put("recordCount",WaterPageContants.RECOND_COUNT);
		return mv;
	}
	/**
	 * 付款记录
	 * @return
	 */
	@RequestMapping(value = "/qrCode/cashier.html",method = RequestMethod.GET)
	public ModelAndView cashier(HttpServletRequest request,
								HttpServletResponse response, ModelMap model,String pageNo){
		FmUtils.FmData(request, model);
		//首先判断是否已登陆
		ModelAndView mv=new ModelAndView();
		ShopBo shopBo= (ShopBo) SessionHelper.getAttribute(request,ShopContants.CURRENT_LOGIN_KEY);
		if(!StringUtil.isNullOrEmpty(shopBo)){
			mv.setViewName(QrCodeContants.QRCODE_CASHIER_PAGE);
		}else{
			mv.setViewName(ShopContants.LOGIN_PAGE);
		}
		if(StringUtil.isNullOrEmpty(pageNo)){
			pageNo="1";
		}
		model.put("pageNo", Integer.valueOf(pageNo));
		model.put("pageSize", WaterPageContants.PAGE_SIZE);
		model.put("recordCount",WaterPageContants.RECOND_COUNT);
		return mv;
	}




	/************************************保存二维码生成记录到数据库中******start******************************************************/
	@RequestMapping(value = "/qrCode/add.html",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> addQrCode(HttpServletRequest request,HttpServletResponse response, ModelMap model,QrCodeBo bo){
		Map<String,Object> queryMap=new HashMap<String, Object>();
		try {
			//获取当前登陆的店铺信息
			ShopBo shopBo= (ShopBo) SessionHelper.getAttribute(request,ShopContants.CURRENT_LOGIN_KEY);
			if(!StringUtil.isNullOrEmpty(shopBo)){
				bo.setShopId(shopBo.getId());
				bo.setStatus(WaterPageContants.FLAG_0);//刚生成的时候，都是未支付的
			}
			//同时要生成二维码连接到页面上,商品id暂时用uuid
			String productId=UUID.randomUUID().toString().replace("-","");
			bo.setId(productId);
			System.out.println("生成二维码是当前店铺是========" + shopBo);
			System.out.println("生成二维码是当前店铺是========"+shopBo.getName());
			String code_url=getQrCodeUrl(shopBo,productId);
			String base64Image=encodeQrcodeToBase64(code_url,response);
			//String base64Image=createLogoQRCode(code_url,response);
			//保存base64Image到数据库中
			bo.setQrCodeImage(base64Image);
			//保存二维码生成记录
			String id=qrCodeService.add(bo);
			queryMap.put("id",id);//保存商品id
			//回写到页面
			queryMap.put("image",base64Image);
		}catch (Exception e){
			e.printStackTrace();
		}
		return queryMap;
	}

	/**
	 * 根据当前登陆账号生成二维码url
	 * @param bo
	 * @return
	 */
	public String getQrCodeUrl(ShopBo bo,String productId){
		bo.setAppId(bo.getAppId().trim());
		bo.setAppSecret(bo.getAppSecret().trim());
		bo.setPartenKey(bo.getPartenKey().trim());
		bo.setPartenValue(bo.getPartenValue().trim());
		String appid=bo.getAppId().trim();
		String mchid=bo.getPartenKey().trim();
		//把bo里面的前后空格都去掉
		System.out.println("当前账号appid="+appid+"\t长度："+appid.length());
		System.out.println("当前账号秘钥="+bo.getAppSecret()+"\t长度："+bo.getAppSecret().length());
		System.out.println("商户号="+mchid+"\t长度："+mchid.length());
		System.out.println("秘钥="+bo.getPartenValue()+"\t长度："+bo.getPartenValue().length());
		Map<String, String> res= Sign.sMsign(bo,productId);
		String code_url="weixin://wxpay/bizpayurl?sign=SIGN&appid=APPID&mch_id=MCHID&nonce_str=NOCESTR&product_id=PRODUCTD&time_stamp=TIMESTAMP";
		code_url=code_url.replace("APPID",appid);
		code_url=code_url.replace("MCHID",mchid);
		code_url=code_url.replace("NOCESTR",res.get("nonceStr"));
		code_url=code_url.replace("TIMESTAMP",res.get("timestamp"));
		code_url=code_url.replace("SIGN",res.get("signature"));
		code_url=code_url.replace("PRODUCTD",res.get("productid"));
		System.out.println("codeurl================="+code_url);
		return code_url;
	}
	/************************************保存二维码生成记录到数据库中******end********************************************************/

	/************************************扫码回调方法start********************************************************/
	/**
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping(value = "/order/update.html")
	public void updateOrderStatus(HttpServletRequest request,HttpServletResponse response, ModelMap model) throws IOException {
		//String openId=request.getAttribute("openid")+"";
		//System.out.print(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>获取得到的openid==>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+openId);
//		String xml = "<xml><appid><![CDATA[wxb4dc385f953b356e]]></appid><bank_type><![CDATA[CCB_CREDIT]]></bank_type><cash_fee><![CDATA[1]]></cash_fee><fee_type><![CDATA[CNY]]></fee_type><is_subscribe><![CDATA[Y]]></is_subscribe><mch_id><![CDATA[1228442802]]></mch_id><nonce_str><![CDATA[1002477130]]></nonce_str><openid><![CDATA[o-HREuJzRr3moMvv990VdfnQ8x4k]]></openid><out_trade_no><![CDATA[1000000000051249]]></out_trade_no><result_code><![CDATA[SUCCESS]]></result_code><return_code><![CDATA[SUCCESS]]></return_code><sign><![CDATA[1269E03E43F2B8C388A414EDAE185CEE]]></sign><time_end><![CDATA[20150324100405]]></time_end><total_fee>1</total_fee><trade_type><![CDATA[JSAPI]]></trade_type><transaction_id><![CDATA[1009530574201503240036299496]]></transaction_id></xml>";
		ModelAndView mv=new ModelAndView();
		mv.setViewName(QrCodeContants.PAY_ORDER_PAGE);
		String inputLine;
		String notityXml = "";
		String resXml = "";
		try {
			while ((inputLine = request.getReader().readLine()) != null) {
				notityXml += inputLine;
			}
			request.getReader().close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("接收到的报文：" + notityXml);

		Map m = parseXmlToList2(notityXml);
		String openid=m.get("openid").toString();
		System.out.println("获取得到的openId======"+m.get("openid").toString());
		SessionHelper.setAttribute(request,MobileContants.USER_OPEN_ID_KEY,m.get("openid").toString());
		ShopBo shopBo= null;
		float price=1;
		String total_price="";
		String name="";//商品名称
		//String jsParam=getWXJsParam(request,price,shopBo);
		//Map m = parseXmlToList2(notityXml);
		String productId=m.get("product_id") + "";
		String key="";//商户key
		//根据返回的productId查询二维码信息
		QrCodeBo qrCodeBo=qrCodeService.findById(productId);
		if(!StringUtil.isNullOrEmpty(qrCodeBo)){
			price=qrCodeBo.getMoney();//商品金额
			float b = (float) (Math.round(price * 100)) / 100;
			total_price=(int) (b * 100) + "";
			name=qrCodeBo.getName();//商品名称
			long shopId=qrCodeBo.getShopId();//根据店铺id查询店铺信息
			shopBo=shopService.findById(shopId + "");//查询当时生成二维码的店铺信息
			System.out.println("查询到的二维码信息===================================="+shopBo);
			key=shopBo.getPartenValue();
			System.out.println("查询到的二维码信息商户key===================================="+key);
		}
		WxPayResult wpr = new WxPayResult();
		String sign=m.get("sign")+"";
		String noce_str=m.get("nonce_str")+"";
		String appid=m.get("appid")+"";
		String mchid=m.get("mch_id")+"";
		wpr.setAppid(appid);
		wpr.setOpenid(openid);
		wpr.setMchId(mchid);
		wpr.setIsSubscribe(m.get("is_subscribe") + "");
		wpr.setNonceStr(m.get("nonce_str") + "");
		wpr.setProduct_id(m.get("product_id") + "");

		Map<Object,Object> objectMap=new HashMap<Object, Object>();
		String noncestr=Sign.create_nonce_str();
		WXPrepay sendData =new WXPrepay();
		sendData.setAppid(appid);
		sendData.setMch_id(mchid);
		sendData.setNonce_str(noncestr);
		sendData.setBody(name);//商品名称
		String outTradeNo=UUID.randomUUID().toString().replace("-", "");//商户订单号
		//记录扫码记录,当有人扫码的时候，都会生成一条记录
		QrcodePayBo smJilu=new QrcodePayBo();
		smJilu.setOpenId(openid);
		System.out.println("当前店铺================================"+shopBo);
		smJilu.setShopId(shopBo.getId());
		System.out.println("当前店铺================================"+shopBo.getId());
		smJilu.setName(name);
		smJilu.setMoney(price);
		smJilu.setOutTradeNo(outTradeNo);
		System.out.println("保存扫码记录start》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》");
		boolean f=qrcodePayService.add(smJilu);//保存扫码记录到数据库,之后用户扫码成功之后，根据outTradeNo更新扫码状态
		System.out.println("保存扫码记录end》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》");

		//将辞商户订单号
		sendData.setOut_trade_no(outTradeNo);
		sendData.setTotal_fee(total_price);
		sendData.setSpbill_create_ip(request.getRemoteAddr());
		sendData.setNotify_url(notifyUrl);
		sendData.setTrade_type("NATIVE");
		sendData.setProduct_id(productId);
		// 请求签名 参数
		SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
		parameters.put("appid", sendData.getAppid());
		parameters.put("body", name);
		parameters.put("mch_id", sendData.getMch_id());
		parameters.put("nonce_str", sendData.getNonce_str());
		parameters.put("notify_url", sendData.getNotify_url());
		parameters.put("out_trade_no", sendData.getOut_trade_no());
		parameters.put("product_id", sendData.getProduct_id());
		parameters.put("spbill_create_ip", sendData.getSpbill_create_ip());
		parameters.put("total_fee", sendData.getTotal_fee());
		parameters.put("trade_type", sendData.getTrade_type());
		String signstr="appid="+appid+"&body="+name+"&mch_id="+sendData.getMch_id()+"&nonce_str="+sendData.getNonce_str()+
				"&notify_url="+ sendData.getNotify_url()+"&out_trade_no="+sendData.getOut_trade_no()+"&product_id="+sendData.getProduct_id()+
				"&spbill_create_ip="+sendData.getSpbill_create_ip()+"&total_fee="+sendData.getTotal_fee()+"&trade_type="+sendData.getTrade_type()+"&key="+key;

		String signstr2="appid="+appid+"&is_subscribe="+"N"+"&mch_id="+sendData.getMch_id()+"&nonce_str="+sendData.getNonce_str()+
				"&openid="+ openid+"&product_id="+sendData.getProduct_id()+
				"&key="+key;
		try {
			// 生成 请求签名
			System.out.println("生成签名钱的字符串=============================================="+signstr);
			String sign1 = getSignStr(signstr);
			System.out.println("生成签名后的字符串=============================================="+sign1);
		//String sign2 = getSignStr(signstr2);
			sendData.setSign(sign1);
			XStream xs = new XStream(new DomDriver("UTF-8"));
			xs.alias("xml", WXPrepay.class);
			String xml = xs.toXML(sendData);
			xml=xml.replace("__","_");
			String newXml = new String(xml.toString().getBytes(), "UTF-8");
			System.out.println("newXml============================================="+newXml);
			String returnXml = HttpRequest.sendPost("https://api.mch.weixin.qq.com/pay/unifiedorder", newXml);
			System.out.println("调用统一下单返回结果==================="+returnXml);
			//拼接xml返回扫码结果
			Map<String, String> apiResult = XMLUtil.doXMLParse(returnXml);
			// ************************************************************************************
			// 3、向微信回写订单信息
			// ************************************************************************************
			// 设置支付参数-为了返回Package 数据，回调URL 必须返回一个xml 格式的返回数据
			SortedMap<String, String> signParams = new TreeMap<String, String>();
			String hdreturencode=apiResult != null ? apiResult.get("return_code") : "FAIL";
			signParams.put("return_code", hdreturencode);
			//signParams.put("return_msg", apiResult != null ? apiResult.get("return_msg") : "下单失败");
			signParams.put("appid", appid);
			signParams.put("mch_id", mchid);
			String hdnoncestr=Sign.create_nonce_str();
			signParams.put("nonce_str", hdnoncestr);
			String prepay_id=apiResult != null ? apiResult.get("prepay_id") : "";
			signParams.put("prepay_id", prepay_id);
			signParams.put("result_code", "SUCCESS");
			//组装回调签名
			String hdsignstr="appid="+appid+"&mch_id="+mchid+"&nonce_str="+hdnoncestr+"&prepay_id="+prepay_id+
					"&result_code=SUCCESS"+"&return_code="+hdreturencode+"&key="+key;
			System.out.println("向微信回写数据前生成签名的字符串==========================="+hdsignstr);
			String hdsign=getSignStr(hdsignstr);
			System.out.println("向微信回写数据前生成签名===================="+hdsign);
			signParams.put("sign", hdsign);
			//signParams.remove("")
			// 回写订单信息
			PrintWriter writer = null;
			response.setHeader("ContentType", "text/xml");
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			try {
				writer = response.getWriter();
				writer.flush();
			    returnXml=MapToXMLString.mapToXml(signParams);
				System.out.println("map转换为xml==============================================="+returnXml);
				writer.print(returnXml);
				//   writer.print(JSONObject.fromObject(signParams).toString());
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				writer.close();
			}
		} catch (Exception e) {
			String msg = "createPayOrderForNATIVE() method catch exception";
			//return null;
		}
	}
	/****************************************扫码回调方法end******************************************************************************************/
	/**
	 * 获得签名
	 * @param signstr
	 * @return
	 */
	public static String getSignStr(String signstr) {
		String signature = "";
		//注意这里参数名必须全部小写，且必须有序
		try
		{
			MessageDigest crypt = MessageDigest.getInstance("MD5");
			crypt.update(signstr.getBytes("UTF-8"));
			signature=  Hex.encodeHexString(crypt.digest()).toUpperCase();
		}
		catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		return signature;
	}
/*	public static String createSign(SortedMap<Object,Object> parameters,String key){
		StringBuffer sb = new StringBuffer();
		Set es = parameters.entrySet();
//所有参与传参的参数按照accsii排序（升序）
		Iterator it = es.iterator();
		while(it.hasNext()) {
			Map.Entry entry = (Map.Entry)it.next();
			String k = (String)entry.getKey();
			Object v = entry.getValue();
			if(null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + key);
		String sign = MD5Util.MD5Encode(sb.toString(), "UTF-8").toUpperCase();
		return sign;
	}
	public static String getNonceStr() {
		Random random = new Random();
		return MD5Util.MD5Encode(String.valueOf(random.nextInt(10000)), "UTF-8");
	}*/
	/**
	 * description: 解析微信通知xml
	 *
	 * @param xml
	 * @return
	 * @author ex_yangxiaoyi
	 * @see
	 */
	@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
	private static Map parseXmlToList2(String xml) {
		Map retMap = new HashMap();
		try {
			StringReader read = new StringReader(xml);
			// 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
			InputSource source = new InputSource(read);
			// 创建一个新的SAXBuilder
			SAXBuilder sb = new SAXBuilder();
			// 通过输入源构造一个Document
			Document doc = (Document) sb.build(source);
			Element root = doc.getRootElement();// 指向根节点
			List<Element> es = root.getChildren();
			if (es != null && es.size() != 0) {
				for (Element element : es) {
					retMap.put(element.getName(), element.getValue());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retMap;
	}
	/**
	 * 统一下单
	 */
	public String  tongyiXiaDanResult(String param){
		String url="https://api.mch.weixin.qq.com/pay/unifiedorder";
		String res=HttpRequest.sendGet(url,param);
		return res;
	}

}
