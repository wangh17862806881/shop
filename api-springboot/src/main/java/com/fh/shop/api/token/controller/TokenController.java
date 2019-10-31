package com.fh.shop.api.token.controller;

import com.fh.shop.api.annotation.Idempotent;
import com.fh.shop.api.annotation.check;
import com.fh.shop.api.common.ServerResponse;
import com.fh.shop.api.token.biz.ITokenService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("token")
public class TokenController {
    @Resource(name="tokenService")
    private ITokenService tokenService;


    //获取token
    @check
    @GetMapping
    public ServerResponse getToken(){
        return tokenService.getToken();
    }

}
