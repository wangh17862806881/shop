package com.fh.shop.api.sms.biz;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fh.shop.api.common.ResponseEnum;
import com.fh.shop.api.common.ServerResponse;
import com.fh.shop.api.member.mapper.IMemberMapper;
import com.fh.shop.api.member.po.Member;
import com.fh.shop.api.util.KeyUtil;
import com.fh.shop.api.util.RedisUtil;
import com.fh.shop.api.util.SMSUtil;
import com.fh.shop.api.util.SystemConst;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service(value="smsssService")
public class ISmsServiceImpl implements ISmsService {

    @Resource
    private IMemberMapper memberMapper;
    @Override
    public ServerResponse postSms(String phone,Long flag) {
        //判断手机号是否存在
        if(null == phone){
            return ServerResponse.error(ResponseEnum.PHONE_IS_NULL);
        }
        //正则验证
        if(!phone.matches("^1\\d{10}")){
            return ServerResponse.error(ResponseEnum.PHONE_IS_ERROR);
        }
        //根据手机号查询用户
        QueryWrapper<Member> memberQueryWrapper = new QueryWrapper<>();
        memberQueryWrapper.eq("phone",phone);
        Member member = memberMapper.selectOne(memberQueryWrapper);
        //使用标志位来判断当前请求是什么 注册  还是登陆  当flag 不为空时是登录请求
        if(null != flag){
            //验证手机号是否被注册  注册请求
            if(null == member){
                //注册后才能登陆
                return ServerResponse.error(ResponseEnum.MEMBER_IS_NOT_REGISTER);
            }
        }else{
            //验证手机号是否被注册  登陆请求
            if(null != member){
                //注册就不能继续注册
                return ServerResponse.error(ResponseEnum.PHONE_IS_EXIST);
            }
        }


        //发送验证码 并获取
        String jsonStr = SMSUtil.postSMS(phone);
        //转换json字符串
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        if(!jsonObject.get("code").equals(200)){
            return ServerResponse.error(ResponseEnum.SMSCODE_IS_ERROR);
        }
        //获取验证码
        String obj = (String) jsonObject.get("obj");
        //将验证码放到redis
        RedisUtil.setEx(KeyUtil.buildSmsCodeKey(phone),SystemConst.SMS_CODE_EXPIRE,obj);
        return ServerResponse.success();
    }




}
