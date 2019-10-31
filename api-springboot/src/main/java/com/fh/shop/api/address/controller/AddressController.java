package com.fh.shop.api.address.controller;

import com.fh.shop.api.address.biz.IAddressService;
import com.fh.shop.api.address.po.Address;
import com.fh.shop.api.annotation.check;
import com.fh.shop.api.common.ServerResponse;
import com.fh.shop.api.member.vo.MemberVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("address")
public class AddressController {
    @Resource(name="addressService")
    private IAddressService addressService;

    //新增
    @PostMapping
    @check
    public ServerResponse insertAddress(Address address, MemberVo memberVo){
        return addressService.insertAddress(address,memberVo);
    }

    //修改
    @PostMapping("updateAddress")
    @check
    public ServerResponse updateAddress(Address address, MemberVo memberVo){
        return addressService.updateAddress(address,memberVo);
    }


    //删除
    @PostMapping("deleteAddress")
    @check
    public ServerResponse deleteAddress(Long id){
        return addressService.deleteAddress(id);
    }

    //查询
    @GetMapping
    @check
    public ServerResponse findAddress(MemberVo memberVo){

        return addressService.findAddress(memberVo);
    }

    //单个查询 修改
    @GetMapping("findAddressById")
    @check
    public ServerResponse findAddressById(MemberVo memberVo,Long id){
        return addressService.findAddressById(memberVo,id);
    }

}
