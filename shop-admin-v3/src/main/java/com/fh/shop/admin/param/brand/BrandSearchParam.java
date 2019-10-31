package com.fh.shop.admin.param.brand;

import com.fh.shop.admin.common.page;

import java.io.Serializable;

public class BrandSearchParam extends page implements Serializable {

    // 排序字段
    private Long sort;
    //品牌名
    private String brandName;

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }
}
