package com.zero2ipo.admin.services.impl;

import com.zero2ipo.admin.dao.IAdminDao;
import com.zero2ipo.admin.services.IAdminService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 后台用户服务层接口实现
 * @author zhengyunfei
 *
 */
@Component("adminService")
public class AdminDaoImpl implements IAdminService {
	/*
	 * 后台用户DAO接口注入
	 */
	@Resource(name = "adminDao")
	private IAdminDao adminDao;

	@Override
	public int findAdminByEmpNo(String empNo) {
		return adminDao.findAdminByEmpNo(empNo);
	}
}
