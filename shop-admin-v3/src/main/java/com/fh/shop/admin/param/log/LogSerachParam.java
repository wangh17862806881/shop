package com.fh.shop.admin.param.log;

import com.fh.shop.admin.common.page;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class LogSerachParam extends page implements Serializable {

    //开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date beginTime;
    //结束时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date endTime;
    //用户名
    private String userName;
    //真实姓名
    private String realName;
    //状态
    private Integer status;
    //日志信息
    private String info;
    //错误信息
    private String errorMsg;

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
