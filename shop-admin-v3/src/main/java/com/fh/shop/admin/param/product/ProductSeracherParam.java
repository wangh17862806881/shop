package com.fh.shop.admin.param.product;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fh.shop.admin.common.page;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ProductSeracherParam extends page implements Serializable {

    //商品名
    private String productName;
    //开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:dd:ss")
    private Date beginTime;
    //结束时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:dd:ss")
    private Date endTime;
    //开始价格范围
    private BigDecimal beginPrice;
    //结束价格范围
    private BigDecimal endPrice;
    //品牌id
    private Long brandId;

    private Long classId1;

    private Long classId2;

    private Long classId3;

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

    public Long getClassId1() {
        return classId1;
    }

    public void setClassId1(Long classId1) {
        this.classId1 = classId1;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getBeginPrice() {
        return beginPrice;
    }

    public void setBeginPrice(BigDecimal beginPrice) {
        this.beginPrice = beginPrice;
    }

    public BigDecimal getEndPrice() {
        return endPrice;
    }

    public void setEndPrice(BigDecimal endPrice) {
        this.endPrice = endPrice;
    }
}
