package com.fh.shop.admin.vo.Resource;

import java.io.Serializable;

public class ResourceVo implements Serializable {

    //主键
    private Long id;
    //菜单名
    private String name;
    //父id
    private Long pId;
    //路径
    private String menuUrl;
    //类型
    private Integer type;

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getpId() {
        return pId;
    }

    public void setpId(Long pId) {
        this.pId = pId;
    }
}
