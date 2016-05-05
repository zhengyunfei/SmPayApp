package com.zero2ipo.common.freemarker.directives;

import com.zero2ipo.common.freemarker.DirectiveUtils;
import com.zero2ipo.framework.util.StringUtil;
import com.zero2ipo.mobile.services.config.IConfManage;
import freemarker.core.Environment;
import freemarker.template.*;
import  com.zero2ipo.common.entity.ConfValue;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 根据codeValue查询code
 * @author zhengYunfei
 *
 */
public class FindConfigByConfigKeyDirective implements TemplateDirectiveModel{
	private static final String PARAM_CODE_VALUE = "confKey";
	public void execute(Environment env, Map params, TemplateModel[] model,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		ConfValue entity=null;
		String confKey= DirectiveUtils.getString(PARAM_CODE_VALUE, params);
		try {
			Map<String,Object> m=new HashMap<String, Object>();
			m.put("confKey",confKey);
			entity=confManage.findConfValueByMap(m);
			if(!StringUtil.isNullOrEmpty(entity)){
				env.setVariable("config", ObjectWrapper.DEFAULT_WRAPPER.wrap(entity));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		body.render(env.getOut());
	}
	/*
	 * 收藏服务层接口注入
	 */
	@Resource(name="confManage")
	private IConfManage confManage;
}
