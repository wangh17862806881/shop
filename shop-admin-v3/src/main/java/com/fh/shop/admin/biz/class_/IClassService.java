package com.fh.shop.admin.biz.class_;

import com.fh.shop.admin.po.class_.ClassPo;

import java.util.List;


public interface IClassService {
//获取所有信息 ztree使用
    List<ClassPo> findAllClass();

    void deleteClass(List<Long> list);

    Long addClass(ClassPo classPo);

    ClassPo findClassById(Long id);

    void updateClass(ClassPo classPo);

    List<ClassPo> findListById(Long id);
}
