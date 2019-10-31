package com.fh.shop.admin.biz.area;

import com.alibaba.fastjson.JSONObject;
import com.fh.shop.admin.common.ServerResponse;
import com.fh.shop.admin.mapper.area.IAreaMapper;
import com.fh.shop.admin.po.area.Area;
import com.fh.shop.admin.util.RedisUtil;
import com.google.gson.JsonObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service(value="AreaService")
public class IAreaServiceImpl implements IAreaService{
    @Autowired
    private IAreaMapper areaMapper;
    //查询所有地区集合
    @Override
    public List<Map> findAllArea() {
        return areaMapper.findAllArea();
    }



    //删除通过id
    @Override
    public void deleteAreaById(Integer[] idarr) {
        RedisUtil.del("allAreaList");
        areaMapper.deleteAreaById(idarr);
    }
//新增
    @Override
    public void addArea(Area area) {
        RedisUtil.del("allAreaList");
         areaMapper.addArea(area);

    }
//修改
    @Override
    public void updateAreaByid(Area area) {
        RedisUtil.del("allAreaList");
        areaMapper.updateAreaByid(area);
    }

    @Override
    public ServerResponse getAreaByPid(Integer id) {
        String allAreaList = RedisUtil.get("allAreaList");
        if(StringUtils.isNotEmpty(allAreaList)){
            List<Area> areas = JSONObject.parseArray(allAreaList, Area.class);
            List<Area> areas11 = getAreaByPid(id, areas);
            return ServerResponse.success(areas11);
        }
        List<Area> areas = areaMapper.selectList(null);
        List<Area> areaByPid = getAreaByPid(id, areas);
        allAreaList = JSONObject.toJSONString(areas);
        RedisUtil.set("allAreaList",allAreaList);
        return ServerResponse.success(areaByPid);
    }

    List<Area> getAreaByPid(Integer id, List<Area> areas) {
        List<Area> areas1 = new ArrayList<>();
        for (Area area : areas) {
            if(id.equals(area.getPid())){
                areas1.add(area);
            }
        }
        return areas1;
    }
}
