package com.fh.shop.api.order.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
@Data
public class Order implements Serializable {
    //订单编号
    @TableId(type = IdType.INPUT)
    private String id;
    //商品总价格
    private BigDecimal totalPrice;
    //商品总数量
    private Integer totalCount;
    //收获地址
    private String address;
    //状态
    private Integer status;
    //创建时间
    private Date createTime;
    //交易关闭时间
    private Date dealCloseTime;
    //交易关闭原因
    private String dealCloseCause;
    //支付时间
    private Date payTime;
    //是否需要发票
    private Integer billNedd;
    //支付成功时间
    private Date paySuccessTime;
    //支付类型
    private Integer payType;
    //快递类型
    private Integer expressType;
    //发货时间
    private Date excepressPostTime;
    //收货时间
    private Date takeTime;
    //评论时间
    private Date commentTime ;
    //评论内容
    private String commentContent;
    //收货人
    private String takePeople;
    //用户id
    private Long memberId;



}
