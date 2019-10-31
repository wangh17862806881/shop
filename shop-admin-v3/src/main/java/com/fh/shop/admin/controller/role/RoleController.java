package com.fh.shop.admin.controller.role;

import com.fh.shop.admin.biz.role.IRoleService;
import com.fh.shop.admin.common.DataTableResult;
import com.fh.shop.admin.common.Log;
import com.fh.shop.admin.common.ServerResponse;
import com.fh.shop.admin.param.role.RoleSearcherParam;
import com.fh.shop.admin.po.role.Role;
import com.fh.shop.admin.vo.role.RoleVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value="/role")
public class RoleController {
@Resource(name="roleService")
    private IRoleService roleService;

//查询所有角色数据
  @RequestMapping(value="/getRoleList")
  @ResponseBody
public ServerResponse getRoleList(RoleSearcherParam roleSearcherParam){
      DataTableResult dataTableResult =  roleService.findRoleAllData(roleSearcherParam);
    return ServerResponse.success(dataTableResult);
}

// 单个删除
    @Log("单个删除角色")
    @RequestMapping(value="/deleteRoleById")
    @ResponseBody
    public ServerResponse deleteRoleById(Long id){


            roleService.deleteRoleById(id);
           return ServerResponse.success();


    }


//修改角色
    @RequestMapping(value="/updateRoleById")
    @ResponseBody
    @Log("修改角色")
    public ServerResponse updateRoleById(Role role,@RequestParam(value="idarr[]",required=false) List<Long> idarr){


          roleService.updateRoleById(role,idarr);
        return ServerResponse.success();


    }


//回显
    @RequestMapping(value="/findRoleById")
    @ResponseBody
    public ServerResponse findRoleById(Long id){


            RoleVo roleVo = roleService.findRoleById(id);
           return ServerResponse.success(roleVo);

    }


//新增
    @RequestMapping(value="/addRole")
    @ResponseBody
    @Log("新增角色")
    public ServerResponse addRole(Role role, @RequestParam(value="idarr[]",required=false) List<Long> idarr){


            roleService.addRole(role,idarr);
          return ServerResponse.success();


    }

//跳转展示
@RequestMapping(value="/toList")
public String toRoleList(){
      return "role/showList";
}


}
