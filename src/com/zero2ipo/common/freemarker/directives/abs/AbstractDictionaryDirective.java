package com.zero2ipo.common.freemarker.directives.abs;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.zero2ipo.common.freemarker.DirectiveUtils;
import com.zero2ipo.mobile.services.dictionary.IDictionaryService;

import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 字典freemarker驱动基类
 * @author zhengyunfei
 *
 */
public abstract class AbstractDictionaryDirective implements TemplateDirectiveModel{

	private static final String PARAM_VERSION = "version";			//版本号
	
	private static final String PARAM_TYPE = "type";				//类型
	
	private static final String PARAM_PCODE = "sort";				//父类ID
	
	private static final String PARAM_CODEVALUE = "codeValue";		//codeValue
	
	/*
	 * 获取版本参数
	 */
	protected String getVersion (Map<String, TemplateModel> params) throws TemplateException {
		return DirectiveUtils.getString(PARAM_VERSION, params);
	}
	
	/*
	 * 获取类型参数
	 */
	protected String getType (Map<String, TemplateModel> params) throws TemplateException {
		return DirectiveUtils.getString(PARAM_TYPE, params);
	}
	
	/*
	 * 获取父ID参数
	 */
	protected String getPcode (Map<String, TemplateModel> params) throws TemplateException {
		return DirectiveUtils.getString(PARAM_PCODE, params);
	}
	
	/*
	 * 获取codeValue参数
	 */
	protected String getCodeValue (Map<String, TemplateModel> params) throws TemplateException {
		return DirectiveUtils.getString(PARAM_CODEVALUE, params);
	}
	
	/*
	 * 字典表服务接口注入
	 */
	@Autowired
	protected IDictionaryService dictionaryService ;
	
}
