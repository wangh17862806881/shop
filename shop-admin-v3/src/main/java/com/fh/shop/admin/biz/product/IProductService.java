package com.fh.shop.admin.biz.product;

import com.fh.shop.admin.common.DataTableResult;
import com.fh.shop.admin.common.ServerResponse;
import com.fh.shop.admin.param.product.ProductSeracherParam;
import com.fh.shop.admin.po.product.Product;
import com.fh.shop.admin.vo.product.ProductVo;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

public interface IProductService {

//新增商品
    void addProduct(Product product);
//回显
    ProductVo findProductById(Long id);
//修改商品方法
    void updateProductById(Product product);
//删除 单个 通过 id
    void deleteProductById(Long id);
//查询商品 条件  分页
    DataTableResult findAllproductData(ProductSeracherParam seracherParam);

    ServerResponse updateProductPopular(Product product);


    List<Product> findALLProductNoCondi(ProductSeracherParam productSeracherParam);
}
