package com.zero2ipo.admin.webc;

import com.zero2ipo.admin.services.IAdminService;
import com.zero2ipo.common.http.FmUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zhengyunfei on 2015/8/31.
 */
@Controller
public class AdminAct {
	/*
     * 核心服务接口注入
     */
	@Autowired
	public IAdminService adminService;

	/**
	 * 查询员工号是否存在
	 * 返回员工个数
	 */
	@RequestMapping(value = "/water/queryEmpNoIsExsit.html", method = RequestMethod.POST)
	@ResponseBody
	public int addAddressForGet(HttpServletRequest request,
										 HttpServletResponse response, ModelMap model,String empNo ) {
		FmUtils.FmData(request, model);
		int count=adminService.findAdminByEmpNo(empNo);
		return count;
	}





}

