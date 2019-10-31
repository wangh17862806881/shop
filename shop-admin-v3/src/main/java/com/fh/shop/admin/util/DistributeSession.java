package com.fh.shop.admin.util;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

public class DistributeSession {

    public static String getSessionId(HttpServletResponse response, HttpServletRequest request){
        String sessionId = CookieUtil.readCookieValue(SystemConst.COOK_NAME, request);
        if(StringUtils.isEmpty(sessionId)){
            sessionId =  UUID.randomUUID().toString();
            CookieUtil.writeCookie(response,SystemConst.COOK_NAME,sessionId,SystemConst.DOMAIN);
        }
        return sessionId;
    }


}
