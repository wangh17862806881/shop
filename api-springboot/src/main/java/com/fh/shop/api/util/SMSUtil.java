package com.fh.shop.api.util;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SMSUtil {

    //发送验证码的请求路径URL
    private static final String URL = "https://api.netease.im/sms/sendcode.action";

    //网易云信分配的账号，请替换你在管理后台应用下申请的Appkey
    private static final String APPKEY="b6da9d1b1c7d4770a2f30f1382c9fd79";

    //网易云信分配的密钥，请替换你在管理后台应用下申请的appSecret
    private static final String APP_SECRET="8fe3f820d125";

    //验证码长度，范围4～10，默认为4
    private static final String CODELEN="6";


    public static String postSMS(String phone){
        //设置  请求头信息
        Map<String,String> headers = new HashMap<>();
        headers.put("AppKey",APPKEY);
        //创建随机数
        String nonce = UUID.randomUUID().toString();
        headers.put("Nonce",nonce);
        //获取当前时间的秒数
        String timeMillis = System.currentTimeMillis()+"";
        headers.put("CurTime",timeMillis);
        //散列算法
        headers.put("CheckSum",CheckSumBuilder.getCheckSum(APP_SECRET,nonce,timeMillis));
        headers.put("Content-Type","application/x-www-form-urlencoded;charset=utf-8");
        //设置请求参数
        Map<String,String> params = new HashMap<>();
        params.put("mobile",phone);
        params.put("codeLen",CODELEN);
        //调用posthttpclient工具类
        String result = HttpclientUtil.HttpPost(URL,headers,params);
        return result;
    }





}
