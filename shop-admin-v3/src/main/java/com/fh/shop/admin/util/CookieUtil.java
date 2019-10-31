package com.fh.shop.admin.util;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {
    //读取cookie方法
    public static String readCookieValue(String cookieKey,HttpServletRequest request){
        //判断当前cookieName 是否存在
        if(StringUtils.isEmpty(cookieKey)){
            return "";
        }
        //获取当前cookie数组
        Cookie[] cookies = request.getCookies();
        //判断当前cookie是否存在值
        if(null == cookies){
            return "";
        }
        //遍历cookie  判断cookie中是否有我们想要的值
        for (Cookie cookie : cookies) {
            if(cookieKey.equals(cookie.getName())){
                return cookie.getValue();
            }
        }
        //没有找 返回空字符串
        return "";
    }

    //写cookie方法
    public static void writeCookie(HttpServletResponse response,String cookieName,String cookieValue,String domain){
        Cookie cookie = new Cookie(cookieName,cookieValue);
        cookie.setDomain(domain);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

}
