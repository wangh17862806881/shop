package com.fh.shop.api.address.biz;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fh.shop.api.address.mapper.IAddressMapper;
import com.fh.shop.api.address.po.Address;
import com.fh.shop.api.common.ServerResponse;
import com.fh.shop.api.member.vo.MemberVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service(value="addressService")
public class IAddressServiceImpl implements IAddressService{
    @Resource
    private IAddressMapper addressMapper;

    @Override
    public ServerResponse insertAddress(Address address, MemberVo memberVo) {
        address.setMemberId(memberVo.getId());
        addressMapper.insert(address);
        return ServerResponse.success();
    }

    @Override
    public ServerResponse updateAddress(Address address, MemberVo memberVo) {
        address.setMemberId(memberVo.getId());
        addressMapper.updateById(address);
        return ServerResponse.success();
    }

    @Override
    public ServerResponse deleteAddress(Long id) {
        addressMapper.deleteById(id);
        return ServerResponse.success();
    }

    @Override
    public ServerResponse findAddress(MemberVo memberVo) {
        Long memberVoId = memberVo.getId();
        QueryWrapper<Address> addressQueryWrapper = new QueryWrapper<>();
        addressQueryWrapper.eq("memberId",memberVoId);
        List<Address> addressList = addressMapper.selectList(addressQueryWrapper);
        return ServerResponse.success(addressList);
    }

    @Override
    public ServerResponse findAddressById(MemberVo memberVo, Long id) {
        Long memberVoId = memberVo.getId();
        QueryWrapper<Address> addressQueryWrapper = new QueryWrapper<>();
        addressQueryWrapper.eq("memberId",memberVoId);
        addressQueryWrapper.eq("id",id);
        Address o = (Address) addressMapper.selectOne(addressQueryWrapper);
        return ServerResponse.success(o);
    }
}
