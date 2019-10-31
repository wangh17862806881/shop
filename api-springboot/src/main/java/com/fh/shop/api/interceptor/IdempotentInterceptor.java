package com.fh.shop.api.interceptor;

import com.fh.shop.api.annotation.Idempotent;
import com.fh.shop.api.common.ResponseEnum;
import com.fh.shop.api.exception.HeaderExceptionInfo;
import com.fh.shop.api.util.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class IdempotentInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        //判断当前请求是否访问需要设置幂等性的方法
        if(!method.isAnnotationPresent(Idempotent.class)){
           return true;
        }
        String token = request.getHeader("token_idempotent");
        //判断当前请求的是否含有头信息
        if(StringUtils.isEmpty(token)){
            throw new HeaderExceptionInfo(ResponseEnum.HEADER_IS_NULL);
        }
        //判断当前请求在redis中是否存在
        boolean token_idempotent = RedisUtil.exist(token);
        if(!token_idempotent){
            throw new HeaderExceptionInfo(ResponseEnum.HEADER_IS_ERROR);
        }
        //判断当前请求是否成功能删除redis  成功删除则说明是第一次请求  三处不了则证明是重复提交
        Long token_idempotent1 = RedisUtil.del(token);
        if(token_idempotent1 <= 0){
            throw new HeaderExceptionInfo(ResponseEnum.REQUEST_IS_REPLACE);
        }
        return true;
    }



}
