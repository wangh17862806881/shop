package com.fh.shop.api.area.controller;

import com.fh.shop.api.area.biz.IAreaService;
import com.fh.shop.api.common.ServerResponse;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@CrossOrigin("*")
@RequestMapping("/areas")
public class AreaController {
    @Resource(name="areaService")
    private IAreaService areaService;


@RequestMapping(method=RequestMethod.GET)
public ServerResponse findAllList(){
return areaService.findAllList();
}


@RequestMapping(value="/{id}",method=RequestMethod.GET)
public ServerResponse findAreaByid(@PathVariable Integer id){
    return areaService.findAreaByid(id);
}

@GetMapping(value="/children/{id}")
    public ServerResponse findClildren(@PathVariable Long id){
    return areaService.findClildren(id);
}


}
