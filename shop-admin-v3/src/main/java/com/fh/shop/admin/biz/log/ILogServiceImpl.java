package com.fh.shop.admin.biz.log;

import com.fh.shop.admin.common.DataTableResult;
import com.fh.shop.admin.mapper.log.ILogMapper;
import com.fh.shop.admin.param.log.LogSerachParam;
import com.fh.shop.admin.po.log.LogInfo;
import com.fh.shop.admin.util.DateUtil;
import com.fh.shop.admin.vo.log.LogInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value="logService")
public class ILogServiceImpl implements ILogService{
    @Autowired
    private ILogMapper logMapper;

//调用新增方法
    @Override
    public void add(LogInfo logInfo) {
        logMapper.add(logInfo);
    }
    //分页条件查询
    @Override
    public DataTableResult getCurrentList(LogSerachParam logSerachParam) {
        DataTableResult data = new DataTableResult();
        //查询总条数
        Long count = logMapper.findCount(logSerachParam);
        //查询当前页数据
        List<LogInfo> logInfoList  = logMapper.findPageData(logSerachParam);
        //po装vo
        List list = po2vo(logInfoList);
        data.setData(list);
        data.setRecordsFiltered(count);
        data.setRecordsTotal(count);
        data.setDraw(logSerachParam.getDraw());
        return data;
    }


    //po转vo
    private  List po2vo(List<LogInfo> logInfoList) {
        List<LogInfoVo> voList = new ArrayList<>();
        //po转vo
        for (LogInfo po : logInfoList) {
            LogInfoVo vo = new LogInfoVo();
            vo.setId(po.getId());
            vo.setCurrentDate(DateUtil.date2str(po.getCurrentDate(),DateUtil.full_year));
            vo.setErrorMsg(po.getErrorMsg());
            vo.setInfo(po.getInfo());
            vo.setRealName(po.getRealName());
            vo.setUserName(po.getUserName());
            vo.setStatus(po.getStatus());
            vo.setDetail(po.getDetail());
            vo.setContent(po.getContent());
            voList.add(vo);
        }
        return voList;
    }
}
