package com.fh.shop.api.cart.vo;

import lombok.Data;

import java.io.Serializable;
@Data
public class ProductInfo implements Serializable {

    //商品id
    private Long id;
    //商品名
    private String productName;
    //商品价格
    private String price;
    //商品数量
    private Long productCount;
    //价格小计  当前商品一共多少钱  可能有多件
    private String allPrice;

    private String imgURL;

    private Long stock;


}
