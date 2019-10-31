package com.fh.shop.api.cart.controller;

import com.fh.shop.api.annotation.check;
import com.fh.shop.api.cart.biz.ICartService;
import com.fh.shop.api.common.ServerResponse;
import com.fh.shop.api.member.vo.MemberVo;
import com.fh.shop.api.util.SystemConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("cart")
public class CartController {
@Resource(name="cartService")
private ICartService cartService;
@Autowired
private HttpServletRequest request;


//新增购物车   自定义参数解析器
    @PostMapping
    @check
    public ServerResponse addCart(Long productId,Long count,MemberVo memberVo){
        Long memberId = memberVo.getId();
        return cartService.addCart(memberId,productId,count);
    }



//查询   自定义参数解析器获取的 memberVo 参数类型只要符合就会传递参数  实现接口 argumentResolver
    @GetMapping
    @check
    public ServerResponse findCart(MemberVo memberVo){
        Long memberId = memberVo.getId();
        return cartService.findCart(memberId);
    }


//删除  单个   memberVo是通过自定义参数解析器获取到的  只要类型一致就可以了
    @check
    @DeleteMapping("/{productId}")
    public ServerResponse deleteById(@PathVariable Long productId,MemberVo memberVo){
        Long memberId = memberVo.getId();
        return cartService.deleteById(productId,memberId);
    }



}
