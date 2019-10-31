package com.fh.shop.api.classify.controller;

import com.fh.shop.api.classify.biz.IClassService;
import com.fh.shop.api.common.ServerResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping("class")
@RestController
@CrossOrigin("*")
public class ClassController {
    @Resource(name="classService")
    private IClassService classService;

    @RequestMapping(value="getClassList")
    public ServerResponse getClassList(){

        return classService.getClassList();
    }


}
