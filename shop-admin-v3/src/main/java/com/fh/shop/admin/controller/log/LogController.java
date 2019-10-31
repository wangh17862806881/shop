package com.fh.shop.admin.controller.log;

import com.fh.shop.admin.biz.log.ILogService;
import com.fh.shop.admin.common.DataTableResult;
import com.fh.shop.admin.common.ServerResponse;
import com.fh.shop.admin.param.log.LogSerachParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping(value="log")
public class LogController {
    @Resource(name="logService")
    private ILogService logService;


    //分页条件查询
    @RequestMapping(value="/getCurrentList")
    @ResponseBody
    public ServerResponse getCurrentList(LogSerachParam logSerachParam){

        DataTableResult dataTableResult = logService. getCurrentList(logSerachParam);
        return ServerResponse.success(dataTableResult);
    }



    //跳转页面
    @RequestMapping(value="/toList")
    public String toList(){
        return "log/showList";
    }


}
