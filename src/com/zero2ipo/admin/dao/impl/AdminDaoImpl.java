package com.zero2ipo.admin.dao.impl;

import com.zero2ipo.admin.dao.IAdminDao;
import com.zero2ipo.mobile.dao.base.IbatisBaseDao;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 后台用户数据交互DAO
 * @author zhengyunfei
 *
 */
@Component("adminDao")
public class AdminDaoImpl extends IbatisBaseDao implements IAdminDao {
	private static final String COMMON = "mobile.Admin.";
	private static final String BO = "Admin";
	private static final String FIND_ADMIN_BY_EMPNO = COMMON+"find"+BO+"ByEmpNo";
	public int findAdminByEmpNo(String empNo){
		Map<String,Object> query=new HashMap<String,Object>();
		query.put("userNo",empNo);
		int count=(Integer)this.query(FIND_ADMIN_BY_EMPNO,query);
		return count;
	}

}
