package com.zero2ipo.user.webc;

import com.zero2ipo.common.http.FmUtils;
import com.zero2ipo.core.MobileContants;
import com.zero2ipo.framework.util.StringUtil;
import com.zero2ipo.mobile.utils.WaterPageContants;
import com.zero2ipo.mobile.web.SessionHelper;
import com.zero2ipo.user.bizc.IUserService;
import com.zero2ipo.user.bo.UserBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zhengyunfei on 2015/8/31.
 */
@Controller
public class UserAct {
	/*
     * 核心服务接口注入
     */
	@Autowired
	public IUserService UserService;
	/**
	 * 个人中心
	 */
	@RequestMapping(value = "/water/option.html", method = RequestMethod.GET)
	public ModelAndView addUserForGet(HttpServletRequest request,
										 HttpServletResponse response, ModelMap model ) {
		FmUtils.FmData(request, model);
		ModelAndView mv=new ModelAndView();
		//判断用户是否已经登陆
		UserBo user=(UserBo) SessionHelper.getAttribute(request, MobileContants.USER_SESSION_KEY);
		if(!StringUtil.isNullOrEmpty(user)){//已登录
			mv.setViewName(WaterPageContants.USER_OPTION_PAGE);
		}else{
			mv.setViewName(WaterPageContants.USER_LOGIN_PAGE);//未登陆
		}
		return mv;
	}
	/**
	 * 积分管理
	 */
	@RequestMapping(value = "/water/jiFenGuanLi.html", method = RequestMethod.GET)
	public ModelAndView jiFenGuanLi(HttpServletRequest request,
									  HttpServletResponse response, ModelMap model ) {
		FmUtils.FmData(request, model);
		ModelAndView mv=new ModelAndView();
		//判断用户是否已经登陆
		UserBo user=(UserBo) SessionHelper.getAttribute(request, MobileContants.USER_SESSION_KEY);
		if(!StringUtil.isNullOrEmpty(user)){//已登录
			mv.setViewName(WaterPageContants.USER_JIFEN_GUANLI_PAGE);
		}else{
			mv.setViewName(WaterPageContants.USER_LOGIN_PAGE);//未登陆
		}
		return mv;
	}
	/**
	 * 积分明细
	 */
	@RequestMapping(value = "/water/jiFenDetail.html", method = RequestMethod.GET)
	public ModelAndView jiFenDetail(HttpServletRequest request,
									HttpServletResponse response, ModelMap model,String pageNo ) {
		FmUtils.FmData(request, model);
		ModelAndView mv=new ModelAndView();
		//判断用户是否已经登陆
		UserBo user=(UserBo) SessionHelper.getAttribute(request, MobileContants.USER_SESSION_KEY);
		if(!StringUtil.isNullOrEmpty(user)){//已登录
			mv.setViewName(WaterPageContants.USER_JIFEN_DETAIL_PAGE);
		}else{
			mv.setViewName(WaterPageContants.USER_LOGIN_PAGE);//未登陆
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
	 * 积分兑换
	 */
	@RequestMapping(value = "/water/jiFenDuiHuan.html", method = RequestMethod.GET)
	public ModelAndView jiFenDuiHuan(HttpServletRequest request,
									HttpServletResponse response, ModelMap model ) {
		FmUtils.FmData(request, model);
		ModelAndView mv=new ModelAndView();
		//判断用户是否已经登陆
		UserBo user=(UserBo) SessionHelper.getAttribute(request, MobileContants.USER_SESSION_KEY);
		if(!StringUtil.isNullOrEmpty(user)){//已登录
			mv.setViewName(WaterPageContants.USER_JIFEN_DUIHUAN_PAGE);
		}else{
			mv.setViewName(WaterPageContants.USER_LOGIN_PAGE);//未登陆
		}
		return mv;
	}
	/**
	 * 优惠券
	 */
	@RequestMapping(value = "/water/youHuiQuan.html", method = RequestMethod.GET)
	public ModelAndView youHuiQuanPage(HttpServletRequest request,
									 HttpServletResponse response, ModelMap model, String couponEndTime,String status) {
		FmUtils.FmData(request, model);
		ModelAndView mv=new ModelAndView();
		//判断用户是否已经登陆
		UserBo user=(UserBo) SessionHelper.getAttribute(request, MobileContants.USER_SESSION_KEY);
		if(!StringUtil.isNullOrEmpty(user)){//已登录
			mv.setViewName(WaterPageContants.USER_YOUHUIQUAN_PAGE);
			if(!StringUtil.isNullOrEmpty(couponEndTime)){
				mv.addObject("couponEndTime",couponEndTime);
			}
			if(!StringUtil.isNullOrEmpty(status)){
				mv.addObject("status",status);
			}
		}else{
			mv.setViewName(WaterPageContants.USER_LOGIN_PAGE);//未登陆
		}
		return mv;
	}
	/**
	 * 安全设置
	 */
	@RequestMapping(value = "/water/anQuanSet.html", method = RequestMethod.GET)
	public ModelAndView anQuanSetPage(HttpServletRequest request,
									   HttpServletResponse response, ModelMap model ) {
		FmUtils.FmData(request, model);
		ModelAndView mv=new ModelAndView();
		//判断用户是否已经登陆
		UserBo user=(UserBo) SessionHelper.getAttribute(request, MobileContants.USER_SESSION_KEY);
		if(!StringUtil.isNullOrEmpty(user)){//已登录
			mv.setViewName(WaterPageContants.USER_ANQUANSET_PAGE);
		}else{
			mv.setViewName(WaterPageContants.USER_LOGIN_PAGE);//未登陆
		}
		return mv;
	}
	/**
	 * 安全退出
	 */
	@RequestMapping(value = "/water/loginout.html", method = RequestMethod.GET)
	public ModelAndView loginout(HttpServletRequest request,
									  HttpServletResponse response, ModelMap model ) {
		FmUtils.FmData(request, model);
		ModelAndView mv=new ModelAndView();
		//清除缓存
		SessionHelper.removeAttribute(request,MobileContants.USER_SESSION_KEY);
		mv.setViewName(WaterPageContants.USER_LOGIN_PAGE);//未登陆
		return mv;
	}
}

