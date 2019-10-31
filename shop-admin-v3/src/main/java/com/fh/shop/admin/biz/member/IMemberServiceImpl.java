package com.fh.shop.admin.biz.member;

import com.fh.shop.admin.common.DataTableResult;
import com.fh.shop.admin.common.ServerResponse;
import com.fh.shop.admin.mapper.member.IMemberMapper;
import com.fh.shop.admin.param.member.MemberSearchParam;
import com.fh.shop.admin.po.member.Member;
import com.fh.shop.admin.po.user.User;
import com.fh.shop.admin.util.DateUtil;
import com.fh.shop.admin.vo.member.MemberVo;
import com.fh.shop.admin.vo.user.UserVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("memberService")
public class IMemberServiceImpl implements IMemberService {
    @Resource
    private IMemberMapper memberMapper;


    @Override
    public ServerResponse findPage(MemberSearchParam memberSearchParam) {
        //查询总条数
        Long totalCount = memberMapper.findTotalCount(memberSearchParam);
        //查询当前页数据
        List<Member> memberList = memberMapper.findPageList(memberSearchParam);
        //po集合转vo集合
        List<MemberVo> memberVos1 = PoList2ViList(memberList);
        DataTableResult dataTableResult = new DataTableResult(totalCount, totalCount, memberSearchParam.getDraw(), memberVos1);

        return ServerResponse.success(dataTableResult);
    }

    private List<MemberVo> PoList2ViList(List<Member> memberList) {
        List<MemberVo> volist = new ArrayList<>();

        for (Member po : memberList) {
            MemberVo vo = new MemberVo();
            vo.setAreaName(po.getAreaName());
            vo.setBirthday(DateUtil.date2str(po.getBirthday(),DateUtil.Y_M_D));
            vo.setEmail(po.getEmail());
            vo.setId(po.getId());
            vo.setPhone(po.getPhone());
            vo.setUserName(po.getUserName());
            vo.setRealName(po.getRealName());
            volist.add(vo);
        }

        return volist;
    }
}
