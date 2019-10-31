package com.fh.shop.api.member.vo;

import java.io.Serializable;

public class MemberVo implements Serializable {

    private String menberName;

    private String uuid;

    private String realName;

    private Long id ;

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMenberName() {
        return menberName;
    }

    public void setMenberName(String menberName) {
        this.menberName = menberName;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
