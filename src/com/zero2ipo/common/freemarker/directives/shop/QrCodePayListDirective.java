package com.zero2ipo.common.freemarker.directives.shop;

import com.zero2ipo.common.freemarker.DirectiveUtils;
import com.zero2ipo.framework.util.StringUtil;
import com.zero2ipo.mobile.utils.WaterPageContants;
import com.zero2ipo.qrcodePay.bizc.IQrcodePayService;
import com.zero2ipo.qrcodePay.bo.QrcodePayBo;
import freemarker.core.Environment;
import freemarker.template.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 二维码付款记录
 * @author zhengYunfei
 *
 */
public class QrCodePayListDirective implements TemplateDirectiveModel{
	private static final String PARAM_SHOP_ID="shopId";
	private static  final String  PARAM_PAGENO="pageNo";
	public void execute(Environment env, Map params, TemplateModel[] model,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		List<QrcodePayBo> list=null;
		try {
			String shopId=DirectiveUtils.getString(PARAM_SHOP_ID, params);
			String pageNo= DirectiveUtils.getString(PARAM_PAGENO, params);
			int PAGESIZE= WaterPageContants.PAGE_SIZE;
			Map<String,Object> queryMap=new HashMap<String,Object>();
			queryMap.put("shopId", shopId);
			if(StringUtil.isNullOrEmpty(pageNo)){
				pageNo="1";
			}
			queryMap.put("start", (Integer.valueOf(pageNo) - 1) * PAGESIZE);
			queryMap.put("end",PAGESIZE*Integer.valueOf(pageNo));
			int size=0;
			size=qrcodePayService.getTotal(queryMap);
			list=qrcodePayService.findAllList(queryMap);
			env.setVariable("recordCount", ObjectWrapper.DEFAULT_WRAPPER.wrap(size));
			if(!StringUtil.isNullOrEmpty(list)&&list.size()>0){
				env.setVariable("qrcodePayList", ObjectWrapper.DEFAULT_WRAPPER.wrap(list));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		body.render(env.getOut());
	}
	/*
	 * 收藏服务层接口注入
	 */
	@Resource(name = "qrcodePayService")
	private IQrcodePayService qrcodePayService;
}
