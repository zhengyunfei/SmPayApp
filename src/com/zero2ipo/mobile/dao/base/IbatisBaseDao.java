package com.zero2ipo.mobile.dao.base;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * Ibatis基类
 * @author zhengyunfei
 *
 */
public class IbatisBaseDao extends SqlMapClientDaoSupport{
	
	@Resource(name="sqlMapClient")
	private SqlMapClient sqlMapClient;

	@PostConstruct
	public void initSqlMapClient() {
		super.setSqlMapClient(sqlMapClient);
	}

	/* 基础增加方法 */
	public Object insert(String statementName, Object obj) {
		return super.getSqlMapClientTemplate().insert(statementName, obj);
	}

	/* 基础修改方法 */
	public int update(String statementName, Object obj) {
		return getSqlMapClientTemplate().update(statementName, obj);
	}

	/* 基础单条记录查询 */
	public Object query(String statementName, Object obj) {
		return super.getSqlMapClientTemplate().queryForObject(statementName, obj);
	}
	
	/* 基础单条记录查询 */
	public Object query(String statementName) {
		return super.getSqlMapClientTemplate().queryForObject(statementName);
	}

	/* 基础多条记录查询 */
	public List<?> queryAll(String statementName, Object obj) {
		return super.getSqlMapClientTemplate().queryForList(statementName, obj);
	}
	
	/* 基础多条记录查询 */
	public List<?> queryAll(String statementName) {
		return super.getSqlMapClientTemplate().queryForList(statementName);
	}
	/* 基础删除方法*/
	public int delete(String statementName,Object parameterObject){
		return super.getSqlMapClientTemplate().delete(statementName, parameterObject);
	}
}
