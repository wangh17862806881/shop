package com.fh.shop.api.sms.controller;

import com.fh.shop.api.common.ServerResponse;
import com.fh.shop.api.sms.biz.ISmsService;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@CrossOrigin("*")
@RequestMapping("/sms")
public class SmsController {
    @Resource(name="smsssService")
    private ISmsService smsService;

    @GetMapping
    public ServerResponse postSms(String phone,@RequestParam(required = false) Long flag){

        return smsService.postSms(phone,flag);
    }


}
