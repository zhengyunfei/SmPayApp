package com.zero2ipo.qrcodePay.webc;
import com.zero2ipo.common.web.BaseCtrl;
import com.zero2ipo.qrcodePay.bizc.IQrcodePayService;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
/**
 * 付款记录功能
 * @author zhengyunfei
 */
@Controller
public class QrcodePayAction extends BaseCtrl {

	@Resource(name = "qrcodePayService")
	private IQrcodePayService qrcodePayService;



}
