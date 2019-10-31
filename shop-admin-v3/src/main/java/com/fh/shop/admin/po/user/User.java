package com.fh.shop.admin.po.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class User  implements Serializable {

    //主键
    private Long id;
    //用户名
    private String userName;
    //真实姓名
    private String realName;
    //密码
    private String password;
    //性别
    private Integer sex;
    //年龄
    private Integer age;
    //手机号
    private String phone;
    //邮箱
    private String email;
    //薪水
    private BigDecimal salary;
    //入职时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")

    private Date entryTime;
    //角色id字符串
    private String roleIds;
    //头像路径
    private String imgURL;
    //登陆次数
    private Integer loginCount;
    //登陆时间
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date loginDate;
    //登陆时间字符串
    private String loginDateStr;
    //盐
    private String salt;
    //错误登陆时间
    private Date loginErrorDate;
    //错误登陆次数

    private Integer loginErrorCount;
    //验证码
    private String imgCode;


    public String getImgCode() {
        return imgCode;
    }

    public void setImgCode(String imgCode) {
        this.imgCode = imgCode;
    }

    private Integer areaId1;

    private Integer areaId2;

    private Integer areaId3;

    private String areaNames;

    public String getLoginDateStr() {
        return loginDateStr;
    }

    public void setLoginDateStr(String loginDateStr) {
        this.loginDateStr = loginDateStr;
    }

    public String getAreaNames() {
        return areaNames;
    }

    public void setAreaNames(String areaNames) {
        this.areaNames = areaNames;
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

    public Date getLoginErrorDate() {
        return loginErrorDate;
    }

    public void setLoginErrorDate(Date loginErrorDate) {
        this.loginErrorDate = loginErrorDate;
    }

    public Integer getLoginErrorCount() {
        return loginErrorCount;
    }

    public void setLoginErrorCount(Integer loginErrorCount) {
        this.loginErrorCount = loginErrorCount;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getRealName() {
        return realName;
    }

    public String getPassword() {
        return password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getSex() {
        return sex;
    }

    public Integer getAge() {
        return age;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }
}
