package com.zero2ipo.qrCode.bizc.impl;

import com.zero2ipo.framework.exception.BaseException;
import com.zero2ipo.framework.log.BaseLog;
import com.zero2ipo.mobile.dao.base.IbatisBaseDao;
import com.zero2ipo.qrCode.bizc.IQrCodeService;
import com.zero2ipo.qrCode.bo.QrCodeBo;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhengyunfei on 2016/2/24.
 */
@Service("QrCodeService")
public  class QrCodeServiceImpl extends IbatisBaseDao implements IQrCodeService {
	public final static String NAMESPACE="mobile.QrCode.";
	public final static String COMMON="QrCode";
	public final static String ADD=NAMESPACE+"add"+COMMON;
	public final static String UPDATE=NAMESPACE+"upd"+COMMON;
	public final static String UPDATEBYQRCODEIMAGE=NAMESPACE+"upd"+COMMON+"Image";
	public final static String DELETE=NAMESPACE+"del"+COMMON;
	public final static String FINDALLLIST=NAMESPACE+"find"+COMMON+"List";
	public final static String FINDALLLISTCOUNT=NAMESPACE+"find"+COMMON+"ListCount";
	public final static String FINDBYID=NAMESPACE+"find"+COMMON+"ById";
	public final static String FINDBYMAP=NAMESPACE+"find"+COMMON+"ByMap";
	@Override
	public String add(QrCodeBo bo) {
		String id="";
		try{
			id=(String)this.insert(ADD, bo);
		}catch(Exception e){
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public boolean update(QrCodeBo bo) {
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
			BaseLog.e(this.getClass(), "del_QrCode 删除出错", e);
			throw new BaseException("删除出错！", e);
		}
		return flag;
	}

	@Override
	public List<QrCodeBo> findAllList(Map<String, Object> queryMap) {
		List<QrCodeBo> list = null;
		try {
			list = (List<QrCodeBo>) this.queryAll(FINDALLLIST, queryMap);
		} catch (Exception e) {
			e.printStackTrace();
			BaseLog.e(this.getClass(), "findAllList 查询列表", e);
			throw new BaseException("查询列表出错！", e);
		}
		return list;
	}
	@Override
	public QrCodeBo findById(String id){
		QrCodeBo bo=null;
		try {
			Map<String,Object> queryMap=new HashMap<String,Object>();
			queryMap.put("id", id);
			bo = (QrCodeBo) this.query(FINDBYID, queryMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bo;
	}
	@Override
	public QrCodeBo findByMap(Map<String,Object> queryMap){
		QrCodeBo bo=null;
		try {
			bo = (QrCodeBo) this.query(FINDBYMAP, queryMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bo;
	}

	@Override
	public boolean updateQrcodeImage(QrCodeBo bo) {
		boolean flag=false;
		try{
			this.update(UPDATEBYQRCODEIMAGE, bo);
			flag=true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public int getTotal(Map<String, Object> queryMap) {
		int total=0;
		try{
			total=(Integer)this.query(FINDALLLISTCOUNT,queryMap);
		}catch(Exception e){
			e.printStackTrace();
		}
		return total;
	}
}
