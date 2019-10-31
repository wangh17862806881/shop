package com.fh.shop.admin.po.Resource;

import java.io.Serializable;

public class Resource implements Serializable {
    //主键
    private Long id;
    //菜单名
    private String menuName;
    //父id
    private Long fatherId;
    //路径
    private String url;
    //类型
    private Integer type;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Long getFatherId() {
        return fatherId;
    }

    public void setFatherId(Long fatherId) {
        this.fatherId = fatherId;
    }
}
