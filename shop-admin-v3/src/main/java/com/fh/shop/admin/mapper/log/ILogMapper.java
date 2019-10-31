package com.fh.shop.admin.mapper.log;

import com.fh.shop.admin.param.log.LogSerachParam;
import com.fh.shop.admin.po.log.LogInfo;

import java.util.List;

public interface ILogMapper {
//调用新增方法
    void add(LogInfo logInfo);
//查询总条数
    Long findCount(LogSerachParam logSerachParam);
//查询当前页数据
    List<LogInfo> findPageData(LogSerachParam logSerachParam);
}
