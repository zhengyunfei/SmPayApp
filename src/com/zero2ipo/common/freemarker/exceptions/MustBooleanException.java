package com.zero2ipo.common.freemarker.exceptions;

import freemarker.template.TemplateModelException;

/*
 * 必须为boolean异常类
 */
@SuppressWarnings("serial")
public class MustBooleanException extends TemplateModelException {
	
	public MustBooleanException(String paramName) {
		super("The \"" + paramName + "\" parameter must be a boolean.");
	}

}
