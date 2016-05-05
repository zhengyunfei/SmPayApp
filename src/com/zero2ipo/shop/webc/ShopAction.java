package com.zero2ipo.shop.webc;

import com.zero2ipo.common.http.FmUtils;
import com.zero2ipo.common.web.BaseCtrl;
import com.zero2ipo.framework.util.StringUtil;
import com.zero2ipo.mobile.web.SessionHelper;
import com.zero2ipo.shop.bizc.IShopService;
import com.zero2ipo.shop.bo.ShopBo;
import com.zero2ipo.shop.bo.ShopContants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
/**
 * 优惠券功能
 * @author zhengyunfei
 *
 */
@Controller
public class ShopAction extends BaseCtrl {

	@Resource(name = "shopService")
	private IShopService shopService;
	/**********************************登陆操作start******************************************************/
	/**
	 * 登陆页面
	 * @return
	 */
	@RequestMapping(value = "/shop/login.html",method = RequestMethod.GET)
    public ModelAndView forLoginPage(HttpServletRequest request,
								HttpServletResponse response, ModelMap model){
		FmUtils.FmData(request, model);
		ModelAndView mv=new ModelAndView();
		mv.setViewName(ShopContants.LOGIN_PAGE);
        return mv;
    }
	/**
	 * 退出页面
	 * @return
	 */
	@RequestMapping(value = "/shop/logout.html",method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request,
									 HttpServletResponse response, ModelMap model){
		FmUtils.FmData(request, model);
		ModelAndView mv=new ModelAndView();
		SessionHelper.removeAttribute(request,ShopContants.CURRENT_LOGIN_KEY);
		mv.setViewName(ShopContants.LOGIN_PAGE);
		return mv;
	}

	/**
	 * 登陆
	 * @return
	 */
	@RequestMapping(value = "/shop/loginAjax.html",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> forLoginPost(HttpServletRequest request,
									 HttpServletResponse response, ModelMap model,String mobile,String password){
		Map<String,Object> result=new HashMap<String, Object>();
		try{
			//根据用户输入的手机号码和密码查询此账号是否存在
			Map<String,Object> queryMap=new HashMap<String, Object>();
			queryMap.put("mobile",mobile);
			queryMap.put("password",password);
			ShopBo shopBo=shopService.findByMap(queryMap);
			if(!StringUtil.isNullOrEmpty(shopBo)){
				//登陆成功
				result.put("flg",true);
				//保存店铺会员到缓存中
				SessionHelper.setAttribute(request,ShopContants.CURRENT_LOGIN_KEY,shopBo);
			}else{//账号获取密码错误
				result.put("flg",false);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
	/**********************************登陆操作end******************************************************/

}
