package com.fh.shop.api.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.fh.shop.api.annotation.check;
import com.fh.shop.api.common.ResponseEnum;
import com.fh.shop.api.exception.HeaderExceptionInfo;
import com.fh.shop.api.member.vo.MemberVo;
import com.fh.shop.api.util.KeyUtil;
import com.fh.shop.api.util.Md5Util;
import com.fh.shop.api.util.RedisUtil;
import com.fh.shop.api.util.SystemConst;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Base64;

public class LoginInterceptor extends HandlerInterceptorAdapter {

//登陆拦截器
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        /*  Access-Control-Allow-Origin:| * // 授权的源控制  允许访问的IP地址
        Access-Control-Max-Age:// 授权的时间
        Access-Control-Allow-Credentials: true | false // 控制是否开启与Ajax的Cookie提交方式
        Access-Control-Allow-Methods:[,]* // 允许请求的HTTP Method  比如get post options
        Access-Control-Allow-Headers:[,]* // 控制哪些header能发送真正的请求
        response.setHeader(org.springframework.http.HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS,"x-auth,Content-Type");*/

        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Methods","POST,GET, OPTIONS, DELETE,PUT");
        response.setHeader("Access-Control-Allow-Headers","x-auth,token_idempotent,Content-Type");


        String methodName = request.getMethod();
        System.out.println(methodName);
        if(methodName.equalsIgnoreCase("OPTIONS")){
            return false;
        }

        //将handler强转换为HandlerMethod
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        //获取方法签名
        Method method = handlerMethod.getMethod();
        //判断当前是否是公共资源  是的话直接通过
        if(!method.isAnnotationPresent(check.class)){
            return true;
        }
        //判断当前请求是否有header
        String headerAuth = request.getHeader("x-auth");
        if(StringUtils.isEmpty(headerAuth)){
            //抛出异常   终止当前方法运行并通过异常管理获取异常 返回状态信息
            throw new HeaderExceptionInfo(ResponseEnum.HEADER_IS_NULL);
        }
        //分割header 获取base64 和 签名  分割 但是 . 点在java中是特殊字符 所以需要转义  不转义分割为空
        String[] split = headerAuth.split("\\.");
        //判断分割后数组长度是否正确
        if(split.length != 2){
            //说明信息信息被修改 或丢失
            throw new HeaderExceptionInfo(ResponseEnum.HEADER_IS_UPDATE);
        }
        //获取登陆信息 和 签名
        String memberInfo64  = split[0];
        String sign64 = split[1];
        //将登陆信息转换为新签名
        String newSign = Md5Util.getSign(memberInfo64, SystemConst.SECRET);
        String newSign64 = Base64.getEncoder().encodeToString(newSign.getBytes());
        //判断信息是否正确  验签
        if(!sign64.equals(newSign64)){
            throw new HeaderExceptionInfo(ResponseEnum.HEADER_IS_ERROR);
        }
        //将base64信息转换为json字符串 并转换vo
        byte[] memberInfoByte = Base64.getDecoder().decode(memberInfo64);
        String memberInfoJsonStr = new String(memberInfoByte);
        MemberVo memberVo = JSONObject.parseObject(memberInfoJsonStr, MemberVo.class);
        //获取里面的信息并判断redis中信息  是否超时
        String redisMemberKey = KeyUtil.buildRedisMemberKey(memberVo.getMenberName(), memberVo.getUuid());
        boolean exist = RedisUtil.exist(redisMemberKey);
        if(!exist){
            throw new HeaderExceptionInfo(ResponseEnum.LOGIN_TIMEOUT);
        }
        //往request作用域放值
        request.setAttribute(SystemConst.CURRENT_MEMBERNAME,memberVo);
        //续命
        RedisUtil.expire(redisMemberKey, SystemConst.MEMBER_EXPIRE);

        return true;
    }


}
