package com.fh.shop.api.payLog.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("t_pay_log")
public class PayLog implements Serializable {

//id  雪花算法生成  支付单号 商户单号
    @TableId(value="out_trade_no",type=IdType.INPUT)
    private String outTraeId;
//交易单号  流水单号
    private String transactionId;
//创建时间
    private Date createTime;
//支付时间
    private Date payTime;
//会员id
    private Long memberId;
//订单id
    private String orderId;
//交易金额  支付金额  元
    private BigDecimal payMoney;
//交易类型
    private Integer payType;
//交易状态
    private Integer payStatus;
//二维码
    @TableField(exist = false)
    private String qrCode;


}
