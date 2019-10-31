package com.fh.shop.api.order.controller;

import com.fh.shop.api.annotation.Idempotent;
import com.fh.shop.api.annotation.check;
import com.fh.shop.api.common.ServerResponse;
import com.fh.shop.api.member.vo.MemberVo;
import com.fh.shop.api.order.biz.IOrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("order")
public class OrderController {
    @Resource(name="orderService")
   private IOrderService orderService;

    //新增订单
    @PostMapping
    @check
    @Idempotent
    public ServerResponse insertOrder(MemberVo memberVo){
       return  orderService.insertOrder(memberVo);

    }
    @GetMapping("findOrder")
    @check
    public ServerResponse findOrder(){
        return orderService.findOrder();
    }

    @GetMapping("findOrder_details")
    @check
    public ServerResponse findOrder_details(){
        return orderService.findOrder_details();
    }

}
