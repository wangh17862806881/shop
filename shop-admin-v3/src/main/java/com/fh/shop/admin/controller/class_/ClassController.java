package com.fh.shop.admin.controller.class_;

import com.fh.shop.admin.biz.class_.IClassService;
import com.fh.shop.admin.common.ServerResponse;
import com.fh.shop.admin.po.class_.ClassPo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value="class")
public class ClassController {
@Resource(name="ClassService")
private IClassService classService;

//获取所有信息 ztree使用
    @RequestMapping(value="findAllClass")
    @ResponseBody
    public ServerResponse findAllClass(){
       List<ClassPo> classPoList =  classService.findAllClass();
        return ServerResponse.success(classPoList);
    }

//查询 动态下拉框使用
    @RequestMapping(value="findListById")
    @ResponseBody
    public ServerResponse findListById(Long id){
        List<ClassPo> classPos = classService.findListById(id);
        return ServerResponse.success(classPos);
    }

//删除 批量删除
    @RequestMapping(value="deleteClass")
    @ResponseBody
    public ServerResponse deleteClass(@RequestParam(value="list[]",required=false) List<Long> list){
        classService.deleteClass(list);
        return ServerResponse.success();
    }

//新增
    @RequestMapping(value="addClass")
    @ResponseBody
    public ServerResponse addClass(ClassPo classPo){
      Long id  = classService.addClass(classPo);
        return ServerResponse.success(id);
    }

//回显
@RequestMapping(value="findClassById")
@ResponseBody
    public ServerResponse findClassById(Long id){
        ClassPo classPo  = classService.findClassById(id);
        return ServerResponse.success(classPo);
}

//修改
@RequestMapping(value="updateClass")
@ResponseBody
public ServerResponse updateClass(ClassPo classPo){
    classService.updateClass(classPo);
        return ServerResponse.success();
}

@RequestMapping(value="toShowList")
public String toShowList(){
        return "class/showList";
    }

}
