package com.fh.shop.api.address.vo;

import lombok.Data;

import java.io.Serializable;
@Data
public class AddressVo implements Serializable {


    //主键
    private Integer id;
    //会员id
    private Long memberId;
    //省
    private String sheng;
    //市
    private String shi;
    //县
    private String xian;
    //详细地址
    private String detailAddress;
    //是否默认
    private Integer defaultAddress;

}
