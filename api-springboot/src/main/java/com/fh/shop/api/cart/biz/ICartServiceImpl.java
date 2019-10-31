package com.fh.shop.api.cart.biz;

import com.alibaba.fastjson.JSONObject;
import com.fh.shop.api.annotation.check;
import com.fh.shop.api.cart.vo.CartInfo;
import com.fh.shop.api.cart.vo.ProductInfo;
import com.fh.shop.api.common.ResponseEnum;
import com.fh.shop.api.common.ServerResponse;
import com.fh.shop.api.product.mapper.IProductMappers;
import com.fh.shop.api.product.po.Product;
import com.fh.shop.api.util.KeyUtil;
import com.fh.shop.api.util.RedisUtil;
import com.fh.shop.api.util.SystemConst;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@Service("cartService")
public class ICartServiceImpl implements ICartService {

    @Resource
    private IProductMappers productMapper;


    //查询购物车
    @Override
    public ServerResponse findCart(Long memberId) {

        String cartJsonStr = RedisUtil.hget(SystemConst.REDIS_CART_MAP_NAME, KeyUtil.buildCartNameField(memberId));
        //判断当前购物车是否有数据
        if(StringUtils.isEmpty(cartJsonStr)){
            return ServerResponse.error(ResponseEnum.CART_IS_NULL);
        }
        CartInfo cartInfo = JSONObject.parseObject(cartJsonStr, CartInfo.class);

        return ServerResponse.success(cartInfo);
    }
    //单个删除
    @Override
    public ServerResponse deleteById(Long productId, Long memberId) {
        String hget = RedisUtil.hget(SystemConst.REDIS_CART_MAP_NAME, KeyUtil.buildCartNameField(memberId));
        //判断非空
        if(StringUtils.isEmpty(hget)){
            return ServerResponse.error(ResponseEnum.CART_IS_NULL);
        }
        //转换 购物车对象
        CartInfo cartInfo = JSONObject.parseObject(hget, CartInfo.class);
        //获取商品集合
        List<ProductInfo> productInfoList = cartInfo.getProductInfoList();
        //删除 通过商品id  删除成功返回true
        Boolean aBoolean = deleteProductByid(productId, productInfoList);
        if(!aBoolean){
            //删除失败  返回信息
           return ServerResponse.error(ResponseEnum.PRODUCT_IS_NULL);
        }
        //删除成功  重新计算属性值
        getNewCartInfo(memberId,cartInfo);
        return ServerResponse.success();
    }


    //通过商品id删除
    Boolean deleteProductByid(Long productId, List<ProductInfo> productInfoList) {
        boolean flag = false;
        for (int i = 0; i <productInfoList.size() ; i++) {
            if(productInfoList.get(i).getId().equals(productId)){
                productInfoList.remove(i);
                flag = true;
                break;
            }
        }
        return flag;
    }




