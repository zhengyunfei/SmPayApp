package com.zero2ipo.mobile.io;

import java.io.File;

/**
 * 
 * 文件处理帮助类
 * @author zhengyunfei
 *
 */
public class FileHelper {

	/**
	 * 判断文件是否存在
	 * @param filePath	文件路径
	 * @return
	 */
	public static boolean isHaveFile(String filePath) {
		
		boolean flg = false;
		
		if(filePath != null && !"".equals(filePath)) 
		{
			File file = new File(filePath);
			flg = file.exists();
		}
		
		return flg;
	}
	
}
