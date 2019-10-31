package com.fh.shop.admin.biz.product;

import com.fh.shop.admin.common.DataTableResult;
import com.fh.shop.admin.common.ServerResponse;
import com.fh.shop.admin.mapper.product.IProductMapper;
import com.fh.shop.admin.param.product.ProductSeracherParam;
import com.fh.shop.admin.po.product.Product;
import com.fh.shop.admin.util.DateUtil;
import com.fh.shop.admin.util.FileUpload;
import com.fh.shop.admin.vo.product.ProductVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Service(value="productService")
public class IProductServiceImpl implements IProductService {
    @Autowired
   private IProductMapper productMapper;

    //新增商品
    @Override
    public void addProduct(Product product) {
        productMapper.addProduct(product);
    }
//回显
    @Override
    public ProductVo findProductById(Long id) {

        Product product =  productMapper.findProductById(id);
        //po对象转vo对象
        ProductVo productVo = getProductVo(product);

        return productVo;
    }
//po对象转vo对象
    private ProductVo getProductVo(Product product) {
        ProductVo productVo = new ProductVo();
        productVo.setId(product.getId());
        productVo.setImgURL(product.getImgURL());
        productVo.setPrice(product.getPrice().toString());
        productVo.setProductName(product.getProductName());
        productVo.setProductDate(DateUtil.date2str(product.getProductDate(),DateUtil.full_year));
        productVo.setBrandName(product.getBrandName());
        productVo.setCategoryName(product.getCategoryName());
        productVo.setPopular(product.getPopular());
        productVo.setClassId1(product.getClassId1());
        productVo.setClassId2(product.getClassId2());
        productVo.setClassId3(product.getClassId3());
        productVo.setBrandId(product.getBrandId());
        return productVo;
    }


    //修改商品方法
    @Override
    public void updateProductById(Product product) {
        if(StringUtils.isNotEmpty(product.getImgURL())){
            //删除旧文件
            FileUpload.delete(product.getOldImgURL());
        }else{
            product.setImgURL(product.getOldImgURL());
        }
        productMapper.updateProductById(product);
    }
//删除 单个 通过 id
    @Override
    public void deleteProductById(Long id) {
        productMapper.deleteProductById(id);
    }
//查询商品 条件  分页
    @Override
    public DataTableResult findAllproductData(ProductSeracherParam seracherParam) {

        //查询总条数
        Long totalCount = productMapper.findTotalCount(seracherParam);
        //查询商品数据
         List<Product> allProduct = productMapper.findALLProduct(seracherParam);
         //po集合转vo集合
        List<ProductVo> allProductVo1 = PoList2VoList(allProduct);

        return new DataTableResult(totalCount,totalCount,seracherParam.getDraw(),allProductVo1);
    }
//修改上下架
    @Override
    public ServerResponse updateProductPopular(Product product) {
        productMapper.updateById(product);
        return ServerResponse.success();
    }


    @Override
    public  List<Product> findALLProductNoCondi(ProductSeracherParam productSeracherParam) {
        List<Product> allProduct =   productMapper.findALLProductNoCondi(productSeracherParam);
        return allProduct;
    }



    //po集合转vo集合
    private List<ProductVo> PoList2VoList(List<Product> allProduct) {
        List<ProductVo> allProductVo = new ArrayList<>();
        for (Product product : allProduct) {
            ProductVo productVo = getProductVo(product);
            allProductVo.add(productVo);
        }
        return allProductVo;
    }






}
