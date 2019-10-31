package com.fh.shop.admin.controller;

import com.fh.shop.admin.common.ServerResponse;
import com.fh.shop.admin.util.RedisUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cache")
public class CacheController {

    @RequestMapping("cleanUPProductCache")
    public ServerResponse cleanUPProductCache(){
        RedisUtil.del("upProductList");
        return ServerResponse.success();
    }



}
