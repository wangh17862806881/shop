package com.fh.shop.admin.biz.class_;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.fh.shop.admin.mapper.Class.IClassMapper;
import com.fh.shop.admin.po.class_.ClassPo;
import com.fh.shop.admin.po.product.Product;
import com.fh.shop.admin.util.RedisUtil;
import com.fh.shop.admin.vo.product.ProductVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service(value="ClassService")
public class IClassServiceImpl implements IClassService{
    @Autowired
    private IClassMapper classMapper;



//查询所有
    @Override
    public List<ClassPo> findAllClass() {
        String jsonClassAllList = RedisUtil.get("ClassAllList");
        if(StringUtils.isNotEmpty(jsonClassAllList)){
            List<ClassPo> ClassPos = JSONObject.parseArray(jsonClassAllList,ClassPo.class);
            return ClassPos;
        }
        List<ClassPo> classPos = classMapper.selectList(null);
         jsonClassAllList = JSONObject.toJSONString(classPos);
        RedisUtil.set("ClassAllList",jsonClassAllList);
        return classPos;
    }

    @Override
    public void deleteClass(List<Long> list) {
        classMapper.deleteBatchIds(list);
        RedisUtil.del("ClassAllList");
    }

    @Override
    public Long addClass(ClassPo classPo) {
        Long insert = Long.valueOf(classMapper.insert(classPo));
        RedisUtil.del("ClassAllList");
        return insert;
    }

    @Override
    public ClassPo findClassById(Long id) {
        String jsonClassAllList = RedisUtil.get("ClassAllList");
        if(StringUtils.isNotEmpty(jsonClassAllList)){
            List<ClassPo> classPos = JSONObject.parseArray(jsonClassAllList, ClassPo.class);
            ClassPo classPo1 = getClassPo(id, classPos);
             return classPo1;
            }
        List<ClassPo> classPos = classMapper.selectList(null);
        jsonClassAllList  = JSONObject.toJSONString(classPos);
        RedisUtil.set("ClassAllList",jsonClassAllList);
        ClassPo classPo = getClassPo(id, classPos);
        return classPo;
        }

    ClassPo getClassPo(Long id, List<ClassPo> classPos) {

        ClassPo classPo1 = new ClassPo();
        for (ClassPo classPo : classPos) {
            if (id == classPo.getId()) {
                classPo1 = classPo;
                break;
            }
            }
        return classPo1;
    }


    @Override
    public void updateClass(ClassPo classPo) {
        UpdateWrapper<ClassPo> w = new UpdateWrapper();
        w.eq("id",classPo.getId());
        classMapper.update(classPo,w);
        RedisUtil.del("ClassAllList");
    }

    @Override
    public List<ClassPo> findListById(Long id) {
        String classAllList = RedisUtil.get("ClassAllList");
        if(StringUtils.isNotEmpty(classAllList)){
            List<ClassPo> classPos = JSONObject.parseArray(classAllList, ClassPo.class);
            List<ClassPo> pos = getClassPos(id, classPos);
            return pos;
        }
        List<ClassPo> classPos = classMapper.selectList(null);
        classAllList = JSONObject.toJSONString(classPos);
        RedisUtil.set("ClassAllList",classAllList);
        List<ClassPo> classPos1 = getClassPos(id, classPos);
        return classPos1;
    }

    List<ClassPo> getClassPos(Long id, List<ClassPo> classPos) {
        List<ClassPo> pos = new ArrayList<>();
        for (ClassPo po : classPos) {
            if(id == po.getPid()){
                pos.add(po);
            }
        }
        return pos;
    }


}
