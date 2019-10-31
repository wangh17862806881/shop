package com.fh.shop.admin.mapper.member;

import com.fh.shop.admin.param.member.MemberSearchParam;
import com.fh.shop.admin.po.member.Member;

import java.util.List;

public interface IMemberMapper {


    Long findTotalCount(MemberSearchParam memberSearchParam);

    List<Member> findPageList(MemberSearchParam memberSearchParam);
}
