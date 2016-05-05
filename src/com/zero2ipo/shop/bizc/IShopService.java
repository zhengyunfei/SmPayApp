package com.zero2ipo.shop.bizc;


import com.zero2ipo.shop.bo.ShopBo;

import java.util.List;
import java.util.Map;


public interface IShopService {


	public List<ShopBo> findAllList(Map<String, Object> map);

	public boolean update(ShopBo notic);


	public ShopBo findById(String id);

	public boolean add(ShopBo bo);

	public boolean delete(String ids);

	public ShopBo findByMap(Map<String, Object> queryMap);
}
