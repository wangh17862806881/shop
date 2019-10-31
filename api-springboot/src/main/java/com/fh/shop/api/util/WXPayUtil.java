package com.fh.shop.api.util;

import com.github.wxpay.sdk.WXPay;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class WXPayUtil {


//获取二维码  返回所有信息 调用时最好查看官方文档返回值
    public static Map<String,String> getQRCode(String out_trade_no,String money){
        WXPayConfig config = null;
        Map<String, String> resp = null;
        try {
            config = new WXPayConfig();
        } catch (Exception e) {
            e.printStackTrace();
        }
        WXPay wxpay = new WXPay(config);
        Date date = DateUtils.addMinutes(new Date(), 5);
        String expireTime = DateUtil.date2str(date, DateUtil.yyyyMMddHHmmss);
        Map<String, String> data = new HashMap<>();
        data.put("body", "飞狐教育-订单支付"); //支付时显示信息
        data.put("out_trade_no", out_trade_no);//商品交易单号  客户端生成  唯一
        data.put("fee_type", "CNY");//支付类型 人民币
        data.put("total_fee", money); //交易金额  单位：分
        data.put("spbill_create_ip", "123.12.12.123");//用户终端ip   不重要 可随便写
        data.put("notify_url", "http://www.example.com/wxpay/notify");//回调  不重要 可随便写
        data.put("trade_type", "NATIVE");  // 此处指定为扫码支付
        data.put("product_id", "11111");//商品id  最好不变 变动后 不能生成重复订单
        data.put("time_expire", expireTime);//交易结束时间  格式 yyyyMMddHHmmss  到时间二维码失效 目前设置五分钟

        try {
            resp = wxpay.unifiedOrder(data);
            System.out.println(resp);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resp;

    }


//验证订单是否支付 返回所有信息 调用时最好查看官方文档返回值
    public static Map<String, String> registerQRcodePay(String out_trade_no){
        Map<String, String> resp = null;
        try {
        WXPayConfig config = new WXPayConfig();
        WXPay wxpay = new WXPay(config);
        Map<String, String> data = new HashMap<>();
        data.put("out_trade_no", out_trade_no);
           resp = wxpay.orderQuery(data);
            System.out.println(resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }


    //关闭订单 返回所有信息 调用时最好查看官方文档返回值
    public static Map<String, String> deleteQRcodePay(String out_trade_no){
        Map<String, String> resp = null;
        try {
            WXPayConfig config = new WXPayConfig();
            WXPay wxpay = new WXPay(config);
            Map<String, String> data = new HashMap<>();
            data.put("out_trade_no", out_trade_no);
            resp = wxpay.closeOrder(data);
            System.out.println(resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }

}
