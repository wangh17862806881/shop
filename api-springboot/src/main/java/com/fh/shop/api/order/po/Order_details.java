package com.fh.shop.api.order.po;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
@Data
public class Order_details implements Serializable {

    //订单id  联合主键
    private String orderId;
    //商品id   联合主键
    private Long productId;
    //商品名
    private String productName;
    //商品数量
    private int count;
    //【商品价格’
    private BigDecimal productPrice;
    //商品图片URL
    private String productImgURL;
    //小计
    private BigDecimal subPrice;
    //用户id  冗余字段
    private Long memberId;


}
