package com.fh.shop.api.address.biz;

import com.fh.shop.api.address.po.Address;
import com.fh.shop.api.common.ServerResponse;
import com.fh.shop.api.member.vo.MemberVo;

public interface IAddressService {
    ServerResponse insertAddress(Address address, MemberVo memberVo);

    ServerResponse updateAddress(Address address, MemberVo memberVo);

    ServerResponse deleteAddress(Long id);

    ServerResponse findAddress(MemberVo memberVo);

    ServerResponse findAddressById(MemberVo memberVo, Long id);
}
