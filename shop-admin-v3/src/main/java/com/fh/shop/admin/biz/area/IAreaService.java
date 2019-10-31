package com.fh.shop.admin.biz.area;

import com.fh.shop.admin.common.ServerResponse;
import com.fh.shop.admin.po.area.Area;

import java.util.List;
import java.util.Map;

public interface IAreaService {
    //查询所有地区集合
    List<Map> findAllArea();
    //删除通过id
    void deleteAreaById(Integer[] id);
    //新增
    void addArea(Area area);
    //修改
    void updateAreaByid(Area area);

    ServerResponse getAreaByPid(Integer id);
}
