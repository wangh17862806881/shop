package com.fh.shop.admin.controller.resource;

import com.alibaba.fastjson.JSONObject;
import com.fh.shop.admin.biz.resource.IResourceService;
import com.fh.shop.admin.common.Log;
import com.fh.shop.admin.common.ServerResponse;
import com.fh.shop.admin.po.user.User;
import com.fh.shop.admin.util.CookKey;
import com.fh.shop.admin.util.DistributeSession;
import com.fh.shop.admin.util.RedisUtil;
import com.fh.shop.admin.util.SystemConst;
import com.fh.shop.admin.vo.Resource.ResourceVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping(value="/resource")
public class ResourceController {
    @Resource(name="resourceService")
    private IResourceService resourceService;
    @Autowired
    private HttpServletRequest request;
    @Resource
    private HttpServletResponse response;

//查询所有资源 菜单
    @RequestMapping(value="/findAllResourceList")
    @ResponseBody
public ServerResponse findAllResourceList(){


            List<ResourceVo> resourceList = resourceService.findAllResourceList();
          return ServerResponse.success(resourceList);

}

//修改
    @RequestMapping(value="/updateResource")
    @ResponseBody
    @Log("修改资源")
    public ServerResponse updateResource(com.fh.shop.admin.po.Resource.Resource resource){


            resourceService.updateResource(resource);
         return ServerResponse.success();

}


//新增
    @RequestMapping(value="/addResource")
    @ResponseBody
    @Log("新增资源")
    public ServerResponse addResource(com.fh.shop.admin.po.Resource.Resource resource){


            resourceService.addResource(resource);
           return  ServerResponse.success(resource.getId());


    }


//删除选择 的 所有
    @RequestMapping(value="/deleteResource")
    @ResponseBody
    @Log("删除选择 的 所有资源")
    public ServerResponse deleteResource(@RequestParam("idarr[]") Long[] idarr){


           resourceService.deleteResource(idarr);
          return ServerResponse.success();


    }

// 动态显示nav导航条
    @RequestMapping(value="/findAllResourceByUserId")
    @ResponseBody
public ServerResponse findAllResourceByUserId(){
        //从session中获取用户权限
     //   List<com.fh.shop.admin.po.Resource.Resource> resourceList = (List<com.fh.shop.admin.po.Resource.Resource>) request.getSession().getAttribute(SystemConst.USER_RESOURCE_MENU );
        String sessionId = DistributeSession.getSessionId(response, request);
        String resourceListJsonStr = RedisUtil.get(CookKey.buildmenuShowId(sessionId));
        List<com.fh.shop.admin.po.Resource.Resource> resourceList = JSONObject.parseArray(resourceListJsonStr, com.fh.shop.admin.po.Resource.Resource.class);
        return  ServerResponse.success(resourceList);

    }



    //跳转展示页面
    @RequestMapping(value="/toList")
public String toResourceShowList(){
    return "resource/showList";
}





}
