package com.fh.shop.api.payLog.biz;

import com.fh.shop.api.payLog.mapper.IPayLogMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service(value="payLogService")
public class IpayLogServiceImpl implements IpayLogService {
@Resource
private IPayLogMapper payLogMapper;


}
