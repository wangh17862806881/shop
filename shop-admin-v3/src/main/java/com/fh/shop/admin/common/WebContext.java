package com.fh.shop.admin.common;

import javax.servlet.http.HttpServletRequest;

//工具类
public class WebContext {
    //创建对象
    private static ThreadLocal<HttpServletRequest> threadLocal = new ThreadLocal<>();

    //给当前先线程副本赋值
    public static void setRequest(HttpServletRequest request){
        threadLocal.set(request);
    }

    //从当前线程取值
    public static HttpServletRequest  getRequest(){
        HttpServletRequest request = threadLocal.get();
        return request;
    }


    //清空当前线程  释放资源
    public static void removeRequest(){
        threadLocal.remove();
    }

}
