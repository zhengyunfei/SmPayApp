package com.zero2ipo.common.freemarker.directives;

import com.zero2ipo.common.freemarker.DirectiveUtils;
import com.zero2ipo.framework.util.StringUtil;
import com.zero2ipo.mobile.services.dictionary.IDictionaryService;
import com.zero2ipo.module.entity.code.CodeInfoEntity;
import freemarker.core.Environment;
import freemarker.template.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

/**
 * 
 * 根据codeValue查询code
 * @author zhengYunfei
 *
 */
public class FindDirectoryByCodeValueDirective implements TemplateDirectiveModel{
	private static final String PARAM_CODE_VALUE = "codeValue";
	public void execute(Environment env, Map params, TemplateModel[] model,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		CodeInfoEntity entity=null;
		String codeValue= DirectiveUtils.getString(PARAM_CODE_VALUE, params);
		try {
			entity=dictionaryService.findCodeInfoByCodeValue(codeValue);
			if(!StringUtil.isNullOrEmpty(entity)){
				env.setVariable("code", ObjectWrapper.DEFAULT_WRAPPER.wrap(entity));
				env.setVariable("codeName", ObjectWrapper.DEFAULT_WRAPPER.wrap(entity.getCodeName()));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		body.render(env.getOut());
	}
	/*
	 * 收藏服务层接口注入
	 */
	@Resource(name="dictionaryService")
	private IDictionaryService dictionaryService;
}
