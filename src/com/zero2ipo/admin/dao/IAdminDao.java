package com.zero2ipo.admin.dao;

import org.springframework.stereotype.Repository;

/**
 * 后台用户数据交互DAO
 *
 */
@Repository
public interface IAdminDao {

	public int findAdminByEmpNo(String empNo);

}
