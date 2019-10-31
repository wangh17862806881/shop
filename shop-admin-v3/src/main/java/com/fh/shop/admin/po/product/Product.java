package com.fh.shop.admin.po.product;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fh.shop.admin.po.brand.Brand;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Product implements Serializable {
    //主键
    private Long id;
    //商品名
    private String productName;
    //商品价格
    private BigDecimal price;
    //商品图片路径
    private String imgURL;
    //商品旧路径
    @TableField(exist = false)
    private String oldImgURL;
    //生产日期
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date productDate;
    //品牌
    @TableField(exist = false)
    private String brandName;
    //品牌id
    private Long brandId;
    //分类
    private Long classId1;

    private Long classId2;

    private Long classId3;

    private Integer popular;

    private String categoryName;



    public String getOldImgURL() {
        return oldImgURL;
    }

    public void setOldImgURL(String oldImgURL) {
        this.oldImgURL = oldImgURL;
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

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public Date getProductDate() {
        return productDate;
    }

    public void setProductDate(Date productDate) {
        this.productDate = productDate;
    }
}
