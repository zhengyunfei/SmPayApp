package com.zero2ipo.qrcodePay.bizc;


import com.zero2ipo.qrcodePay.bo.QrcodePayBo;

import java.util.List;
import java.util.Map;


public interface IQrcodePayService {


	public List<QrcodePayBo> findAllList(Map<String, Object> map);

	public boolean update(QrcodePayBo notic);


	public QrcodePayBo findById(String id);

	public boolean add(QrcodePayBo bo);

	public boolean delete(String ids);

	public QrcodePayBo findByMap(Map<String, Object> queryMap);

	public int getTotal(Map<String, Object> queryMap);
}
