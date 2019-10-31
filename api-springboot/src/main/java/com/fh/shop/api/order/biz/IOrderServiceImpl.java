package com.fh.shop.api.order.biz;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.fh.shop.api.cart.vo.CartInfo;
import com.fh.shop.api.cart.vo.ProductInfo;
import com.fh.shop.api.common.ResponseEnum;
import com.fh.shop.api.common.ServerResponse;
import com.fh.shop.api.member.vo.MemberVo;
import com.fh.shop.api.order.mapper.IOrderMapper;
import com.fh.shop.api.order.mapper.IOrder_DetailsMapper;
import com.fh.shop.api.order.po.Order;
import com.fh.shop.api.order.po.Order_details;
import com.fh.shop.api.payLog.mapper.IPayLogMapper;
import com.fh.shop.api.payLog.po.PayLog;
import com.fh.shop.api.product.mapper.IProductMappers;
import com.fh.shop.api.product.po.Product;
import com.fh.shop.api.util.IdUtil;
import com.fh.shop.api.util.KeyUtil;
import com.fh.shop.api.util.RedisUtil;
import com.fh.shop.api.util.SystemConst;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("orderService")
public class IOrderServiceImpl implements IOrderService {
    @Resource
    private IOrderMapper orderMapper;
    @Resource
    private IOrder_DetailsMapper order_detailsMapper;
    @Resource
    private IProductMappers productMapper;
    @Resource
    private IPayLogMapper payLogMapper;

    @Override
    public ServerResponse insertOrder(MemberVo memberVo) {
        Long memberVoId = memberVo.getId();

        //判断当前redis中是否存在 商品及库存
        boolean exist = RedisUtil.exist(SystemConst.ALL_PRODUCT_STOCK_NAME);
        //非空验证
//获取redis中购物车数据
        String hget = RedisUtil.hget(SystemConst.REDIS_CART_MAP_NAME, KeyUtil.buildCartNameField(memberVoId));
        if(StringUtils.isEmpty(hget)){
            return ServerResponse.error(ResponseEnum.CART_IS_NULL);
        }
        CartInfo cartInfo = JSONObject.parseObject(hget, CartInfo.class);
        //雪花算法 获取id
        String timeId = IdWorker.getTimeId();
        //新增订单  并判断 当前库存数量是否允许购买
        List<ProductInfo> productInfoList = cartInfo.getProductInfoList();
        List<ProductInfo> productErrorInfoList = new ArrayList<>();
        for (int i=productInfoList.size()-1; i>=0; i--) {
            ProductInfo productInfo = productInfoList.get(i);
            Long count = productInfo.getProductCount();
            Order_details order_details = new Order_details();
            Product product = productMapper.selectById(productInfo.getId());
           if(product.getStock() <  productInfo.getProductCount()){
                //将失败订单商品添加到新集合中 用于返回数据
                productErrorInfoList.add(productInfoList.get(i));
                //将失败商品从购物车集合中移出
                productInfoList.remove(i);

            }else{
            //获取数据库被修改的数据的数量
            Long updateCount = productMapper.updateProduct(count, productInfo.getId());
            //判断数据是否被修改  就可以知道是否能成功购买
               if(updateCount == 0){
                   //将失败订单商品添加到新集合中 用于返回数据
                   productErrorInfoList.add(productInfoList.get(i));
                   //将失败商品从购物车集合中移出
                   productInfoList.remove(i);
               }else{
            order_details.setProductId(productInfo.getId());
            order_details.setOrderId(timeId);

            order_details.setCount(count.intValue());
            order_details.setProductImgURL(productInfo.getImgURL());
            order_details.setSubPrice(new BigDecimal(productInfo.getAllPrice()));
            order_details.setMemberId(memberVoId);
            order_details.setProductName(productInfo.getProductName());
            order_details.setProductPrice(new BigDecimal(productInfo.getPrice()));
            order_detailsMapper.insert(order_details);
               }
           }
        }

        //先判断 当前订单是否有能成功下订单的商品 没有直接返回信息
        if(productInfoList.size() <= 0){
            //清空redis
            RedisUtil.hdel(SystemConst.REDIS_CART_MAP_NAME,KeyUtil.buildCartNameField(memberVoId));
            return ServerResponse.error(ResponseEnum.ORDER_NOT_COMMIT);
        }
        //更新购物车
        //更新购物车信息  可以直接将商品你信息里数据
        Integer totalCount = 0;
        String totalPrice = "0.00";
        for (int i = 0; i < productInfoList.size(); i++) {
            //设置总条数
            totalCount += productInfoList.get(i).getProductCount().intValue();
            //设置总计价格
            totalPrice = getBigdecimalAdd(productInfoList.get(i).getAllPrice(), totalPrice).toString();
        }

//----------------------------新增订单--------------------------------------------------------
        Order order = new Order();
        order.setId(timeId);
        order.setStatus(10);
        order.setCreateTime(new Date());
        order.setTotalPrice(new BigDecimal(totalPrice));
        order.setTotalCount(totalCount);
        order.setBillNedd(1);
        order.setAddress("");
        order.setPayType(1);
        order.setTakePeople("aaa");
        orderMapper.insert(order);
        RedisUtil.hdel(SystemConst.REDIS_CART_MAP_NAME,KeyUtil.buildCartNameField(memberVoId));

//---------------------------生成支付日志---------------------------------------------------------------------
        PayLog payLog = new PayLog();
        payLog.setCreateTime(new Date());
        payLog.setMemberId(memberVoId);
        payLog.setOrderId(order.getId());
        payLog.setPayType(1);
        payLog.setPayStatus(1);
        payLog.setPayMoney(order.getTotalPrice());
        //生成交易号 商家单号
        String outTraeId = IdUtil.createId();
        payLog.setOutTraeId(outTraeId);
        //将支付日志新增到数据库
        payLogMapper.insert(payLog);
        //将日志保存到 redis
        String payJsonStr = JSONObject.toJSONString(payLog);
        RedisUtil.set(KeyUtil.buildPayLogKey(memberVoId),payJsonStr);
        return ServerResponse.success(productErrorInfoList);
    }





    @Override
    public ServerResponse findOrder() {
        List<Order> orders = orderMapper.selectList(null);
        return ServerResponse.success(orders);
    }

    @Override
    public ServerResponse findOrder_details() {
        List<Order_details> order_details = order_detailsMapper.selectList(null);
        return ServerResponse.success(order_details);
    }




    BigDecimal getBigdecimalAdd(String oldPrice , String price){
        if(StringUtils.isEmpty(price)){
            price = "0.00";
        }
        BigDecimal bigDecimal = new BigDecimal(oldPrice);
        BigDecimal bigDecimaprice = new BigDecimal(price);
        return bigDecimal.add(bigDecimaprice).setScale(2);
    }
}
