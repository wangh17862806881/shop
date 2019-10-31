package com.fh.shop.api.cart.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class CartInfo implements Serializable {

    //总数量
    private Long tatalCount;
    //总价格
    private String totalPrice;
    //商品信息  全部  集合
    private List<ProductInfo>  productInfoList = new ArrayList<>();


}
