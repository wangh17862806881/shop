package com.fh.shop.api.member.controller;

import com.fh.shop.api.annotation.check;
import com.fh.shop.api.common.ServerResponse;
import com.fh.shop.api.member.vo.MemberVo;
import com.fh.shop.api.member.biz.IMemberService;
import com.fh.shop.api.member.po.Member;
import com.fh.shop.api.util.KeyUtil;
import com.fh.shop.api.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/members")
public class MemberController {
    @Resource(name="memberService")
    private IMemberService memberService;
    @Autowired
    private HttpServletRequest request;

 //新增 用户  注册
@PostMapping
 public ServerResponse addMember(Member member){
    return memberService.addMember(member);
 }

 //登陆 用户名密码
    @PostMapping("/login")
    public ServerResponse login(Member member){
    return memberService.login(member);
    }

//免密登陆  验证码登陆
@GetMapping(value="phoneLogin")
public ServerResponse phoneLogin(String phone,String verifyCode){
      return   memberService.phoneLogin(phone,verifyCode);
}


//验证用户名唯一
    @GetMapping("userNameVerify")
    public ServerResponse userNameVerify(String userNameVerify){
    return memberService.userNameVerify(userNameVerify);

    }

 //验证用户名唯一
    @GetMapping("emailVerify")
    public ServerResponse emailVerify(String emailVerify){
    return memberService.emailVerify(emailVerify);

    }

    //验证手机号唯一
    @GetMapping("phoneVerify")
    public ServerResponse phoneVerify(String phoneVerify){
    return memberService.phoneVerify(phoneVerify);

    }

    //获取登陆信息
    @GetMapping("findmemberInfo")
    @check
    public ServerResponse findmemberInfo(){
        MemberVo memberInfo = (MemberVo) request.getAttribute("memberInfo");
        return ServerResponse.success(memberInfo);
    }


    //退出登陆
    @check
    @GetMapping("logout")
    public ServerResponse logout(){
        MemberVo memberInfo = (MemberVo) request.getAttribute("memberInfo");
        RedisUtil.del(KeyUtil.buildRedisMemberKey(memberInfo.getMenberName(),memberInfo.getUuid()));
        return ServerResponse.success();
    }


}
