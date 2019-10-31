package com.fh.shop.api.brand.vo;

import java.io.Serializable;

public class BrandVo implements Serializable {

    //主键
    private Long id;
    //品牌名
    private String brandName;
    //图片路径
    private String imgURL;

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
