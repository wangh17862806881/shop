package com.fh.shop.admin.mapper.area;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fh.shop.admin.po.area.Area;

import java.util.List;
import java.util.Map;

public interface IAreaMapper extends BaseMapper<Area> {
    //查询所有地区集合
    List<Map> findAllArea();
//删除通过id
    void deleteAreaById(Integer[] idarr);
//新增
    void addArea(Area area);
//修改
    void updateAreaByid(Area area);
}
