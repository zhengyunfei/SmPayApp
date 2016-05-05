package com.zero2ipo.core.code;

import javax.servlet.http.HttpServletRequest;

/**
 * 验证码处理类
 * 
 * @author zhengyunfei
 *
 */
public class VerificationCode {
	
	private int codeNum = 0;
	
	public VerificationCode(String codeNum) {
		this.codeNum = Integer.valueOf(codeNum);
	}

	public VerificationCode() {
		// TODO Auto-generated constructor stub
	}

	private String VERIFICATION_CODE = "VERIFICATIONCODE";

	/**
	 * 验证码生成
	 * 
	 * @return
	 */
	public  String generate(HttpServletRequest request) {
		String s = "";
		for(int i=0;i<4;i++){
			s += (int) (Math.random() * 10);
		}
		
		//测试用
		//s = "1";
		remove(request);
		request.getSession().setAttribute(VERIFICATION_CODE, s);
		return s;
	}

	/**
	 * 获取验证码
	 * 
	 * @param request
	 * @return
	 */
	public String get(HttpServletRequest request) {
		return (String) request.getSession().getAttribute(VERIFICATION_CODE);
	}

	/**
	 * 验证码销毁
	 */
	public void remove(HttpServletRequest request) {
		request.getSession().removeAttribute(VERIFICATION_CODE);
	}

	/**
	 * 验证码清空
	 */
	public void clear(HttpServletRequest request) {
		request.getSession().setAttribute(VERIFICATION_CODE, "");
	}

}