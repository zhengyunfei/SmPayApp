package com.zero2ipo.mobile.dao.config;

import com.zero2ipo.common.entity.ConfValue;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * cfjCollection 实体类
 * Thu Apr 30 11:19:31 GMT+08:00 2015 zhengyunfei
 */

@Service
public interface IConfigDao {
	public ConfValue findConfValueByMap(Map<String, Object> m) ;

 
 
}

