package com.fh.shop.admin.biz.log;

import com.fh.shop.admin.common.DataTableResult;
import com.fh.shop.admin.param.log.LogSerachParam;
import com.fh.shop.admin.po.log.LogInfo;

public interface ILogService {
//调用新增方法
    void add(LogInfo logInfo);
//分页条件查询
    DataTableResult getCurrentList(LogSerachParam logSerachParam);
}
