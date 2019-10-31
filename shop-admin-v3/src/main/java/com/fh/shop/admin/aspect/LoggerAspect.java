package com.fh.shop.admin.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fh.shop.admin.biz.log.ILogService;
import com.fh.shop.admin.common.Log;
import com.fh.shop.admin.common.WebContext;
import com.fh.shop.admin.po.log.LogInfo;
import com.fh.shop.admin.po.user.User;
import com.fh.shop.admin.util.*;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

//普通java类 需要配置才能生效
public class LoggerAspect {
private Logger LOGGER = LoggerFactory.getLogger(LoggerAspect.class);
    //注入logService

    @Resource(name="logService")
    private ILogService logService;

    //横切逻辑
    public Object doLog(ProceedingJoinPoint pjp){

        Object proceed = null;
        //获取类名
        String className = pjp.getTarget().getClass().getName();
        //获取方法名
        String methodNme = pjp.getSignature().getName();
        //获取当前线程的request
        HttpServletRequest request = WebContext.getRequest();
        String requestURI = request.getRequestURI();
        System.out.println(requestURI);
        //从session中取当前用户信息
       // User user = (User) request.getSession().getAttribute(SystemConst.CURRENT_USER);
        String sessionId = CookieUtil.readCookieValue(SystemConst.COOK_NAME, request);
        String userJsonStr = RedisUtil.get(CookKey.buildUserId(sessionId));
        User user = JSONObject.parseObject(userJsonStr,User.class);
        //获取当前用户名
        String userName = user.getUserName();
        //获取当前用户真实姓名
        String realName = user.getRealName();
       //获取参数
        Map<String, String[]> parameterMap = request.getParameterMap();
        //遍历循环成字符串保存到数据库
        Iterator<Map.Entry<String, String[]>> iterator = parameterMap.entrySet().iterator();
        StringBuffer strb = new StringBuffer();
        while(iterator.hasNext()){
            Map.Entry<String, String[]> next = iterator.next();
            strb.append("/").append(next.getKey()).append("=").append(next.getValue());
        }
        //获取自定义注解  先获取发方法签名
         MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        String logStr = "";
        if(method.isAnnotationPresent(Log.class)){
            Log annotation = method.getAnnotation(Log.class);
             logStr = annotation.value();
        }

        try {
            proceed = pjp.proceed();
            //loggger日志打印
            LOGGER.info("执行了"+className+"类的"+methodNme+"方法成功");
            LogInfo logInfo = new LogInfo();
            logInfo.setStatus(SystemConst.LOGGER_SUCCESS_STATUS);
            logInfo.setRealName(realName);
            logInfo.setInfo("执行了"+className+"类的"+methodNme+"方法成功");
            logInfo.setUserName(userName);
            logInfo.setErrorMsg("");
            logInfo.setDetail(strb.toString());
            logInfo.setCurrentDate(new Date());
            logInfo.setContent(logStr);
            //调用新增方法
            logService.add(logInfo);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            //错误日志打印
            LOGGER.error("执行了"+className+"类的"+methodNme+"方法失败");
            LogInfo logInfo = new LogInfo();
            logInfo.setStatus(SystemConst.LOGGER_SUCCESS_STATUS);
            logInfo.setRealName(realName);
            logInfo.setInfo("执行了"+className+"类的"+methodNme+"方法失败");
            logInfo.setUserName(userName);
            logInfo.setErrorMsg(throwable.getMessage());
            logInfo.setDetail(strb.toString());
            logInfo.setCurrentDate(new Date());
            logInfo.setContent(logStr);
            //调用新增方法
            logService.add(logInfo);
            //必须将异常再次抛出  因为一旦捕获了异常controller的全局异常就捕获不到异常了再次抛出
            throw  new RuntimeException(throwable);
        }

        return proceed;
    }

}
