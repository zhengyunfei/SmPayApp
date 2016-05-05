package com.zero2ipo.qrCode.bizc;


import com.zero2ipo.qrCode.bo.QrCodeBo;

import java.util.List;
import java.util.Map;


public interface IQrCodeService {


	public List<QrCodeBo> findAllList(Map<String, Object> map);

	public boolean update(QrCodeBo notic);


	public QrCodeBo findById(String id);

	public String add(QrCodeBo bo);

	public boolean delete(String ids);

	public QrCodeBo findByMap(Map<String, Object> queryMap);

	public boolean updateQrcodeImage(QrCodeBo bo);

	public int getTotal(Map<String, Object> queryMap);
}
