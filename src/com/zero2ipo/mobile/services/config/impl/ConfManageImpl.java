/**
 * Copyright (c) 2010 CEPRI,Inc.All rights reserved.
 * Created by 2012-6-1 
 */
package com.zero2ipo.mobile.services.config.impl;

import com.zero2ipo.common.entity.ConfValue;
import com.zero2ipo.mobile.dao.config.IConfigDao;
import com.zero2ipo.mobile.services.config.IConfManage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @title :项目配置部署
 * @description :项目配置部署
 * @author: zhengYunFei
 * @data: 2013-7-11
 */
@Service("confManage")
public class ConfManageImpl implements IConfManage{
	
	@Resource(name = "configDao")
	private IConfigDao configDao;
	public ConfValue findConfValueByMap(Map<String, Object> m) {
		return configDao.findConfValueByMap(m);
	}

}
