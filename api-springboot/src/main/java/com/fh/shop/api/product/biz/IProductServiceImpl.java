package com.fh.shop.api.product.biz;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fh.shop.api.common.ServerResponse;
import com.fh.shop.api.product.mapper.IProductMappers;
import com.fh.shop.api.product.po.Product;
import com.fh.shop.api.product.vo.ProductVo;
import com.fh.shop.api.util.DateUtil;
import com.fh.shop.api.util.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service(value="productService")
public class IProductServiceImpl implements IProductService{
    @Resource
    private IProductMappers productMapper;


    @Override
    public ServerResponse findList() {
        String upProductList = RedisUtil.get("upProductList");
        if(StringUtils.isNotEmpty(upProductList)){
            List<ProductVo> productVos = JSONObject.parseArray(upProductList, ProductVo.class);
            return ServerResponse.success(productVos);
        }
        QueryWrapper<Product> productQueryWrapper = new QueryWrapper<>();
        productQueryWrapper.orderByDesc("popular");
        productQueryWrapper.eq("ground","1");
        List<Product> products = productMapper.selectList(productQueryWrapper);
        List<ProductVo> voList = po2vo(products);
        String s = JSONObject.toJSONString(voList);
        RedisUtil.setEx("upProductList",20,s);
        return ServerResponse.success(voList);
    }

    @Override
    public List<Product> getStock() {
        QueryWrapper<Product> productQueryWrapper = new QueryWrapper<>();
        productQueryWrapper.le("stock",5);
        List<Product> products = productMapper.selectList(productQueryWrapper);
        return products;
    }

    //po转vo
    List<ProductVo> po2vo(List<Product> products) {
        List<ProductVo> voList = new ArrayList<>();
        for (Product po : products) {
            ProductVo vo = new ProductVo();
            vo.setId(po.getId());
            vo.setBrandId(po.getBrandId());
            vo.setImgURL(po.getImgURL());
            vo.setPrice(po.getPrice().toString());
            vo.setProductDate(DateUtil.date2str(po.getProductDate(),DateUtil.Y_M_D));
            vo.setProductName(po.getProductName());
            voList.add(vo);
        }
        return voList;
    }
}
