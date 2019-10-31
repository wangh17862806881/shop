package com.fh.shop.admin.common;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

//过滤器
public class WebContextFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
       //赋值
        WebContext.setRequest((HttpServletRequest) request);
        //继续执行
        try {
            chain.doFilter(request,response);
        } finally {
            //释放资源
            WebContext.removeRequest();
        }

    }

    @Override
    public void destroy() {

    }
}
