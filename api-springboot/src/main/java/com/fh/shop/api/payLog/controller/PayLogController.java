package com.fh.shop.api.payLog.controller;

import com.fh.shop.api.payLog.biz.IpayLogService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("payLog")
public class PayLogController {
    @Resource(name="payLogService")
    private IpayLogService payLogService;



}
