package com.fh.shop.admin.param.member;

import com.fh.shop.admin.common.page;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class MemberSearchParam extends page implements Serializable {

    //用户名
    private String userName;
    //真实姓名
    private String realName;
    //开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginTime;
    //结束时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;
    private Integer areaId1;

    private Integer areaId2;

    private Integer areaId3;

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

    public Integer getAreaId1() {
        return areaId1;
    }

    public void setAreaId1(Integer areaId1) {
        this.areaId1 = areaId1;
    }

    public Integer getAreaId2() {
        return areaId2;
    }

    public void setAreaId2(Integer areaId2) {
        this.areaId2 = areaId2;
    }

    public Integer getAreaId3() {
        return areaId3;
    }

    public void setAreaId3(Integer areaId3) {
        this.areaId3 = areaId3;
    }
}
