package com.fh.shop.admin.param.user;

import com.fh.shop.admin.common.page;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserSearcherParam extends page implements Serializable {
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
    //开始年龄范围
    private Integer beginAge;
    //结束年龄范围
    private Integer endAge;
    //开始薪资范围
    private Double beginSalary;
    //结束薪资范围
    private Double endSalary;
    //角色id数组
    private Integer[] roleidarr;
    //角色个数
    private  Integer roleidarrcount;


    private Integer areaId1;

    private Integer areaId2;

    private Integer areaId3;

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

    public Integer getRoleidarrcount() {
        return roleidarrcount;
    }

    public void setRoleidarrcount(Integer roleidarrcount) {
        this.roleidarrcount = roleidarrcount;
    }

    public Integer[] getRoleidarr() {
        return roleidarr;
    }

    public void setRoleidarr(Integer[] roleidarr) {
        this.roleidarr = roleidarr;
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

    public Integer getBeginAge() {
        return beginAge;
    }

    public void setBeginAge(Integer beginAge) {
        this.beginAge = beginAge;
    }

    public Integer getEndAge() {
        return endAge;
    }

    public void setEndAge(Integer endAge) {
        this.endAge = endAge;
    }

    public Double getBeginSalary() {
        return beginSalary;
    }

    public void setBeginSalary(Double beginSalary) {
        this.beginSalary = beginSalary;
    }

    public Double getEndSalary() {
        return endSalary;
    }

    public void setEndSalary(Double endSalary) {
        this.endSalary = endSalary;
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
}
