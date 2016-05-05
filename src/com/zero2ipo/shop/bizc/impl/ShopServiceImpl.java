package com.zero2ipo.shop.bizc.impl;

import com.zero2ipo.framework.exception.BaseException;
import com.zero2ipo.framework.log.BaseLog;
import com.zero2ipo.mobile.dao.base.IbatisBaseDao;
import com.zero2ipo.shop.bizc.IShopService;
import com.zero2ipo.shop.bo.ShopBo;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Created by zhengyunfei on 2016/2/24.
 */
@Service("shopService")
public  class ShopServiceImpl extends IbatisBaseDao implements IShopService {
	public final static String NAMESPACE="mobile.Shop.";
	public final static String COMMON="Shop";
	public final static String ADD=NAMESPACE+"add_"+COMMON;
	public final static String UPDATE=NAMESPACE+"upd_"+COMMON;
	public final static String DELETE=NAMESPACE+"del_"+COMMON;
	public final static String FINDALLLIST=NAMESPACE+"find"+COMMON+"List";
	public final static String FINDALLLISTCOUNT=NAMESPACE+"find"+COMMON+"ListCount";
	public final static String FINDBYID=NAMESPACE+"find"+COMMON+"ById";
	public final static String FINDBYMAP=NAMESPACE+"find"+COMMON+"ByMap";
	@Override
	public boolean add(ShopBo bo) {
		boolean flag=false;
		try{
			this.insert(ADD, bo);
			flag=true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean update(ShopBo bo) {
		boolean flag=false;
		try{
			this.update(UPDATE, bo);
			flag=true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean delete(String ids) {
		boolean flag=false;
		try {
			Map map = new HashMap();
			map.put("id", ids.split(","));
			this.delete(DELETE, map);
			// 删除成功
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			BaseLog.e(this.getClass(), "del_Shop 删除出错", e);
			throw new BaseException("删除出错！", e);
		}
		return flag;
	}

	@Override
	public List<ShopBo> findAllList(Map<String, Object> queryMap) {
		List<ShopBo> list = null;
		try {
			list = (List<ShopBo>) this.queryAll(FINDALLLIST, queryMap);
		} catch (Exception e) {
			e.printStackTrace();
			BaseLog.e(this.getClass(), "findAllList 查询列表", e);
			throw new BaseException("查询列表出错！", e);
		}
		return list;
	}
	@Override
	public ShopBo findById(String id){
		ShopBo bo=null;
		try {
			Map<String,Object> queryMap=new HashMap<String,Object>();
			queryMap.put("id", id);
			bo = (ShopBo) this.query(FINDBYID, queryMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bo;
	}
	@Override
	public ShopBo findByMap(Map<String,Object> queryMap){
		ShopBo bo=null;
		try {
			bo = (ShopBo) this.query(FINDBYMAP, queryMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bo;
	}
}
