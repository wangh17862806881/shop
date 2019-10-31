package com.fh.shop.admin.interceptor;

import com.fh.shop.admin.util.*;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Integerceptor extends HandlerInterceptorAdapter {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //Object user = request.getSession().getAttribute(SystemConst.CURRENT_USER);
        String sessionId = DistributeSession.getSessionId(response, request);
        String user = RedisUtil.get(CookKey.buildUserId(sessionId));

        System.out.println(request.getRequestURI());
        if(null != user ){
           RedisUtil.expire(CookKey.buildUserId(sessionId),SystemConst.CODE_EXPIRE);
           RedisUtil.expire(CookKey.builduserAllResource(sessionId),SystemConst.CODE_EXPIRE);
           RedisUtil.expire(CookKey.buildmenuShowId(sessionId),SystemConst.CODE_EXPIRE);
           RedisUtil.expire(CookKey.buildallResourceId(sessionId),SystemConst.CODE_EXPIRE);
            return true;
        }
        response.sendRedirect("/");
        return false;
    }

}