    //新增商品
    @Override
    @check
    public ServerResponse addCart(Long memberId, Long productId, Long count) {
        //获取商品
        Product product = productMapper.selectById(productId);
        //判断商品是否存在
       if(null == product){
            return ServerResponse.error(ResponseEnum.PRODUCT_IS_NULL);
       }
        //判断商品是否上架 状态是否允许添加购物车
        if(product.getGround() != 1){
            return ServerResponse.error(ResponseEnum.PRODUCT_IS_NOT_GROUND);
        }
        //判断当前用户是否有购物车---- 没有购物车 ----------------------------------------------------------------------------------------
        String hget = RedisUtil.hget(SystemConst.REDIS_CART_MAP_NAME, KeyUtil.buildCartNameField(memberId));
       if(StringUtils.isEmpty(hget)){
        //构建购物车  ’
        CartInfo cartInfo = new CartInfo();
        //更新购物车 并上传redis
        getNewCart(memberId,count, product, cartInfo);
        //返回
        return ServerResponse.success();
       }
        //有购物车-----------------------------------------------------------------------------------------------------------------
        CartInfo cartInfo = JSONObject.parseObject(hget, CartInfo.class);
        List<ProductInfo> productInfoList = cartInfo.getProductInfoList();
        ProductInfo productInfo = null;
        for (int i = 0; i < productInfoList.size(); i++) {
            if(productInfoList.get(i).getId() .equals(productId)){
                productInfo = productInfoList.get(i);
                break;
            }
        }
        //判断当前购物车中是否含有要添加的商品 --- 不含有商品   ---------------------------------------------------------------------
        if(null == productInfo){
           //更新购物车并上传
          getNewCart(memberId,count,product,cartInfo);
          return ServerResponse.success();
        }

        //--有购物车 且含有当前要添加的商品-----------------------------------------------------------------------------------------------------------

            //修改商品中信息   如数量  价格小计
            productInfo.setProductCount(productInfo.getProductCount() + count);
            BigDecimal bigDecimalMultiPly = getBigDecimalMultiPly(productInfo.getPrice(), productInfo.getProductCount());
            productInfo.setAllPrice(bigDecimalMultiPly.toString());
            productInfo.setAllPrice(bigDecimalMultiPly.toString());
            //判断修改后的数据  数量是否还存在
            if(productInfo.getProductCount() < 1){
                deleteProductByid(productId,productInfoList);
            }
        //更新购物车信息   如价格总计  商品总数量
        getNewCartInfo(memberId,cartInfo);
        //返回信息
        return ServerResponse.success();
    }


    void getNewCart(Long memberId ,Long count, Product product, CartInfo cartInfo) {
        //构建商品
        ProductInfo productInfo = getProductInfo(count, product);
        cartInfo.getProductInfoList().add(productInfo);
        //将商品放进购物车
        getNewCartInfo(memberId, cartInfo);
    }

    private void getNewCartInfo(Long memberId, CartInfo cartInfo) {
        List<ProductInfo> productInfoList = cartInfo.getProductInfoList();
        if(productInfoList.size() < 1){
            //购物车中 没有任何商品了  直接删除购物车
            RedisUtil.hdel(SystemConst.REDIS_CART_MAP_NAME,KeyUtil.buildCartNameField(memberId));
        }else {

            //更新购物车信息  可以直接将商品你信息里数据
            long totalCount = 0;
            String totalPrice = "0.00";

            for (int i = 0; i < productInfoList.size(); i++) {
                //设置总条数
                totalCount += productInfoList.get(i).getProductCount();
                //设置总计价格
                totalPrice = getBigdecimalAdd(productInfoList.get(i).getAllPrice(), totalPrice).toString();
            }

            cartInfo.setTatalCount(totalCount);
            cartInfo.setTotalPrice(totalPrice);
            String jsonStr = JSONObject.toJSONString(cartInfo);
            //上传redis
            RedisUtil.hset(SystemConst.REDIS_CART_MAP_NAME, KeyUtil.buildCartNameField(memberId), jsonStr);
        }
    }

    ProductInfo getProductInfo(Long count, Product product) {
        ProductInfo productInfo = new ProductInfo();
        String price = product.getPrice().toString();
        productInfo.setPrice(price);
        productInfo.setId(product.getId());
        productInfo.setProductName(product.getProductName());
        productInfo.setProductCount(count);
        BigDecimal bigDecimal = getBigDecimalMultiPly(price,count);
        productInfo.setAllPrice(bigDecimal.toString());
        productInfo.setImgURL(product.getImgURL());
        return productInfo;
    }

    //获取bigdecimal 小计价格  bigdecimal 相乘
    BigDecimal getBigDecimalMultiPly(String price,Long count) {
        BigDecimal bigDecimalprice = new BigDecimal(price);
        BigDecimal bigDecimalcount = new BigDecimal(count);
        return bigDecimalcount.multiply(bigDecimalprice).setScale(2);
    }
    //bigdecimal相加
    BigDecimal getBigdecimalAdd(String oldPrice , String price){
        if(StringUtils.isEmpty(price)){
            price = "0.00";
        }
        BigDecimal bigDecimal = new BigDecimal(oldPrice);
        BigDecimal bigDecimaprice = new BigDecimal(price);
        return bigDecimal.add(bigDecimaprice).setScale(2);
    }

}
