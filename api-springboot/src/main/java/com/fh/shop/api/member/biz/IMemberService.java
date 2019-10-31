package com.fh.shop.api.member.biz;

import com.fh.shop.api.common.ServerResponse;
import com.fh.shop.api.member.po.Member;

public interface IMemberService {
    ServerResponse addMember(Member member);

    ServerResponse userNameVerify(String userNameVerify);

    ServerResponse emailVerify(String emailVerify);

    ServerResponse phoneVerify(String phoneVerify);

    ServerResponse login(Member member);

    ServerResponse phoneLogin(String phone, String verifyCode);
}
