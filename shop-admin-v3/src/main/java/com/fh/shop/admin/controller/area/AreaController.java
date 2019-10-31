package com.fh.shop.admin.controller.area;

import com.fh.shop.admin.biz.area.IAreaService;
import com.fh.shop.admin.common.Log;
import com.fh.shop.admin.common.ServerResponse;
import com.fh.shop.admin.po.area.Area;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
@Controller
@RequestMapping(value="/area")
public class AreaController {

    @Resource(name="AreaService")
    private IAreaService areaService;

//通过父id查找子id
    @RequestMapping("getAreaByPid")
    @ResponseBody
    public ServerResponse getAreaByPid(Integer id){

        return areaService.getAreaByPid(id);
    }


//查询所有地区集合
    @RequestMapping(value="findAllArea")
    @ResponseBody
    public ServerResponse findAllArea(){


            List<Map>   areaList = areaService.findAllArea();
            return ServerResponse.success(areaList);


    }

//删除通过id
    @RequestMapping(value="/deleteAreaById")
    @ResponseBody
    @Log("通过id删除地区")
    public ServerResponse deleteAreaById(Integer[] idarr){

            areaService.deleteAreaById(idarr);
            return ServerResponse.success();

    }

//新增
@RequestMapping(value="/addArea")
@ResponseBody
@Log("新增地区")
public ServerResponse addArea(Area area){


      areaService.addArea(area);
        return ServerResponse.success(area.getId());

}


//修改
@RequestMapping(value="/updateAreaByid")
@ResponseBody
@Log("修改地区")
public ServerResponse updateAreaByid(Area area){


        areaService.updateAreaByid(area);
      return ServerResponse.success();




}


//跳转地区展示
@RequestMapping(value="/toAreaList")
public  String  toAreaList(){
    return "area/list";
}

}
