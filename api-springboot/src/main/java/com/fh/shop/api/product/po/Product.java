package com.fh.shop.api.product.po;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
@Data
public class Product implements Serializable {
    //主键
    private Long id;
    //商品名
    private String productName;
    //商品价格
    private BigDecimal price;
    //商品图片路径
    private String imgURL;
    //生产日期
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date productDate;
    //品牌id
    private Long brandId;

    //是否上架
    private Integer ground;

    private Long stock;

}
