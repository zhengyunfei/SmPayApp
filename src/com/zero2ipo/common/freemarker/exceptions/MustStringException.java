package com.zero2ipo.common.freemarker.exceptions;

import freemarker.template.TemplateModelException;

/*
 * 必须为String异常类
 */
@SuppressWarnings("serial")
public class MustStringException extends TemplateModelException {
	
	public MustStringException(String paramName) {
		super("The \"" + paramName + "\" parameter must be a string.");
	}
	
}
