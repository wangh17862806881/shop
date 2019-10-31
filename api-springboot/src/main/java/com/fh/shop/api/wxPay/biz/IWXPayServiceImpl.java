package com.fh.shop.api.wxPay.biz;

import com.alibaba.fastjson.JSONObject;
import com.fh.shop.api.common.ResponseEnum;
import com.fh.shop.api.common.ServerResponse;
import com.fh.shop.api.member.vo.MemberVo;
import com.fh.shop.api.order.mapper.IOrderMapper;
import com.fh.shop.api.order.po.Order;
import com.fh.shop.api.payLog.mapper.IPayLogMapper;
import com.fh.shop.api.payLog.po.PayLog;
import com.fh.shop.api.util.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

@Service(value="wxPayService")
public class IWXPayServiceImpl implements IWXPayService {
@Resource
private IPayLogMapper payLogMapper;
@Resource
private IOrderMapper orderMapper;

    @Override
    public ServerResponse postCode(MemberVo memberVo) {
        //获取会员id
        Long memberVoId = memberVo.getId();
        //获取日志表
        String payLogJsonStr = RedisUtil.get(KeyUtil.buildPayLogKey(memberVoId));
        //非空判断
        if(StringUtils.isEmpty(payLogJsonStr)){
            return ServerResponse.error(ResponseEnum.ORDER_IS_NOT_EXIST);
        }
        PayLog payLog = JSONObject.parseObject(payLogJsonStr, PayLog.class);
        String outTraeId = payLog.getOutTraeId();
        BigDecimal payMoney = payLog.getPayMoney();
        if(null == payMoney){
            return ServerResponse.error(ResponseEnum.ORDER_IS_NOT_EXIST);
        }
        BigDecimal multiply = BigdecimalUtil.multiply(payMoney.toString(), "100.00").setScale(0);
        //获取二维码
        Map<String, String> qrCode = WXPayUtil.getQRCode(outTraeId, multiply.toString());

        //验证二维码中数据是否正确 是否正常返回二维码
        if(!qrCode.get("return_code").equalsIgnoreCase("success")){
            return ServerResponse.error(9999,SystemConst.WXPAY_IS_ERROR+qrCode.get("return_msg"));
        }
        if(!qrCode.get("result_code").equalsIgnoreCase("success")){
            return ServerResponse.error(9999,SystemConst.WXPAY_IS_ERROR+qrCode.get("err_code_des"));
        }
        payLog.setQrCode(qrCode.get("code_url"));
        //返回信息
        return ServerResponse.success(payLog);
    }

    @Override
    public ServerResponse getStatus(MemberVo memberVo) {
        //获取会员id
        Long memberVoId = memberVo.getId();
        //获取日志表
        String payLogJsonStr = RedisUtil.get(KeyUtil.buildPayLogKey(memberVoId));
        //非空判断
        if(StringUtils.isEmpty(payLogJsonStr)){
            return ServerResponse.error(ResponseEnum.ORDER_IS_NOT_EXIST);
        }
        PayLog payLog = JSONObject.parseObject(payLogJsonStr, PayLog.class);
        String outTraeId = payLog.getOutTraeId();

        int count = 0;
        //验证是否付款
       while(true){
           Map<String, String> qrCode = WXPayUtil.registerQRcodePay(outTraeId);
            if (!qrCode.get("return_code").equalsIgnoreCase("success")) {
                return ServerResponse.error(9999, SystemConst.WXPAY_IS_ERROR + qrCode.get("return_msg"));
            }
            if (!qrCode.get("result_code").equalsIgnoreCase("success")) {
                return ServerResponse.error(9999, SystemConst.WXPAY_IS_ERROR + qrCode.get("err_code_des"));
            }

           if (qrCode.get("trade_state").equalsIgnoreCase("success")) {
//支付成功后 要做什么---------------------------------------------------------------
               //要更改支付日志表 数据
               PayLog payLog1 = new PayLog();
               payLog1.setOutTraeId(outTraeId);
               payLog1.setPayTime(new Date());
               payLog1.setPayStatus(2);
               payLog1.setTransactionId(qrCode.get("transaction_id"));
               payLogMapper.updateById(payLog1);
               //更改 订单表支付时间 支付状态
               Order order = new Order();
               order.setId(payLog.getOutTraeId());
               order.setStatus(20);
               order.setPayTime(new Date());
               orderMapper.updateById(order);
               //清除redis缓存服务器中 支付日志信息
                RedisUtil.del(KeyUtil.buildPayLogKey(memberVoId));
               return ServerResponse.success();           }
           try {
               Thread.sleep(3000);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           count ++;
           System.out.println(count);
           if(count >100){
               return ServerResponse.error(8888,SystemConst.POSTQRCODE_ERROR);
           }
       }


    }
}
