package com.zero2ipo.core;

/**
 * 页面地址管理类
 * @author zhengyunfei
 *
 */
public class MobilePageContants {

	/**
	 * 手机端页面地址
	 */
	
	public static final String FM_PAGE_WDXCQ = "mobile/user/washCoupon"; //我的洗车券
	public static final String FM_PAGE_GMXCQ = "pay/couponbuy"; //购买洗车券

	public static final String FM_PAGE_MAIN = "mobile/xc/index";    	//主页
	public static final String CAR_DETAIL_PAGE = "mobile/xc/xzfw";    	//主页
	public static final String ADMIN_ORDER_DETAIL_PAGE = "mobile/admin/adminOrderDetail";    	//主页
	public static final String MY_DETAIL_PAGE = "mobile/xc/xzfw";    	//主页
	public static final String MY_ORDER_PAGE = "mobile/user/myOrder";    	//主页
	public static final String FM_MY_PAGE = "mobile/main/mobile_fund";    	//主页
	public static final String BOOK_REGEDIT="mobile/user/regedit";//注册
	public static final String FM_USER_LOGIN ="mobile/user/login2";				//未授权登陆需要先通过授权连接再跳转到登录页面
	public static final String BOOK_UPDATE_MOBILE="mobile/book/updateMobile";//注册
	public static final String FM_OAUTHED_LOGIN="mobile/user/login";//授权后页面登陆
	public static final String ADMIN_INDEX_PAGE="mobile/admin/orderlist";//洗车工登陆后主页
	public static final String ADMIN_LOGIN_PAGE="mobile/admin/adminLogin";//洗车工登陆
	public static final String RESET_PASSWORD_PAGE="mobile/user/resetPassword";//重置密码页面
	public static final String QIANDAO_PAGE="mobile/user/qiandao";//重置密码页面

	
	public static final String FM_USER_HOME = "mobile/user/vip";				//个人中心
	
	public static final String FM_USER_OPTION = "mobile/user/option";				//个人中心
	
	public static final String FM_LZH = "mobile/user/orderlist";				//个人中心
	
	public static final String ADD_ADDRESS="mobile/user/addAddress";//添加常用地址
	public static final String LOVE_ADDRESS="mobile/user/Loveaddress";//常用地址


	/****/
	public static final String OAUTH_LINK ="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx5d2523ead18e4d17&redirect_uri=http%3A%2F%2Fxxxx.sinaapp.com%2FoauthServlet&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
	
}
