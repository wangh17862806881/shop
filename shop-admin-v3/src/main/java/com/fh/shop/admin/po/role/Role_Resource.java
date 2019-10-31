package com.fh.shop.admin.po.role;

import java.io.Serializable;

public class Role_Resource implements Serializable {
    //主键
    private Long id;
    //角色id
    private Integer roleId;
    //菜单 资源 id
    private Long resourceId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }
}
