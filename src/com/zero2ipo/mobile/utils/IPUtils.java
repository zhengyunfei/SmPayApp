package com.zero2ipo.mobile.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhengyunfei on 2015/7/28.
 */
public class IPUtils {
    /**
     * 获取客户端ip地址
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {

        String ip = request.getHeader("x-forwarded-for");

        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {

            ip = request.getHeader("Proxy-Client-IP");

        }

        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {

            ip = request.getHeader("WL-Proxy-Client-IP");

        }

        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {

            ip = request.getRemoteAddr();

        }
        return ip;

    }
}
