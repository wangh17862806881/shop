package com.fh.shop.admin.biz.member;

import com.fh.shop.admin.common.ServerResponse;
import com.fh.shop.admin.param.member.MemberSearchParam;

public interface IMemberService {
    ServerResponse findPage(MemberSearchParam memberSearchParam);
}
