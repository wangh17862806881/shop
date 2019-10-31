package com.fh.shop.admin.vo.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class UserVo implements Serializable {

    //主键
    private Long id;
    //用户名
    private String userName;
    //真实姓名
    private String realName;
    //性别
    private Integer sex;
    //年龄
    private Integer age;
    //手机号
    private String phone;
    //邮箱
    private String email;
    //薪水
    private String salary;
    //入职时间
    private String entryTime;
    //密码
    private String password;
    //角色名
    private String roleNames;
    //角色id集合
    private List<Integer> roleIdlist = new ArrayList<>();
    //头像路径
    private String imgURL;
    //状态
    private boolean status;

    private Integer areaId1;

    private Integer areaId2;

    private Integer areaId3;

    private String areaNames;

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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public List<Integer> getRoleIdlist() {
        return roleIdlist;
    }

    public void setRoleIdlist(List<Integer> roleIdlist) {
        this.roleIdlist = roleIdlist;
    }

    public Long getId() {
        return id;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public String getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(String roleNames) {
        this.roleNames = roleNames;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
