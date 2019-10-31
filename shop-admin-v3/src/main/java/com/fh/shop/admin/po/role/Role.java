package com.fh.shop.admin.po.role;

import java.io.Serializable;

public class Role implements Serializable {

    //主键
    private Integer id;
    //角色名
    private String roleName;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
