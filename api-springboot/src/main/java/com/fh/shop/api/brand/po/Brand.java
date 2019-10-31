package com.fh.shop.api.brand.po;

import java.io.Serializable;

public class Brand implements Serializable {

    //主键
    private Long id;
    //品牌名
    private String brandName;
    //图片路径
    private String imgURL;
    // 排序字段
    private Long sort;
    //是否热销
    private Integer popular;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }

    public Integer getPopular() {
        return popular;
    }

    public void setPopular(Integer popular) {
        this.popular = popular;
    }
}
