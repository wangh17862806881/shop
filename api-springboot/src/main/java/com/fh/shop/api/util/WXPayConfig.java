package com.fh.shop.api.util;


import java.io.InputStream;

//微信支付 配置类
public class WXPayConfig implements com.github.wxpay.sdk.WXPayConfig {

    public String getAppID() {
        return "wxa1e44e130a9a8eee";
    }

    public String getMchID() {
        return "1507758211";
    }

    public String getKey() {
        return "feihujiaoyu12345678yuxiaoyang123";
    }

    @Override
    public InputStream getCertStream() {
        return null;
    }


    public int getHttpConnectTimeoutMs() {
        return 8000;
    }

    public int getHttpReadTimeoutMs() {
        return 10000;
    }
}
