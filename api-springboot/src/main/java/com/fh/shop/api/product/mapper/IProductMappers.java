package com.fh.shop.api.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fh.shop.api.product.po.Product;
import org.apache.ibatis.annotations.Param;

public interface IProductMappers extends BaseMapper<Product> {

    Long updateProduct(@Param("count")Long count, @Param("id") Long id);
}
