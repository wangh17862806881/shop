package com.fh.shop.api.member.biz;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fh.shop.api.common.ResponseEnum;
import com.fh.shop.api.common.ServerResponse;
import com.fh.shop.api.member.vo.MemberVo;
import com.fh.shop.api.member.mapper.IMemberMapper;
import com.fh.shop.api.member.po.Member;
import com.fh.shop.api.util.KeyUtil;
import com.fh.shop.api.util.Md5Util;
import com.fh.shop.api.util.RedisUtil;
import com.fh.shop.api.util.SystemConst;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Base64;
import java.util.UUID;

@Service("memberService")
public class IMemberServiceImpl implements IMemberService{
    @Resource
    private IMemberMapper memberMapper;


    @Override
    public ServerResponse addMember(Member member) {
        String phone = member.getPhone();
        String userName = member.getUserName();
        String email = member.getEmail();
        String code = member.getCode();
        //非空验证 -------------------------------------------------------------------------------------------
        if(null == phone){
            return ServerResponse.error(ResponseEnum.PHONE_IS_NULL);
        }
        if(null == userName){
            return ServerResponse.error(ResponseEnum.USERNAME_IS_NULL);
        }
        if(null == email){
            return ServerResponse.error(ResponseEnum.EMAIL_IS_NULL);
        }
        if(null == code){
            return ServerResponse.error(ResponseEnum.SMS_IS_NULL);
        }
        //验证验证码是否正确
        String smsCode = RedisUtil.get(KeyUtil.buildSmsCodeKey(phone));
        if(null == smsCode){
            return ServerResponse.error(ResponseEnum.SMS_IS_EXPIRE);
        }
        if(!code.equals(smsCode)){
            return ServerResponse.error(ResponseEnum.VERIFY_IS_ERROR);
        }
        //唯一验证-------------------------------------------------------------------------------------------
        QueryWrapper<Member> phoneselect = new QueryWrapper<>();
        phoneselect.eq("phone",phone);
        Member member1 = memberMapper.selectOne(phoneselect);
        if(null != member1){
            return ServerResponse.error(ResponseEnum.PHONE_IS_EXIST);
        }
        QueryWrapper<Member> emailselect = new QueryWrapper<>();
        emailselect.eq("email",email);
        Member emailselect1 = memberMapper.selectOne(emailselect);
        if(null != emailselect1){
            return ServerResponse.error(ResponseEnum.EMAIL_IS_EXIST);
        }
        QueryWrapper<Member> userNameselect= new QueryWrapper<>();
        userNameselect.eq("email",email);
        Member userNameselect1 = memberMapper.selectOne(userNameselect);
        if(null != userNameselect1){
            return ServerResponse.error(ResponseEnum.USERNAME_IS_EXIST);
        }
        String salt = UUID.randomUUID().toString();
        member.setSalt(salt);
        String password = Md5Util.encodePassword(member.getPassword(), salt);
        member.setPassword(password);
        memberMapper.insert(member);
        return ServerResponse.success();
    }
    //用户名验证
    @Override
    public ServerResponse userNameVerify(String userNameVerify) {

        QueryWrapper<Member> memberQueryWrapper = new QueryWrapper<>();
        memberQueryWrapper.eq("userName",userNameVerify);
        Member member = memberMapper.selectOne(memberQueryWrapper);
        if(null != member){
            return ServerResponse.error();
        }
        return ServerResponse.success();
    }
    //邮箱验证
    @Override
    public ServerResponse emailVerify(String emailVerify) {
        QueryWrapper<Member> emailVerifywrapper = new QueryWrapper<>();
        emailVerifywrapper.eq("email",emailVerify);
        Member member = memberMapper.selectOne(emailVerifywrapper);
        if(null != member){
            return ServerResponse.error();
        }
        return ServerResponse.success();
    }
    //手机号验证
    @Override
    public ServerResponse phoneVerify(String phoneVerify) {
        QueryWrapper<Member> phoneVerifywrapper = new QueryWrapper<>();
        phoneVerifywrapper.eq("phone",phoneVerify);
        Member member = memberMapper.selectOne(phoneVerifywrapper);
        if(null != member){
            return ServerResponse.error();
        }
        return ServerResponse.success();
    }
    //登陆验证  处理
    @Override
    public ServerResponse login(Member member) {
        String memberName = member.getUserName();
        String password = member.getPassword();
        //非空验证
        if(StringUtils.isEmpty(memberName)){
            return ServerResponse.error(ResponseEnum.USERNAME_IS_NULL);
        }
        if(StringUtils.isEmpty(password)){
            return ServerResponse.error(ResponseEnum.PASSWORD_IS_EMPTY);
        }
        //验证用户是否存在
        QueryWrapper<Member> memberQueryWrapper = new QueryWrapper<>();
        memberQueryWrapper.eq("userName",memberName);
        Member member1 = memberMapper.selectOne(memberQueryWrapper);
        if(null == member1){
            return ServerResponse.error(ResponseEnum.USER_IS_EMPTY);
        }
        //验证密码  是否一致
        if(!member1.getPassword().equals(Md5Util.encodePassword(password,member1.getSalt()))){
            return ServerResponse.error(ResponseEnum.PASSWORD_IS_ERROR);
        }

        //转VO
        MemberVo memberVo = new MemberVo();
        memberVo.setMenberName(memberName);
        memberVo.setId(member1.getId());
        memberVo.setRealName(member1.getRealName());
        String uuid = UUID.randomUUID().toString();
        memberVo.setUuid(uuid);
        //转json字符串
        String voJsonStr = JSONObject.toJSONString(memberVo);
        //base64编码
        String vo64Str = Base64.getEncoder().encodeToString(voJsonStr.getBytes());
        //生成签名
        String sign = Md5Util.getSign(vo64Str, SystemConst.SECRET);
        //将信息放到redis中  主要就是为了设置过期时间
        RedisUtil.setEx(KeyUtil.buildRedisMemberKey(memberName,uuid),SystemConst.MEMBER_EXPIRE,"1");
        //将生成的MD5 码  转换 base64 码
        String signBase64 = Base64.getEncoder().encodeToString(sign.getBytes());
        //将 base64 码  和  签名返回
        return ServerResponse.success(vo64Str+"."+signBase64);
    }
    //验证码登陆
    @Override
    public ServerResponse phoneLogin(String phone, String verifyCode) {
        //非空验证
        if(StringUtils.isEmpty(phone)){
            return ServerResponse.error(ResponseEnum.PHONE_IS_NULL);
        }
        if(StringUtils.isEmpty(verifyCode)){
            return ServerResponse.error(ResponseEnum.SMS_IS_NULL);
        }
        //验证手机号格式
        boolean matches = phone.matches("^1\\d{10}");
        if(!matches){
            return ServerResponse.error(ResponseEnum.PHONE_IS_ERROR);
        }
        //验证验证码是否正确
        String codestr = RedisUtil.get(KeyUtil.buildSmsCodeKey(phone));
        if(StringUtils.isEmpty(codestr)){
            return ServerResponse.error(ResponseEnum.SMS_IS_EXPIRE);
        }
        if(!codestr.equals(verifyCode)){
            return ServerResponse.error(ResponseEnum.VERIFY_IS_ERROR);
        }
        //查看用户信息
        //根据手机号查询用户
        QueryWrapper<Member> memberQueryWrapper = new QueryWrapper<>();
        memberQueryWrapper.eq("phone",phone);
        Member member = memberMapper.selectOne(memberQueryWrapper);
        //判断用户是否存在
        if(null == member){
            return ServerResponse.error(ResponseEnum.USER_IS_EMPTY);
        }
        //删除验证码信息
        RedisUtil.del(KeyUtil.buildSmsCodeKey(phone));
        //将信息存入响应返回前台  使用token 登陆
//转VO
        String memberName = member.getUserName();
        MemberVo memberVo = new MemberVo();
        memberVo.setMenberName(memberName);
        memberVo.setId(member.getId());
        memberVo.setRealName(member.getRealName());
        String uuid = UUID.randomUUID().toString();
        memberVo.setUuid(uuid);
        //转json字符串
        String voJsonStr = JSONObject.toJSONString(memberVo);
        //base64编码
        String vo64Str = Base64.getEncoder().encodeToString(voJsonStr.getBytes());
        //生成签名
        String sign = Md5Util.getSign(vo64Str, SystemConst.SECRET);
        //将信息放到redis中  主要就是为了设置过期时间
        RedisUtil.setEx(KeyUtil.buildRedisMemberKey(memberName,uuid),SystemConst.MEMBER_EXPIRE,"1");
        //将生成的MD5 码  转换 base64 码
        String signBase64 = Base64.getEncoder().encodeToString(sign.getBytes());
        return ServerResponse.success(vo64Str+"."+signBase64);
    }

}
