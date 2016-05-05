package com.zero2ipo.common.freemarker.directives.shop;

import com.zero2ipo.common.freemarker.DirectiveUtils;
import com.zero2ipo.framework.util.StringUtil;
import com.zero2ipo.mobile.utils.WaterPageContants;
import com.zero2ipo.qrCode.bizc.IQrCodeService;
import com.zero2ipo.qrCode.bo.QrCodeBo;
import freemarker.core.Environment;
import freemarker.template.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 二维码生成记录
 * @author zhengYunfei
 *
 */
public class QrCodeCreateListDirective implements TemplateDirectiveModel{
	private static final String PARAM_SHOP_ID="shopId";
	private static  final String  PARAM_PAGENO="pageNo";
	public void execute(Environment env, Map params, TemplateModel[] model,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		List<QrCodeBo> list=null;
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
			size=qrCodeService.getTotal(queryMap);
			list=qrCodeService.findAllList(queryMap);
			if(!StringUtil.isNullOrEmpty(list)&&list.size()>0){
				env.setVariable("qrcodeList", ObjectWrapper.DEFAULT_WRAPPER.wrap(list));
			}
			env.setVariable("recordCount", ObjectWrapper.DEFAULT_WRAPPER.wrap(size));
			//env.setVariable("pageSize", ObjectWrapper.DEFAULT_WRAPPER.wrap(PAGESIZE));
			//env.setVariable("pageNo", ObjectWrapper.DEFAULT_WRAPPER.wrap(pageNo));

		} catch (Exception e) {
			e.printStackTrace();
		}
		body.render(env.getOut());
	}
	/*
	 * 收藏服务层接口注入
	 */
	@Resource(name = "QrCodeService")
	private IQrCodeService qrCodeService;
}
