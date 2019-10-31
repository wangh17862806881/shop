package com.fh.shop.api.wxPay.controller;

import com.fh.shop.api.annotation.check;
import com.fh.shop.api.common.ServerResponse;
import com.fh.shop.api.member.vo.MemberVo;
import com.fh.shop.api.wxPay.biz.IWXPayService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("WXpay")
public class WXPayController {
    @Resource(name="wxPayService")
    private IWXPayService wxPayService;

//获取二维码
    @GetMapping
    @check
    public ServerResponse postCode(MemberVo memberVo){
        return wxPayService.postCode(memberVo);
    }
//验证是否支付
    @PostMapping
    @check
    public ServerResponse getStatus(MemberVo memberVo){
        return wxPayService.getStatus(memberVo);
}


}
