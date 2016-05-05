package com.zero2ipo.common.log;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.net.SyslogAppender;

/**
 * 自定义IP记录日志类
 * 用于记录用户登录日志
 * @author zhengyunfei
 *
 */
public class IpLog {
	
	/*
     * 继承Level 
     */  
    private static class IpLogLevel extends Level{  

		private static final long serialVersionUID = 1L;

		public IpLogLevel(int level, String levelStr, int syslogEquivalent) {  
            super(level, levelStr, syslogEquivalent);  
        }         
    }  
      
    /* 
     * 自定义级别名称，以及级别范围 
     */  
    private static final Level Ipevel = new IpLogLevel(25000, "IP", SyslogAppender.LOG_LOCAL0);  
      
    /* 
     * 使用日志打印logger中的log方法 
     */  
    public static void ipLog(Logger logger,Object objLogInfo){  
        logger.log(Ipevel, objLogInfo);  
    } 
    
}
