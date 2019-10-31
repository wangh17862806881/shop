package com.fh.shop.api.classify.biz;

import com.alibaba.fastjson.JSONObject;
import com.fh.shop.api.classify.mapper.IClassMapper;
import com.fh.shop.api.classify.po.Class_po;
import com.fh.shop.api.common.ServerResponse;
import com.fh.shop.api.util.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service(value="classService")
public class IClassServiceImpl implements IClassService{
    @Resource
    private IClassMapper classMapper;


    @Override
    public ServerResponse getClassList() {
        String classAllList = RedisUtil.get("ClassAllList");
        if(StringUtils.isNotEmpty(classAllList)){
            List<Class_po> class_pos = JSONObject.parseArray(classAllList, Class_po.class);
            return ServerResponse.success(class_pos);
        }
        List<Class_po> class_pos = classMapper.selectList(null);
         classAllList = JSONObject.toJSONString(class_pos);
         RedisUtil.set("ClassAllList",classAllList);
        return ServerResponse.success(class_pos);
    }
}
