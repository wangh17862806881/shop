package com.fh.shop.api.address.po;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
@Data
public class Address implements Serializable {
    //主键
    private Integer id;
    //会员id
    private Long memberId;
    //省
    private Long shengId;
    //市
    private Long shiId;
    //县
    private Long xianId;
    //省
    @TableField(exist = false)
    private String ssxName;
    //详细地址
    private String detailAddress;
    //是否默认
    private Integer defaultAddress;
    //收件人
    private String takePeople;
    //收件人电话
    private String takePhone;
    //邮箱
    private String email;
    //别名‘
    private Integer alias;


}
