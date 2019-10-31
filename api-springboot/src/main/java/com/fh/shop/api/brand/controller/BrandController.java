package com.fh.shop.api.brand.controller;

import com.fh.shop.api.brand.biz.IBrandServerce;
import com.fh.shop.api.common.ServerResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@CrossOrigin("*")
@RequestMapping(value="/brand")
public class BrandController {


    @Resource(name="brandService")
    private IBrandServerce brandService;

    @RequestMapping("findBrandList")
    public ServerResponse findBrandList(){
        return brandService.findBrandList();
    }





}
