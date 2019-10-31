package com.fh.shop.api.area.biz;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fh.shop.api.area.mapper.IAreaMapper;
import com.fh.shop.api.area.po.Area;
import com.fh.shop.api.common.ServerResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service(value="areaService")
public class IAreaServiceImpl implements IAreaService{
    @Resource
    private IAreaMapper areaMapper;


    @Override
    public ServerResponse findAllList() {
        List<Area> areas = areaMapper.selectList(null);
        return ServerResponse.success(areas);
    }

    @Override
    public ServerResponse findAreaByid(Integer id) {
        Area area = areaMapper.selectById(id);
        return ServerResponse.success(area);
    }

    @Override
    public ServerResponse findClildren(Long id) {
        QueryWrapper<Area> areaQueryWrapper = new QueryWrapper<>();
        areaQueryWrapper.eq("pid",id.intValue());
        List<Area> areas = areaMapper.selectList(areaQueryWrapper);
        return ServerResponse.success(areas);
    }
}
