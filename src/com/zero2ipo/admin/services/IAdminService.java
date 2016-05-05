package com.zero2ipo.admin.services;

import org.springframework.stereotype.Service;

/**
 * 后台用户服务层接口
 * @author zhengyunfei
 *
 */
@Service
public interface IAdminService {
	public int findAdminByEmpNo(String empNo);
}
