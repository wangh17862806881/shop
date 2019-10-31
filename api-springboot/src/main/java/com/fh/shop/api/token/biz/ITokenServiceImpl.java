package com.fh.shop.api.token.biz;

import com.fh.shop.api.common.ServerResponse;
import com.fh.shop.api.util.RedisUtil;
import com.fh.shop.api.util.SystemConst;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service(value="tokenService")
public class ITokenServiceImpl implements ITokenService {


    @Override
    public ServerResponse getToken() {
        //设置uuid
        String token = UUID.randomUUID().toString();
        //将redis再放到cookie中
        RedisUtil.setEx(token,SystemConst.VERIFY_EXPIRE,"1");
        //正常响应
        return ServerResponse.success(token);
    }
}
