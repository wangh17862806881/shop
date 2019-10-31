package com.fh.shop.admin.vo.Brand;

import java.io.Serializable;

public class BrandVo implements Serializable {
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

    public Integer getPopular() {
        return popular;
    }

    public void setPopular(Integer popular) {
        this.popular = popular;
    }

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }

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
}
