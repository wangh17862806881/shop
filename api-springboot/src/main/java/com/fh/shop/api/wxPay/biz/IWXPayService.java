package com.fh.shop.api.wxPay.biz;

import com.fh.shop.api.common.ServerResponse;
import com.fh.shop.api.member.vo.MemberVo;

public interface IWXPayService {
    ServerResponse postCode(MemberVo memberVo);

    ServerResponse getStatus(MemberVo memberVo);

}
