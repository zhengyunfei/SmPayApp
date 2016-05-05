package com.zero2ipo.common.domain.exception;

import freemarker.template.TemplateModelException;

/**
 * 跨域文件上传异常
 * @author zhengyunfei
 *
 */
public class DomainFileUploadException extends TemplateModelException{

	private static final long serialVersionUID = 1L;

	public DomainFileUploadException() {
		super("跨域文件上传异常");
	}
	
}
