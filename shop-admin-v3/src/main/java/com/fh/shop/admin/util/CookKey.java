package com.fh.shop.admin.util;

public class CookKey {

    public static String buildImgCodeId(String sessionId){
        return "code:"+sessionId;
    }

    public static String buildUserId(String sessionId){
        return "user:"+sessionId;
    }

    public static String buildmenuShowId(String sessionId){
        return "menuShowJsonStr:"+sessionId;
    }


    public static String buildallResourceId(String sessionId){
        return "allResource"+sessionId;
    }
    public static String builduserAllResource(String sessionId){
        return "userAllResource"+sessionId;
    }



}
