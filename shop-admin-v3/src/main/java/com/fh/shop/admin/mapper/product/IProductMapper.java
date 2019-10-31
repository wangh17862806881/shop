package com.fh.shop.admin.mapper.product;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fh.shop.admin.param.product.ProductSeracherParam;
import com.fh.shop.admin.po.product.Product;

import java.util.List;

public interface IProductMapper extends BaseMapper<Product> {
//查询总条数
    Long findTotalCount(ProductSeracherParam seracherParam);
//查询商品数据
    List<Product> findALLProduct(ProductSeracherParam seracherParam);
//新增商品
    void addProduct(Product product);
//回显
    Product findProductById(Long id);
//修改商品方法
    void updateProductById(Product product);
//删除 单个 通过 id
    void deleteProductById(Long id);
//通过id数组寻找对应类别 分类
    List<String> findClassById(String[] str);

    List<Product> findALLProductNoCondi(ProductSeracherParam productSeracherParam);
}
