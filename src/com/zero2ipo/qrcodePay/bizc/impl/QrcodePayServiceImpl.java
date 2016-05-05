package com.zero2ipo.qrcodePay.bizc.impl;
import com.zero2ipo.framework.exception.BaseException;
import com.zero2ipo.framework.log.BaseLog;
import com.zero2ipo.mobile.dao.base.IbatisBaseDao;
import com.zero2ipo.qrcodePay.bizc.IQrcodePayService;
import com.zero2ipo.qrcodePay.bo.QrcodePayBo;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Created by zhengyunfei on 2016/2/24.
 */
@Service("qrcodePayService")
public  class QrcodePayServiceImpl extends IbatisBaseDao implements IQrcodePayService {
	public final static String NAMESPACE="mobile.ShopPay.";
	public final static String COMMON="ShopPay";
	public final static String ADD=NAMESPACE+"add"+COMMON;
	public final static String UPDATE=NAMESPACE+"upd"+COMMON;
	public final static String DELETE=NAMESPACE+"del"+COMMON;
	public final static String FINDALLLIST=NAMESPACE+"find"+COMMON+"List";
	public final static String FINDALLLISTCOUNT=NAMESPACE+"find"+COMMON+"ListCount";
	public final static String FINDBYID=NAMESPACE+"find"+COMMON+"ById";
	public final static String FINDBYMAP=NAMESPACE+"find"+COMMON+"ByMap";
	@Override
	public boolean add(QrcodePayBo bo) {
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
	public boolean update(QrcodePayBo bo) {
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
	public List<QrcodePayBo> findAllList(Map<String, Object> queryMap) {
		List<QrcodePayBo> list = null;
		try {
			list = (List<QrcodePayBo>) this.queryAll(FINDALLLIST, queryMap);
		} catch (Exception e) {
			e.printStackTrace();
			BaseLog.e(this.getClass(), "findAllList 查询列表", e);
			throw new BaseException("查询列表出错！", e);
		}
		return list;
	}
	@Override
	public QrcodePayBo findById(String id){
		QrcodePayBo bo=null;
		try {
			Map<String,Object> queryMap=new HashMap<String,Object>();
			queryMap.put("id", id);
			bo = (QrcodePayBo) this.query(FINDBYID, queryMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bo;
	}
	@Override
	public QrcodePayBo findByMap(Map<String,Object> queryMap){
		QrcodePayBo bo=null;
		try {
			bo = (QrcodePayBo) this.query(FINDBYMAP, queryMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bo;
	}

	@Override
	public int getTotal(Map<String, Object> queryMap) {
		int total=0;
		try {
			total = (Integer) this.query(FINDALLLISTCOUNT, queryMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return total;
	}
}
