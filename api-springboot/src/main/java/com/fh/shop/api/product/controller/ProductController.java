package com.fh.shop.api.product.controller;

import com.fh.shop.api.annotation.check;
import com.fh.shop.api.common.ServerResponse;
import com.fh.shop.api.product.biz.IProductService;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value="product")
public class ProductController {
    @Resource(name="productService")
   private IProductService productService;

    @RequestMapping("findList")
    public ServerResponse fidall(){
        ServerResponse list = productService.findList();
        return list;
    }

}
