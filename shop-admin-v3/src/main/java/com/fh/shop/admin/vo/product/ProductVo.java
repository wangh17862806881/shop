package com.fh.shop.admin.vo.product;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ProductVo implements Serializable {

    //主键
    private Long id;
    //商品名
    private String productName;
    //商品价格
    private String price;
    //商品图片路径
    private String imgURL;

    //生产日期
    private String productDate;
    //品牌名
    private String brandName;
    //分类
    private Long classId1;

    private Long classId2;

    private Long classId3;

    private Integer popular;

    private String categoryName;

    private Long brandId;

    public Long getClassId1() {
        return classId1;
    }

    public void setClassId1(Long classId1) {
        this.classId1 = classId1;
    }

    public Long getClassId2() {
        return classId2;
    }

    public void setClassId2(Long classId2) {
        this.classId2 = classId2;
    }

    public Long getClassId3() {
        return classId3;
    }

    public void setClassId3(Long classId3) {
        this.classId3 = classId3;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getPopular() {
        return popular;
    }

    public void setPopular(Integer popular) {
        this.popular = popular;
    }



    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public String getProductDate() {
        return productDate;
    }

    public void setProductDate(String productDate) {
        this.productDate = productDate;
    }
}
