package com.fh.shop.api.order.biz;

import com.fh.shop.api.common.ServerResponse;
import com.fh.shop.api.member.vo.MemberVo;

public interface IOrderService {
    ServerResponse insertOrder(MemberVo memberVo);

    ServerResponse findOrder();

    ServerResponse findOrder_details();

}
