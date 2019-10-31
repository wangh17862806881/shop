package com.fh.shop.api.util;

public class KeyUtil {

    public static String buildSmsCodeKey(String phone){
        return "smsCode:"+phone;
    }


    public static String buildRedisMemberKey(String memberName,String uuid){

        return "memberName:"+memberName+":"+uuid;

    }


    public static String buildCartNameField(Long id){
        return "memberCart:"+id;
    }



    public static String buildPayLogKey(Long id){
        return "PayLog:Member:"+id;
    }

}
