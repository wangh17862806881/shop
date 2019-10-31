package com.fh.shop.admin.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.fh.shop.admin.po.Resource.Resource;
import com.fh.shop.admin.util.CookKey;
import com.fh.shop.admin.util.DistributeSession;
import com.fh.shop.admin.util.RedisUtil;
import com.fh.shop.admin.util.SystemConst;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResourceInterCeptor extends HandlerInterceptorAdapter {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
     //获取当前请求路径  相对 uri
        String uri = request.getRequestURI();
        //获取所有权限
      //  List<Resource> resourceAllList = (List<Resource>) request.getSession().getAttribute(SystemConst.RESOURCE_ALL_LIST);
        String sessionId = DistributeSession.getSessionId(response, request);
        String resourceAllListJsonStr = RedisUtil.get(CookKey.buildallResourceId(sessionId));
        List<Resource> resourceAllList = JSONObject.parseArray(resourceAllListJsonStr, Resource.class);
        boolean hasrRsource = false;
        //判断当前请求是否是请求公共资源   判断请求在不再所有url中
        for(int i= resourceAllList.size()-1 ; i >=0;i-- ){
            if(StringUtils.isNotEmpty(resourceAllList.get(i).getUrl()) && uri.contains(resourceAllList.get(i).getUrl())){
                hasrRsource = true;
                break;
            }
        }
        //如果当前请求是公共资源  直接通过
        if(!hasrRsource){
            return true;
        }
        boolean userResource = false;
        //获取当前请求方式  判断是否是ajax请求
        String header = request.getHeader("X-Requested-With");
        //判断当前请求是否含有菜单权限  或 按钮权限  含有直接放过
        //List<String> menuResourceList = (List<String>) request.getSession().getAttribute(SystemConst.USER_RESOURCE_ALL_URL_LIST );
        String menuResourceListJsonStr = RedisUtil.get(CookKey.builduserAllResource(sessionId));
        List<String> menuResourceList = JSONObject.parseArray(menuResourceListJsonStr, String.class);
        for(int i= menuResourceList.size()-1 ; i>= 0 ; i--){
            if(StringUtils.isNotBlank(menuResourceList.get(i)) && uri.contains(menuResourceList.get(i)) ){
                userResource = true;
                break;
            }
        }
        if(!userResource){
            if(StringUtils.isNotEmpty(header) && header.equals("XMLHttpRequest") ){
                //ajax请求 错误 需要返回json字符串
                Map resultMap = new HashMap();
                resultMap.put("code",-10000);
                resultMap.put("msg","无权限操作");
                String jsonString = JSONObject.toJSONString(resultMap);
                outJson(jsonString,response);
                userResource =  false;
            }else{
                response.sendRedirect("/error.jsp");
                userResource = false;
            }
        }
        return userResource;
    }


 //ajax响应返回json字符串
    private void outJson(String jsonStr,HttpServletResponse response){
        //获取写的流
        PrintWriter writer =null;
        //设置响应格式  防止乱码
        response.setContentType("application/json;charset=UTF-8");
        try {
             writer = response.getWriter();
            writer.write(jsonStr);
            writer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            writer.close();
            writer = null;
        }


    }



}
