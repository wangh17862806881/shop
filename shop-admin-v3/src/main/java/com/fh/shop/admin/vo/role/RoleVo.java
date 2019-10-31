package com.fh.shop.admin.vo.role;

import java.io.Serializable;
import java.util.List;

public class RoleVo implements Serializable {
    //主键
    private Integer id;
    //角色名
    private String roleName;
    //资源 菜单 id 集合
    private List<Long> resourceIdList;
    //资源 / 菜单  名
    private String menuNames;

    public String getMenuNames() {
        return menuNames;
    }

    public void setMenuNames(String menuNames) {
        this.menuNames = menuNames;
    }

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

    public List<Long> getResourceIdList() {
        return resourceIdList;
    }

    public void setResourceIdList(List<Long> resourceIdList) {
        this.resourceIdList = resourceIdList;
    }
}
