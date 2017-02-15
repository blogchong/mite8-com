package com.mite8.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * Author: blogchong
 * Time:  2016/12/7.
 * Email: blogchong#qq.com
 * 公众号：数据虫巢 ID:blogchong
 * Desc:  获取真实IP
 */
public class GetAddrHostUtils {

    public static String getAddrHost(HttpServletRequest request){
//        String ip=request.getHeader("x-forwarded-for");
//        if(ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)){
//            ip=request.getHeader("Proxy-Client-IP");
//        }
//        if(ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)){
//            ip=request.getHeader("WL-Proxy-Client-IP");
//        }
//        if(ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)){
//            ip=request.getRemoteAddr();
//        }
        return request.getRemoteAddr();
    }

}
